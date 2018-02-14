package org.projectlarp.app.modules.user.web;

import org.projectlarp.app.modules.user.User;
import org.projectlarp.app.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountResource {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;

	// TODO @PostMapping("/register")
	// TODO @GetMapping("/activate")
	// TODO @GetMapping("/authenticate")
	// TODO @PostMapping("/account")
	// TODO @PostMapping(path = "/account/change-password")
	// TODO @PostMapping(path = "/account/reset-password/init")
	// TODO @PostMapping(path = "/account/reset-password/finish")

	@GetMapping("/account")
	public User getAccount() {
		return userService.getUserWithAuthorities().map(User::new)
				.orElseThrow(() -> new IllegalArgumentException("User could not be found"));
	}

}
