package voyago.backend.dao;

import java.util.Optional;

import voyago.backend.entity.User;

public interface UserDAO {
	public User save(User user);
	public User findUser(String email, String password);
	public Optional<User> getUser(String id);
	public Optional<User> checkUserExists(String email);
}
