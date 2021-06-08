package com.example.demo;
/*
 * Anotación "ComponentScan" es requerida en spring boot o no encuentra el controlador en otro paquete
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.*")
public class DemoApplication {

	public static void main(String[] args) {
		//	yoooo yoooo
		//	This is and update test into both computers!!
		System.out.println("Some stuff...");
		SpringApplication.run(DemoApplication.class, args);
	}

}
