package com.techiesgym.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techiesgym.auth.dto.AuthResponse;
import com.techiesgym.auth.dto.LoginRequest;
import com.techiesgym.auth.dto.RegisterRequest;
import com.techiesgym.auth.entity.User;
import com.techiesgym.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtservice;
	private final AuthenticationManager authenticationManager;
	
	//Register new user
	public AuthResponse register(RegisterRequest request) {
		User user = User.builder()
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword())) //Encrypt pwd
				.role(request.getRole())								// MEMBER, TRAINER, ADMIN
				.build();
		userRepository.save(user);
		String jwtToken = jwtservice.generateToken(user);
		return new AuthResponse(jwtToken);
	}
	
	//Authenticate and generate token
	public AuthResponse login(LoginRequest request) {
		//Authenticate using Spring Security
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
						)
				);
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found!"));
		
		String jwtToken = jwtservice.generateToken(user);
		return new AuthResponse(jwtToken);
		
	}
}























