package com.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Greeting;

/**
 * Regular REST controller for API stuff..
 * @author dcore099
 */
@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	/**
	 * Just a regular controller
	 * @param	name	Receives the message to return along with some text.
	 * @return	a greeting object with the received parameter message.
	 */
	@GetMapping("/greetingss")
	public Greeting greetings(@RequestParam(value = "name", defaultValue = "World") String name) {
		System.out.println("Entering controller!");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	@GetMapping("/")
	public String greetings() {
		return "Helooooooooo!!!!";
	}
	
}
