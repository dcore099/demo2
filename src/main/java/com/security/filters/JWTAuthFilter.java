package com.security.filters;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

/**
 * This class will filter the requests through the application and check for a
 * valid JWT token.
 * 
 * @author Administrador
 *
 */
public class JWTAuthFilter extends BasicAuthenticationFilter {

	String ISSUER_INFO = "Yo merengues";
	String HEADER_AUTHORIZATION_KEY = "hrdcoded_auth_header_key";
	String SUPER_SECRET_KEY = "this is a key";
	String TOKEN_BEARER_PREFIX = "hrdcodded_prefix";

	public JWTAuthFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("The filter caught request..");

		String header = req.getHeader(HEADER_AUTHORIZATION_KEY);
		if ((header == null) || header.startsWith(HEADER_AUTHORIZATION_KEY)) {
			chain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken auth = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {

		System.out.println("Retrieving the JWT from the request header..");

		String token = req.getHeader(HEADER_AUTHORIZATION_KEY);
		if (token != null) {
			String user = Jwts.parser().setSigningKey(SUPER_SECRET_KEY)
					.parseClaimsJws(token.replace(TOKEN_BEARER_PREFIX, "")).getBody().getSubject();

			System.out.println("user variable: " + user);

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			return null;
		}
		return null;
	}

}
