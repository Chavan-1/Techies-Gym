package com.techiesgym.auth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.techiesgym.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
}

/**
 * JpaRepository gives you CRUD operations out of the box. 
 * The findByEmail() method allows Spring Data JPA to auto-generate SQL like
SELECT * FROM users WHERE email = ?
 **/