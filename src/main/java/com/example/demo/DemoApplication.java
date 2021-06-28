package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * This is our main application class. Nothing fancy here, just the class
 * initializer for Spring boot.
 * 
 * @author Administrador
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.*")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
