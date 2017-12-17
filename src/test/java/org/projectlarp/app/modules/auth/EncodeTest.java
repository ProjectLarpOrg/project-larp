package org.projectlarp.app.modules.auth;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodeTest {

	@Test
	public void test() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		for (String username : new String[]{"admin", "user1", "user2"})
			System.out.println(String.format("%s %s", username, passwordEncoder.encode(username)));
	}
	
}
