package org.projectlarp.app.modules.user;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserTest {

	@Test
	public void test() {
		String[] users = {"admin", "user", "user1", "user2"};
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		for (String username : users)
			System.out.println(String.format("%s %s", username, passwordEncoder.encode(username)));
	}
}
