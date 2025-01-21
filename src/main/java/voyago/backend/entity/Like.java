package voyago.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "likes")
public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(name = "post_id", nullable = false)
	private String postId;
	
	@Column(name = "liked_by", nullable = false)
	private String likedBy;
	
	@Column(name = "timestamp")
	private LocalDateTime timestamp;

	public Like(String postId, String likedBy, LocalDateTime timestamp) {
		super();
		this.postId = postId;
		this.likedBy = likedBy;
		this.timestamp = timestamp;
	}

	public Like(String postId, String likedBy) {
		super();
		this.postId = postId;
		this.likedBy = likedBy;
	}
	
	public Like() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(String likedBy) {
		this.likedBy = likedBy;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
