package com.security.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.beans.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

/**
 * This class is a filter to catch the valid JWT on requests.
 * 
 * @author Administrador
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	String ISSUER_INFO = "Yo merengues";
	String HEADER_AUTHORIZATION_KEY = "hrdcoded_auth_header_key";
	String SUPER_SECRET_KEY = "this is a key";
	String TOKEN_BEARER_PREFIX = "hrdcodded_prefix";

	private AuthenticationManager authenticationManager;
	int TOKEN_EXPIRATION_TIME = 60000000;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		// setFilterProcessesUrl("/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("There's been an authentication attempt..");

		try {
			System.out.println("request class: "+ req.getClass());
			System.out.println("request InputStream: "+req.getInputStream());
			System.out.println("request contentType: "+ req.getContentType());
			System.out.println("request ParameterMap: "+ req.getParameterMap());
			
			User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUser(), user.getPassword(), new ArrayList<>()));
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		System.out.println("Succesfull authentication!");

		String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
				.setSubject(((User) auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY).compact();
		response.addHeader(HEADER_AUTHORIZATION_KEY, TOKEN_BEARER_PREFIX + " " + token);
	}

}
