package com.projectlarp.app.modules.user;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/user")
public class UserService {

	@Autowired
	UserRepository data;

	@RequestMapping(value = "token", method = RequestMethod.POST)
	public ResponseEntity<UserResponse> findByToken(@RequestBody UserRequest req) {
		// TODO
		throw new NotYetImplementedException();
	}
}
