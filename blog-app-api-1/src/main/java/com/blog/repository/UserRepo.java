package com.blog.repository;

//import java.util.Optional;

//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	//public Optional<User> findByEmail(String email);
	
}
