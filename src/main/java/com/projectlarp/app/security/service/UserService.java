package com.projectlarp.app.security.service;

import java.util.List;

import com.projectlarp.app.security.domain.model.User;


public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
}
