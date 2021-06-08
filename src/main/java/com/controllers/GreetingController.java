package com.controllers;
/*
 * Regular controller for stuff..
 */

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Greeting;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greetingss")
	public Greeting greetings(@RequestParam(value = "name", defaultValue = "World") String name) {
		System.out.println("Entering controller!");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
