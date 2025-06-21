package com.techiesgym.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
	private String token;
}

//DTO (Data Transfer Object) is used to carry data between client and server. We never expose full User entity directly for security.