package com.projectlarp.app.modules.auth;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.google.common.base.Strings;

public class UserContext {
	private final String username;
	private final List<GrantedAuthority> authorities;

	private UserContext(String username, List<GrantedAuthority> authorities) {
		this.username = username;
		this.authorities = authorities;
	}

	public static UserContext create(String username, List<GrantedAuthority> authorities) {
		if (Strings.isNullOrEmpty(username))
			throw new IllegalArgumentException("Username is blank: " + username);
		return new UserContext(username, authorities);
	}

	public String getUsername() {
		return username;
	}

	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}
}