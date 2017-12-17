package org.projectlarp.app.security.web;

/**
 * Object to return as body in JWT Authentication.
 */
public class JWTLoginResponse {

	public String id_token;
	public String aud;
	public String token_type;
	public Long exp;

	public JWTLoginResponse(String id_token, String aud, Long exp) {
		this.id_token = id_token;
		this.aud = aud;
		this.token_type = "Bearer";
		this.exp = exp;
	}
}
