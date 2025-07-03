package com.techiesgym.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	//BCrypt for password hashing
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Expose AuthenticationManager as a bean
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
		return configuration.getAuthenticationManager();
	}
	
	//Allow /api/v1/auth/** endpoints publicly
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

		http
		.csrf(csrf -> csrf.disable())
		.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/v1/auth/**").permitAll()  //allow register/login
				.anyRequest().authenticated()					//secure everything else
		)
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}

/****Adds your custom JwtAuthenticationFilter before UsernamePasswordAuthenticationFilter.
Sets SessionManagement to STATELESS because JWT does not use server-side sessions.*****/
/***@Configuration tells Spring this class defines beans.
@Bean makes Spring manage PasswordEncoder as a singleton.
BCryptPasswordEncoder is the recommended encoder.
It automatically hashes passwords with a salt for security.***/