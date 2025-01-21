package voyago.backend.dao;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import voyago.backend.entity.Post;

public interface PostDAO extends JpaRepository<Post, String> {
	long count();
	List<Post> findByCreatedBy_Id(String userId);
	Page<Post> findAll(Pageable pageable);
}


