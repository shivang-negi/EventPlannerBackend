package voyago.backend.rest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import voyago.backend.DTO.UserDTO.FindUserByEmailDTO;
import voyago.backend.DTO.UserDTO.FindUserDTO;
import voyago.backend.DTO.UserDTO.SaveUserDTO;
import voyago.backend.DTO.UserDTO.UserResponseDTO;
import voyago.backend.entity.Post;
import voyago.backend.entity.User;
import voyago.backend.service.JwtService;
import voyago.backend.service.PostService;
import voyago.backend.service.UserService;
import voyago.backend.utils.ResponseWrapper;
import voyago.backend.utils.Status;
import voyago.backend.utils.UserMapper;

@RestController
@RequestMapping("/user")
public class UserMappings {
	
	UserService userService;
	PostService postService;
	JwtService jwtService;
	
	@Autowired
	public UserMappings(UserService userService, PostService postService, JwtService jwtService) {
		this.userService = userService;
		this.postService = postService;
		this.jwtService = jwtService;
	}
	
	@GetMapping("/")
	String getDefaultSlash() {
		return "first page";
	}
	
	@PostMapping("/getUserByCredentials")
	ResponseEntity<ResponseWrapper<UserResponseDTO>> getUserByCredentials(@RequestBody FindUserDTO user) {
		
		User u = userService.findUser(user.getEmail(),user.getPassword());
		String token = jwtService.generateToken(u.getId());
		return ResponseEntity.ok(new ResponseWrapper<UserResponseDTO>(Status.SUCCESS, "user found", new UserResponseDTO(u,token)));
	}
	
	@PostMapping("/getUserByEmail")
	ResponseEntity<ResponseWrapper<User>> getUserByEmail(@RequestBody FindUserByEmailDTO email) {
		
		User u = userService.findUserByEmail(email.getEmail());
		return ResponseEntity.ok(new ResponseWrapper<User>(Status.SUCCESS, "user found", u));
	}
	
	@GetMapping("/getUserById")
	ResponseEntity<ResponseWrapper<User>> getUserByID(@RequestParam String id) {
		User u = userService.findUser(id);
		return ResponseEntity.ok(new ResponseWrapper<User>(Status.SUCCESS,"User found",u));
	}
	
	@PostMapping("/saveUser")
	ResponseEntity<ResponseWrapper<UserResponseDTO>> saveUser(@RequestBody SaveUserDTO user) {
		User u = userService.saveUser(UserMapper.fromDTO(user));
		String token = jwtService.generateToken(u.getId());
		return ResponseEntity.ok(new ResponseWrapper<UserResponseDTO>(Status.SUCCESS,"user saved",new UserResponseDTO(u,token)));
	}
	
	@GetMapping("/getPostsByUser")
	ResponseEntity<?> getPostByUser(@RequestParam String id) {
		List<Post> userList = postService.findByUser(id);
		if(userList.isEmpty()) return ResponseEntity.status(400).body("No posts made by this user");
		else return ResponseEntity.ok(userList);
	}
}
