package voyago.backend.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import voyago.backend.dao.LikeDAO;
import voyago.backend.entity.Like;
import voyago.backend.exception.DatabaseException;

@Service
public class LikeService {
	private LikeDAO likeDao;
	
	@Autowired
	public LikeService(LikeDAO likeDao) {
		this.likeDao = likeDao;
	}

	@Transactional
	public Like likePost(String user, String post) {
		try {
			Optional<Like>likedOrNot = likeDao.findByPostIdAndLikedBy(post, user);
			if(likedOrNot.isPresent()) throw new DatabaseException("Post already liked");
			Like like = new Like( post,user, LocalDateTime.now());
			Like l = likeDao.save(like);
			return l;
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	@Transactional
	public boolean unlikePost(String user, String post) {
		try {
			likeDao.deleteByPostIdAndLikedBy(post, user);
			return true;
		}
		catch(Exception e) {
			throw new DatabaseException("Internal server error.", e);
		}
	}
	
	public boolean getLike(String post, String user) {
		try {
			Optional<Like>like = likeDao.findByPostIdAndLikedBy(post, user);
			if(like.isEmpty()) return false;
			return true;
		}
		catch(Exception e) {
			throw new DatabaseException("Internal server error.", e);
		}
	}
}
