package voyago.backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import voyago.backend.entity.Like;

public interface LikeDAO extends JpaRepository<Like, String> {
	void deleteByPostIdAndLikedBy(String post, String user);
	Optional<Like> findByPostIdAndLikedBy(String post, String user);
}
