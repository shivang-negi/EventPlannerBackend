package voyago.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import voyago.backend.dao.UserDAO;
import voyago.backend.entity.User;
import voyago.backend.exception.UserException;

@Service
public class UserService {
	private final UserDAO userdao;
	private final PasswordEncoder encoder;
	
	@Autowired
	public UserService(UserDAO userdao, PasswordEncoder encoder) {
		this.userdao = userdao;
		this.encoder = encoder;
	}
	
	public User saveUser(User user) {
		try {
			Optional<User> u = userdao.checkUserExists(user.getEmail());
			if(u.isEmpty()) {
				user.setPassword(encoder.encode(user.getPassword()));
				return userdao.save(user);	
			}
			else throw new UserException("User already exists");
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public User findUser(String email, String password) {
		try {
			Optional<User> u = userdao.checkUserExists(email);
			if(u.isEmpty()) throw new UserException("User doesn't exist.");
			else {
				boolean match = encoder.matches(password, u.get().getPassword());
				if(match) return u.get();
				else throw new UserException("Incorrect password");
			}
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public User findUserByEmail(String email) {
		try {
			Optional<User> u = userdao.checkUserExists(email);
			if(u.isEmpty()) throw new UserException("User doesn't exist.");
			else return u.get();
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public User findUser(String id) {
		try {
			Optional<User> u = userdao.getUser(id);
			if(u.isEmpty()) throw new UserException("User not found.");
			else return u.get();
		}
		catch(Exception e) {
			throw e;
		}
	}
}
