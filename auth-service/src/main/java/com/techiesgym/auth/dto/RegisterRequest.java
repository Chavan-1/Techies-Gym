package com.techiesgym.auth.dto;

import com.techiesgym.auth.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {
	private String email;
	private String password;
	private Role role;
}

//DTO (Data Transfer Object) is used to carry data between client and server. We never expose full User entity directly for security.