package com.techiesgym.auth.service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.techiesgym.auth.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;
	
	// Generates signing key from the secret
	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
		
	}
	
	// Generate a JWT token for a user
	public String generateToken(User user) {
		return Jwts.builder()
				.setSubject(user.getEmail())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
		
	}
	
	// Extract username/email from token
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
		
	}
	
	// Generic claim extractor
	private <T> T extractClaim(String token, Function<Claims, T> resolver) {
		final Claims claims = extractAllClaims(token);
		return resolver.apply(claims);
		
	}
	
	 // Parse the token and return all claims
	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		
	}
	
	// Check if token is valid for a given user
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token); 
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
		
	}
	
	 // Check if token is expired
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
		
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
		
	}
}
