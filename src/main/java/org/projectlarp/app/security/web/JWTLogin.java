package org.projectlarp.app.security.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.projectlarp.app.config.Constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JWTLogin {

	@Pattern(regexp = Constants.LOGIN_REGEX)
	@NotNull
	@Size(min = 1, max = 50)
	private String username;

	@NotNull
	private String password;

	private Boolean rememberMe = false;

	@JsonCreator
	public JWTLogin(@JsonProperty("username") String username, @JsonProperty("password") String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
}