package voyago.backend.DTO.PostDTO;

import java.util.List;
import java.util.Map;

import voyago.backend.entity.Post;

public class PostListDTO {
	private long count;
	private List<Post> posts;
	private List<Map<String,Object>> liked;
	
	public List<Map<String, Object>> getLiked() {
		return liked;
	}
	public void setLiked(List<Map<String, Object>> liked) {
		this.liked = liked;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
