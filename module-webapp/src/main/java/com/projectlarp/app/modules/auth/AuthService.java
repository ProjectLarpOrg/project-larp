package com.projectlarp.app.modules.auth;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

interface IAuthService {

	ResponseEntity<AuthServiceToken> login(@RequestBody AuthServiceLogin login);

	void logout(@RequestBody AuthServiceToken token);
}

@RestController()
@RequestMapping("api/auth")
@Log4j
public class AuthService implements IAuthService {

	@Value("${auth.backdoor.users.jsonarray:}")
	String authBackdoorUsers;

	AuthStats stats = new AuthStats();
	AuthServiceBackdoor backdoor;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<AuthServiceToken> login(@RequestBody AuthServiceLogin login) {
		AuthServiceToken token = null;
		String username = login.getUsername();
		log.debug("LOGON;" + username);
		try {
			if (backdoor().isEnabled() && backdoor().haveUser(username)) {
				token = backdoor().loginByProperties(login);
			} else {
				// TODO login token
				throw new NotYetImplementedException();
			}
			stats.log("LOGON_SUCCESS", username);
			return new ResponseEntity<AuthServiceToken>( //
					new AuthServiceToken(token.toString()), //
					OK);
		} catch (Exception e) {
			stats.log("LOGON_ERROR", username);
			return new ResponseEntity<AuthServiceToken>( //
					BAD_REQUEST);
		}
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public void logout(@RequestBody AuthServiceToken token) {
		// TODO logout token
	}

	AuthServiceBackdoor backdoor() {
		if (backdoor == null)
			backdoor = new AuthServiceBackdoor(authBackdoorUsers);
		return backdoor;
	}

}
