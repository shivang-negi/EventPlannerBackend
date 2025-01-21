package voyago.backend.service;

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${jwt.secret_key}")
	private String jwtSecretKey;

	final private long expirationTime = 1000 * 60 * 60 * 24; //24 hours
	
	public String generateToken(String userId) {
		return Jwts.builder()
				.setSubject(userId)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(Keys.hmacShaKeyFor(jwtSecretKey.getBytes()))
				.compact();
	}
	
	public boolean validateToken(String token, String userId) {
		final String extractedUsername = extractClaim(token, Claims::getSubject);
        return extractedUsername.equals(userId) && !isTokenExpired(token);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
	
	private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
