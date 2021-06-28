package com.controllers;

import java.security.Key;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Greeting;
import com.beans.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * Regular REST controller for API stuff..
 * 
 * @author dcore099
 */
@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	/**
	 * This is a simple controller to return a greeting.
	 * 
	 * @param name Receives the message to return along with some text.
	 * @return a greeting object with the received parameter message.
	 */
	@GetMapping("/greetingss")
	public Greeting greetings(@RequestParam(value = "name", defaultValue = "World") String name) {
		System.out.println("Entering controller!");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	
	/**
	 * This method is a login controller for JWT.
	 * 
	 * @param username
	 * @param pass
	 * @return
	 * @deprecated
	 */
	/*
	@GetMapping()
	public User login(@RequestParam("username") String username, @RequestParam("password") String pass) {

		// TODO add a real key from the application, this is a pre-made signin key.
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

		String token = Jwts.builder().setSubject(username).signWith(key).compact();

		User user = new User();
		user.setUser(username);
		user.setToken(token);

		return user;
	}
	*/

}
