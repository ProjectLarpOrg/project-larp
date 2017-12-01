package org.projectlarp.app.modules.auth;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.projectlarp.app.modules.admin.User;
import org.projectlarp.app.modules.admin.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
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

	@Autowired
	UserRepository userRepository;
	/*
	@Autowired
	DefaultTokenServices defaultTokenServices;
	*/
	@RequestMapping( //
			value = "/login", //
			method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<LoginResponse> login( //
			@RequestBody LoginRequest user) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( //
				user.getUsername(), //
				user.getPassword());
		try {
			// auth
			Authentication auth = authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(auth);
			User u = userRepository.findByUsername(user.getUsername());
			// generate token
			String id_token = u.id.toString();
			/*
			AuthorizationRequest authorizationRequest = new AuthorizationRequest();
		    authorizationRequest.setApproved(true);
			OAuth2Authentication authenticationRequest = new OAuth2Authentication(authorizationRequest, authenticationToken);
	        authenticationRequest.setAuthenticated(true);
			String accessToken = defaultTokenServices.createAccessToken(auth);
			*/
			String accessToken = user.getUsername();
			// save token
			u.setToken(accessToken);
			userRepository.save(u);
			// send token
			LoginResponse response = new LoginResponse(u.getToken());

			return new ResponseEntity<LoginResponse>(response, OK);
		} catch (BadCredentialsException ex) {
			return new ResponseEntity<LoginResponse>(UNAUTHORIZED);
		}
	}
}