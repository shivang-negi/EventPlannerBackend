package voyago.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import voyago.backend.service.JwtService;

@RestController
@RequestMapping("/test")
public class TestMappings {
	
	public JwtService jwtService;

	@Autowired
	public TestMappings(JwtService jwtService) {
		this.jwtService = jwtService;
	}
	
	@GetMapping("/token")
	public String getToken() {
		return jwtService.generateToken("shivangnegi");
	}
	
	@GetMapping("/validate")
	public boolean validateToken() {
		return jwtService.validateToken(
				"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzaGl2YW5nbmVnaSIsImlhdCI6MTczNjY2NTIwNCwiZXhwIjoxNzM2NzUxNjA0fQ.qMVXKEGrM43AhgyHf-iCa-Uiaq8Fc9okiXWTVYiKNF-ChW8fXgZ4Pk-38zxvhaBTlKbv7SGbBcIloOra1pkUpA"
				, "shivangnegi");
	}
}
