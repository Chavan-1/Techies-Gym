package com.techiesgym.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techiesgym.auth.dto.AuthResponse;
import com.techiesgym.auth.dto.LoginRequest;
import com.techiesgym.auth.dto.RegisterRequest;
import com.techiesgym.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
		AuthResponse response = authService.register(request);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
		AuthResponse response = authService.login(request);
		return ResponseEntity.ok(response);
	}
}

/****@RequestBody	Automatically converts incoming JSON to Java DTO (RegisterRequest)	
	 ResponseEntity.ok(...)	Standard Spring way to send HTTP 200 OK with JSON body

****/