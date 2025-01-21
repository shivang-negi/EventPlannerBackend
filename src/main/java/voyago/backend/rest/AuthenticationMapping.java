package voyago.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import voyago.backend.DTO.AuthDTO.AuthTokenDTO;
import voyago.backend.service.JwtService;
import voyago.backend.utils.ResponseWrapper;
import voyago.backend.utils.Status;

@RestController
@RequestMapping("/auth")
public class AuthenticationMapping {
	
	JwtService jwtService;
	
	@Autowired
	public void AuthenticatonMapping(JwtService jwtService) {
		this.jwtService = jwtService;
	}
	
	@PostMapping("/verify")
	public ResponseEntity<ResponseWrapper<Boolean>> validateToken(@RequestBody AuthTokenDTO tokenDTO) {
		Boolean val = jwtService.validateToken(tokenDTO.getToken(), tokenDTO.getId());
		return ResponseEntity.ok().body(new ResponseWrapper<>(Status.SUCCESS,"JWT validation",val));
	}
}
