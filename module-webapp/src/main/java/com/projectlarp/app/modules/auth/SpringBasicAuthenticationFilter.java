package com.projectlarp.app.modules.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @see origin
 *      https://javattitude.com/2014/06/07/spring-security-custom-token-based-
 *      rest-authentication/
 */
public class SpringBasicAuthenticationFilter extends BasicAuthenticationFilter {

	public SpringBasicAuthenticationFilter(final AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void onSuccessfulAuthentication(final javax.servlet.http.HttpServletRequest request,
			final javax.servlet.http.HttpServletResponse response, final Authentication authResult) {
		// Generate Token
		// Save the token for the logged in user
		// send token in the response
		response.setHeader("header-name", "token");
	}

}