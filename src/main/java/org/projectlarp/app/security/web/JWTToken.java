package org.projectlarp.app.security.web;

/**
 * Object to return as body in JWT Authentication.
 */
public class JWTToken {

	public String access_token;
	public String token_type;
	public Long exp;

	public JWTToken(String access_token, Long exp) {
		this.access_token = access_token;
		this.token_type = "Bearer";
		this.exp = exp;
	}
}
