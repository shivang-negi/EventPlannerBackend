package voyago.backend.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import voyago.backend.utils.LazyLoadingSerializer;

@Entity
@Table(name = "comments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(name = "comment_content", nullable = false)
	private String commentContent;
	
	@Column(name = "timestamp", nullable = false)
	private LocalDateTime timestamp;
	
	@ManyToOne
	@JoinColumn(name = "comment_by", nullable = false)
	private User commentBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_on", nullable = false)
	@JsonSerialize(using = LazyLoadingSerializer.class)
	private Post commentOn;
	
	@Column(name = "comment_ref", nullable = true)
	private String commentRef;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public User getCommentBy() {
		return commentBy;
	}

	public void setCommentBy(User commentBy) {
		this.commentBy = commentBy;
	}

	public Post getCommentOn() {
		return commentOn;
	}

	public void setCommentOn(Post commentOn) {
		this.commentOn = commentOn;
	}

	public String getCommentRef() {
		return commentRef;
	}

	public void setCommentRef(String commentRef) {
		this.commentRef = commentRef;
	}
	
	public Comment() {
		
	}

	public Comment(String commentContent, LocalDateTime timestamp, User commentBy, Post commentOn, String commentRef) {
		super();
		this.commentContent = commentContent;
		this.timestamp = timestamp;
		this.commentBy = commentBy;
		this.commentOn = commentOn;
		this.commentRef = commentRef;
	}

	public Comment(String commentContent, LocalDateTime timestamp, User commentBy, Post commentOn) {
		super();
		this.commentContent = commentContent;
		this.timestamp = timestamp;
		this.commentBy = commentBy;
		this.commentOn = commentOn;
	}

}
