package org.projectlarp.app.modules.auth;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @see origin
 *      https://stackoverflow.com/questions/19500332/spring-security-and-json-
 *      authentication
 */
@Component
public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private boolean postOnly;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("attemptAuthentication");
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		UsernamePasswordAuthenticationToken authRequest;
		try {
			authRequest = this.getUserNamePasswordAuthenticationToken(request);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	/**
     * @param request
     * @return
     */
    private UsernamePasswordAuthenticationToken getUserNamePasswordAuthenticationToken(HttpServletRequest request)  throws IOException{
        StringBuffer sb = new StringBuffer();
        BufferedReader bufferedReader = null;
        String content = "";
        LoginRequest sr = null;

        try {
            bufferedReader =  request.getReader();
            char[] charBuffer = new char[128];
            int bytesRead;
            while ( (bytesRead = bufferedReader.read(charBuffer)) != -1 ) {
                sb.append(charBuffer, 0, bytesRead);
            }
            content = sb.toString();
            ObjectMapper objectMapper = new ObjectMapper();
            try{
                sr = objectMapper.readValue(content, LoginRequest.class);
            }catch(Throwable t){
                throw new IOException(t.getMessage(), t);
            }
        } catch (IOException ex) {

            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        return new UsernamePasswordAuthenticationToken(sr.getUsername(), sr.getPassword());
    }
}