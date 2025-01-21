package voyago.backend.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import voyago.backend.entity.User;
import voyago.backend.exception.DatabaseException;
import voyago.backend.exception.UserException;

@Repository
public class UserDAOImpl implements UserDAO{
	
	private EntityManager em;
	
	@Autowired
	public UserDAOImpl(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	public User save(User user) {
		try {
			em.persist(user);
			return user;
		}
		catch(Exception e) {
			throw new UserException("Could not save the user. " + e.getMessage());
		}
	}
	
	public User findUser(String email, String password) {
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			
			return query.getSingleResult();
		}
		catch(NoResultException e) {
			throw new UserException("User with given credentials does not exist.");
		}
		catch(Exception e) {
			throw new DatabaseException("Internal database error", e);
		}
	}
	
	public Optional<User> getUser(String id) {
		try {
			System.out.print(id);
			User user = em.find(User.class, id);
			return Optional.ofNullable(user);
		}
		catch(Exception e) {
			throw new DatabaseException("Internal database error", e);
		}
	}
	
	public Optional<User> checkUserExists(String email) {
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
			query.setParameter("email", email);

			return Optional.of(query.getSingleResult());
		}
		catch(NoResultException e) {
			return Optional.empty();
		}
		catch(Exception e) {
			throw new DatabaseException("Internal database error", e);
		}
	}
}
