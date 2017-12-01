package org.projectlarp.app.modules.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @see https://github.com/sahat/satellizer/blob/master/examples/server/java/src/main/java/com/example/helloworld/core/Token.java
 */
public class LoginResponse {
	public String access_token;
	public String id_token = "my_profil";
	public String token_type = "Bearer";
	public int  expires_in = 3600;

	public LoginResponse(@JsonProperty("access_token") String access_token) {
		this.access_token = access_token;
	}
}