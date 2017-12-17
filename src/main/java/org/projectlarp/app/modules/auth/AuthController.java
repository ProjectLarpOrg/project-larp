package org.projectlarp.app.modules.auth;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.projectlarp.app.modules.admin.Identity;
import org.projectlarp.app.modules.admin.User;
import org.projectlarp.app.modules.admin.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(value = "/login", method = POST)
	@ResponseBody
	public ResponseEntity<LoginResponse> login( //
			@RequestBody LoginRequest request) {
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
			return new ResponseEntity<LoginResponse>(UNAUTHORIZED);
		}
		// generate token
		String id_token = user.getId().toString();
		/*
		 * AuthorizationRequest authorizationRequest = new AuthorizationRequest();
		 * authorizationRequest.setApproved(true); OAuth2Authentication
		 * authenticationRequest = new OAuth2Authentication(authorizationRequest,
		 * authenticationToken); authenticationRequest.setAuthenticated(true); String
		 * accessToken = defaultTokenServices.createAccessToken(auth);
		 */
		String accessToken = user.getUsername();
		// save token
		identity = user.getIdentity();
		if(identity == null) {
			identity = new Identity();
			user.setIdentity(identity);
		}
		identity.setToken(accessToken);
		userRepository.save(user);
		// send token
		LoginResponse resp = new LoginResponse( //
				identity.getToken(), //
				user.getId().toString(), //
				identity.getTokenExpirationDate().getTimeInMillis());
		return new ResponseEntity<LoginResponse>(resp, OK);
	}

	@RequestMapping(value = "/tokeninfo", method = POST)
	@ResponseBody
	public ResponseEntity<TokeninfoResponse> tokeninfo( //
			@RequestParam("access_token") String token) {
		User user;
		Identity ident;
		user = userRepository.findByIdentityToken(token);
		if (user == null | user.getIdentity() == null) {
			return new ResponseEntity<TokeninfoResponse>(NOT_FOUND);
		}
		ident = user.getIdentity();
		if (!ident.isTokenValid()) {
			return new ResponseEntity<TokeninfoResponse>(UNAUTHORIZED);
		}
		TokeninfoResponse res = new TokeninfoResponse( //
				user.getId().toString(), //
				ident.getTokenExpirationDate().getTimeInMillis());
		return new ResponseEntity<TokeninfoResponse>(res, OK);
	}
}