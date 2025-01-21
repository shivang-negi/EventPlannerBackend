package voyago.backend.utils;

import voyago.backend.DTO.UserDTO.SaveUserDTO;
import voyago.backend.entity.User;

public class UserMapper {
	public static User fromDTO(SaveUserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setDob(userDTO.getDob());
        return user;
    }
}
