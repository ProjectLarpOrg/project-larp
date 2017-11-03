package com.projectlarp.app.modules.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @see origin
 *      http://www.baeldung.com/spring-security-authentication-with-a-database
 * 
 * @see origin
 *      https://github.com/gregturn/spring-a-gram/blob/master/spring-a-gram-
 *      frontend/src/main/java/com/greglturnquist/springagram/frontend/
 *      SpringDataJpaUserDetailsService.java
 */
@Service
public class SpringDataJpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User( //
				user.username, //
				user.password, //
				user.enabled, //
				user.accountNonExpired, //
				user.credentialsNonExpired, //
				user.accountNonLocked, //
				AuthorityUtils.createAuthorityList( //
						user.roles //
								.stream() //
								.map(i -> i.getRole()) //
								.toArray(String[]::new)));
	}
}