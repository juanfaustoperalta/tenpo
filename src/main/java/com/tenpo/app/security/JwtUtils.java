package com.tenpo.app.security;

import com.tenpo.app.repository.TokenRepository;
import com.tenpo.app.services.impl.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	private String jwtSecret;

	private int jwtExpirationMs;

	private final TokenRepository tokenRepository;

	public JwtUtils(TokenRepository tokenRepository, @Value("${tenpo.app.jwt.expiration.ms}") int jwtExpirationMs,
					@Value("${tenpo.app.jwt.secret}") String jwtSecret) {
		this.tokenRepository = tokenRepository;
		this.jwtSecret = jwtSecret;
		this.jwtExpirationMs = jwtExpirationMs;
	}


	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		String token = Jwts.builder()
						.setSubject((userPrincipal.getUsername()))
						.setIssuedAt(new Date())
						.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
						.signWith(SignatureAlgorithm.HS512, jwtSecret)
						.compact();
		tokenRepository.whiteListing(userPrincipal.getUsername(), token);
		return token;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			if (hasToken(authToken)) {
				return true;
			}

		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}

	public void invalidate(String token) {
		String username = this.getUserNameFromJwtToken(token);
		tokenRepository.removeToken(username);
	}

	private boolean hasToken(String authToken) {
		String username = this.getUserNameFromJwtToken(authToken);
		Optional<String> token = tokenRepository.getToken(username);
		return token.isPresent() && token.get().equals(authToken);
	}

}