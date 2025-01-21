package voyago.backend.DTO.CommentDTO;

import java.util.List;

import voyago.backend.entity.Comment;

public class FetchCommentsDTO {
	private long count;
	private List<Comment> comments;
	
	public FetchCommentsDTO() {}
	public FetchCommentsDTO(long count, List<Comment> comments) {
		this.count = count;
		this.comments = comments;
	}
	
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
}
