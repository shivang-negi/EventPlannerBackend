package voyago.backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import voyago.backend.DTO.PostDTO.PostListDTO;
import voyago.backend.DTO.PostDTO.SavePostDTO;
import voyago.backend.dao.LikeDAO;
import voyago.backend.dao.PostDAO;
import voyago.backend.entity.Like;
import voyago.backend.entity.Post;
import voyago.backend.entity.User;
import voyago.backend.exception.DatabaseException;
import voyago.backend.exception.PostException;
import voyago.backend.exception.UserException;

@Service
public class PostService {
	private PostDAO postdao;
	private UserService userService;
	private LikeDAO likedao;
	
	
	@Autowired
	public PostService(PostDAO postdao, UserService userService, LikeDAO likedao) {
		this.postdao = postdao;
		this.userService = userService;
		this.likedao = likedao;
	}
	
	public List<Post> findByUser(String id) {
		try {
			List<Post> posts =  postdao.findByCreatedBy_Id(id);
			return posts;
		}
		catch(Exception e) {
			throw new DatabaseException("Internal server error.", e);
		}
	}
	
	public Post findById(String id) {
		return postdao.findById(id).orElseThrow(()->new PostException("Post doesn't exist"));
	}
	
	public Post savePost(SavePostDTO postDTO) {
		try {
			User u = userService.findUser(postDTO.getUserID());
			Post post = new Post(postDTO.getPostContent(),LocalDateTime.now(),u);
			return postdao.save(post);
		}
		catch(UserException e) {
			throw new UserException("user doesn't exist");
		}
		catch(Exception e) {
			throw new DatabaseException(e.getMessage(),e);
		}
	}
	
	public PostListDTO getPostList(int pageNo, String userId) {
		return getPostList(pageNo,5,userId);
	}
	
	public PostListDTO getPostList(int pageNo, int pageSize, String userId) {
		
		long count = postdao.count();
		
		PostListDTO postList = new PostListDTO();
		postList.setCount(count);
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("createdOn").descending());
		Page<Post> posts = postdao.findAll(pageable);
		postList.setPosts(posts.getContent());
		
		List<Map<String,Object>> fav = new ArrayList<>();
		
		for(Post post: posts.getContent()) {
			Optional<Like>like = likedao.findByPostIdAndLikedBy(post.getId(), userId);
			if(like.isEmpty()) {
				fav.add(Map.of("favorite",0,"id",post.getId()));
			}
			else {
				fav.add(Map.of("favorite",1,"id",post.getId()));
			}
		}
		postList.setLiked(fav);
		
		return postList;
	}
}
