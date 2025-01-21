package voyago.backend.DTO.UserDTO;

import voyago.backend.entity.User;

public class UserResponseDTO {
	private User user;
	private String token;
	
	public UserResponseDTO(User user, String token) {
		this.user = user;
		this.token = token;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
