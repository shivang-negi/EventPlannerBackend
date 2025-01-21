package voyago.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import voyago.backend.DTO.CommentDTO.FetchCommentsDTO;
import voyago.backend.DTO.CommentDTO.SaveCommentDTO;
import voyago.backend.dao.CommentDAO;
import voyago.backend.dao.PostDAO;
import voyago.backend.dao.UserDAO;
import voyago.backend.entity.Comment;
import voyago.backend.entity.Post;
import voyago.backend.entity.User;
import voyago.backend.exception.CommentException;
import voyago.backend.exception.PostException;
import voyago.backend.exception.UserException;

@Service
public class CommentService {
	private CommentDAO commentdao;
	private UserDAO userdao;
	private PostDAO postdao;
	
	@Autowired
	public CommentService(CommentDAO commentdao, UserDAO userdao, PostDAO postdao) {
		this.commentdao = commentdao;
		this.userdao = userdao;
		this.postdao = postdao;
	}
	
	public Comment saveComment(SaveCommentDTO commentDto) {
		
		User user = userdao.getUser(commentDto.getCommentBy()).get();
		if(user == null) throw new UserException("User doesn't exist.");
		
		Optional<Post> opost = postdao.findById(commentDto.getCommentOn());
		if(opost.isEmpty()) throw new PostException("Post doesn't exist.");
		
		String c_ref = commentDto.getCommentRef();
		if(c_ref != null) {
			Comment com = commentdao.findById(c_ref).get();
			if(com == null) throw new CommentException("Reference comment doesn't exist");
		}
		
		
		Comment comment = new Comment(
				commentDto.getCommentContent(),
				LocalDateTime.now(),
				user,
				opost.get(),
				c_ref
				);
		
		Comment c = commentdao.save(comment);
		return c;
	}
	
	public FetchCommentsDTO fetchComments(String postId) {
		System.out.println("here");
		List<Comment> comments = commentdao.findByCommentOn_Id(postId);
		long count = comments.size();
		
		return new FetchCommentsDTO(count,comments);
	}
}
