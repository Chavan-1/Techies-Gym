package com.techiesgym.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
}

/**
 *  @Entity: Tells JPA this is a DB table
	@Id, @GeneratedValue: Auto-increment primary key
	@Column(unique = true): No duplicate emails
	@Enumerated: Stores enum as string
	Lombok: Generates getters, setters, constructors
 * **/
 