package org.projectlarp.app.modules.auth;

/**
 * @see https://github.com/sahat/satellizer/blob/master/examples/server/java/src/main/java/com/example/helloworld/core/Token.java
 */
public class LoginResponse {

	public String access_token;
	public String aud;
	public String token_type;
	public Long exp;

	public LoginResponse(String access_token, String aud, Long exp) {
		this.access_token = access_token;
		this.aud = aud;
		this.token_type = "Bearer";
		this.exp = exp;
	}
}