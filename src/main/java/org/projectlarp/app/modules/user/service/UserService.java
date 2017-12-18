package org.projectlarp.app.modules.user.service;

import java.util.Optional;

import org.projectlarp.app.modules.user.User;
import org.projectlarp.app.modules.user.web.UserRepository;
import org.projectlarp.app.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Transactional(readOnly = true)
	public Optional<User> getUserWithAuthorities() {
		return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByLogin);
	}

}
