package org.projectlarp.app.security.web;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import javax.validation.Valid;

import org.projectlarp.app.modules.user.Identity;
import org.projectlarp.app.modules.user.User;
import org.projectlarp.app.modules.user.service.RandomUtil;
import org.projectlarp.app.modules.user.web.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class JWTController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@PostMapping("/authenticate")
	public ResponseEntity<JWTToken> authorize(@Valid @RequestBody JWTLogin request) {
		User user;
		Identity identity;
		// auth
		try {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken( //
					request.getUsername(), //
					request.getPassword());
			Authentication auth = authenticationManager.authenticate(authToken);
			SecurityContextHolder.getContext().setAuthentication(auth);
			user = userRepository.findByUsername(request.getUsername());
		} catch (BadCredentialsException ex) {
			return new ResponseEntity<JWTToken>(UNAUTHORIZED);
		}
		// generate token
		String accessToken = RandomUtil.generateAccessToken();
		// save token
		identity = user.getIdentity();
		if (identity == null) {
			identity = new Identity();
			user.setIdentity(identity);
		}
		identity.setToken(accessToken);
		userRepository.save(user);
		// send token
		JWTToken resp = new JWTToken( //
				identity.getToken(), //
				identity.getTokenExpirationDate().getTimeInMillis());
		return new ResponseEntity<JWTToken>(resp, OK);
	}

}
