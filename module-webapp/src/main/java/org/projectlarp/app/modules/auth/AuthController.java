package org.projectlarp.app.modules.auth;

import static org.springframework.http.HttpStatus.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @see origin
 *      https://stackoverflow.com/questions/15626917/spring-security-rest-
 *      authorization
 */
@RestController()
@RequestMapping(value = "/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@RequestMapping( //
			value = "/login", //
			method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<AuthResponse> login( //
			@RequestBody Auth user) {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken( //
				user.username, //
				user.password);
		try {
			Authentication auth = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			AuthResponse response = new AuthResponse(token.toString());
			return new ResponseEntity<AuthResponse>(response, OK);
		} catch (BadCredentialsException ex) {
			return new ResponseEntity<AuthResponse>(UNAUTHORIZED);
		}
	}
}