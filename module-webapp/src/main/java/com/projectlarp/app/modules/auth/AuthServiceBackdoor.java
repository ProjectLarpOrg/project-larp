package com.projectlarp.app.modules.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;

import lombok.extern.log4j.Log4j;

interface IAuthServiceBackdoor {

	boolean isEnabled();

	boolean haveUser(String user);

	AuthServiceToken loginByProperties(AuthServiceLogin login);
}

@Log4j
public class AuthServiceBackdoor implements IAuthServiceBackdoor {
	
	Map<String, AuthServiceLogin> users;

	public AuthServiceBackdoor(String jsonString) {
		users = new HashMap<String, AuthServiceLogin>();

		if (Strings.isNullOrEmpty(jsonString))
			return;

		try {
			List<AuthServiceLogin> list = new ObjectMapper() //
					.readValue(jsonString, new TypeReference<List<AuthServiceLogin>>() {
					});
			for (AuthServiceLogin i : list)
				users.put(i.username, i);
		} catch (Exception e) {
			log.warn("Error during backdoor reading", e);
		}
	}

	public boolean isEnabled() {
		return !users.isEmpty();
	}

	public boolean haveUser(String user) {
		return users.containsKey(user);
	}

	public AuthServiceToken loginByProperties(AuthServiceLogin login) {
		String username = login.getUsername();
		String password = login.getPassword();
		boolean isValidBackdoorUser = !users.isEmpty() //
				&& users.containsKey(username) //
				&& users.get(username).password.equals(password);

		if (!isValidBackdoorUser)
			throw new SecurityException("no BACKDOOR profil for this user!" + username);

		log.warn("USER LOGGED BY BACKDOOR!!!");
		return new AuthServiceToken(username);
	}

}
