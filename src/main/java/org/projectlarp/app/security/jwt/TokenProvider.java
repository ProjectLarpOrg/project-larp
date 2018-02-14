package org.projectlarp.app.security.jwt;

import java.util.Collection;

import org.projectlarp.app.modules.user.Identity;
import org.projectlarp.app.modules.user.User;
import org.projectlarp.app.modules.user.service.RandomUtil;
import org.projectlarp.app.modules.user.web.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {

	@Autowired
	UserRepository userRepository;

	private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

	public String createToken(Authentication authentication, boolean rememberMe) {
		User user = userRepository.findByUsername(authentication.getName());
		// generate token
		// FIXME generate a real token from user/pass/auth/remember
		String accessToken = RandomUtil.generateActivationKey();
		// save token
		Identity identity = user.getIdentity();
		if (identity == null) {
			identity = new Identity();
			user.setIdentity(identity);
		}
		identity.setToken(accessToken);
		userRepository.save(user);
		// send token
		return RandomUtil.generateActivationKey();
	}

	@Deprecated // FIXME TOKEN ALWAYS OK
	public Authentication getAuthentication(String token) {
		/*
		 * Claims claims = Jwts.parser() .setSigningKey(secretKey)
		 * .parseClaimsJws(token) .getBody();
		 * 
		 * Collection<? extends GrantedAuthority> authorities =
		 * Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
		 * .map(SimpleGrantedAuthority::new) .collect(Collectors.toList());
		 * 
		 * User principal = new User(claims.getSubject(), "", authorities);
		 */
		Object principal = null;
		Collection<? extends GrantedAuthority> authorities = null;
		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}

	public boolean validateToken(String authToken) {
		try {
			// TODO wts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
			return true;
		} catch (Exception e) {
			log.info("Invalid JWT signature.");
			log.trace("Invalid JWT signature trace: {}", e);
		}
		return false;
	}

}
