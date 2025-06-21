package com.techiesgym.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
	private String email;
	private String password;
}

//DTO (Data Transfer Object) is used to carry data between client and server. We never expose full User entity directly for security.