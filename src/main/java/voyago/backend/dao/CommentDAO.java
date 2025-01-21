package voyago.backend.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import voyago.backend.entity.Comment;

public interface CommentDAO extends JpaRepository<Comment, String> {
	public List<Comment> findByCommentOn_Id(String postId);
}
