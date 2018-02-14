package com.projectlarp.app.security.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectlarp.app.security.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByUsername( String username );
}

