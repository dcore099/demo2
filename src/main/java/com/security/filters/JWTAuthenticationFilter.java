package com.security.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;

/**
 * This class is a filter to catch the valid JWT on requests. Interesting read
 * on Signature algorithms
 * https://datatracker.ietf.org/doc/html/rfc7518#section-3.2
 * 
 * @author Administrador
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	String ISSUER_INFO = "Yo merengues";
	String HEADER_AUTHORIZATION_KEY = "MY_AUTH_KEY_HEADER";
	SecretKey SUPER_SECRET_KEY = io.jsonwebtoken.security.Keys.secretKeyFor(SignatureAlgorithm.HS512);
	String TOKEN_BEARER_PREFIX = "MY_TOKEN_PREFIX";
	int TOKEN_EXPIRATION_TIME = 60000000;

	private AuthenticationManager authenticationManager;
	

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		// setFilterProcessesUrl("/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("There's been an authentication attempt..");

		// System.out.println("req type: "+req.getMethod());
		// System.out.println("params: "+req.getParameter("password"));

		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getParameter("username"),
				req.getParameter("password"), new ArrayList<>()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		System.out.println("Succesfull authentication!");

		String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
				.setSubject(((User) auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY).compact();
		response.addHeader(HEADER_AUTHORIZATION_KEY, TOKEN_BEARER_PREFIX + " - " + token);
	}

}
