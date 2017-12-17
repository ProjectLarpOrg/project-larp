package org.projectlarp.app.modules.auth.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.projectlarp.app.modules.admin.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @see origin
 *      https://javattitude.com/2014/06/07/spring-security-custom-token-based-rest-authentication/
 */
@Component
public class TokenAuthenticationFilter extends GenericFilterBean {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;

		// extract token from header
		final String accessToken = httpRequest.getHeader("authorization");
		if (null != accessToken && accessToken.startsWith("Bearer ")) {
			final String token = accessToken.replaceAll("Bearer ","");
			org.projectlarp.app.modules.admin.User u = userRepository.findByIdentityToken(token);
			if (u == null) {
				throw new UsernameNotFoundException("token not found: "+accessToken);
			}
			if (!u.getIdentity().isTokenValid()) {
				throw new IllegalArgumentException("token expired: "+accessToken);
			}
			
			final User user = new org.springframework.security.core.userdetails.User( //
					u.getUsername(), //
					u.getPassword() , //
					u.isEnabled(), //
					u.isAccountNonExpired(), //
					u.isCredentialsNonExpired(), //
					u.isAccountNonLocked(), //
					AuthorityUtils.createAuthorityList("ADMIN"));
			final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
					null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		chain.doFilter(request, response);
	}

}
