package org.projectlarp.app.security.web;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import javax.validation.Valid;

import org.projectlarp.app.modules.user.Identity;
import org.projectlarp.app.security.jwt.JWTConfigurer;
import org.projectlarp.app.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
	TokenProvider tokenProvider;

	@PostMapping("/authenticate")
	public ResponseEntity<JWTLoginResponse> authorize(@Valid @RequestBody JWTLoginRequest loginVM) {

		try {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					loginVM.getUsername(), loginVM.getPassword());
			Authentication auth = authenticationManager.authenticate(authToken);
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			boolean rememberMe = (loginVM.getRememberMe() == null) ? false : loginVM.getRememberMe();
			String jwt = tokenProvider.createToken(auth, rememberMe);
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
			
			JWTLoginResponse resp = new JWTLoginResponse( //
					jwt, //
					loginVM.getUsername(), //
					Identity.EXPIRE_IN);
			return new ResponseEntity<>(resp, httpHeaders, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<JWTLoginResponse>(UNAUTHORIZED);
		}
	}

}
