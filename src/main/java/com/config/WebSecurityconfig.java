package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Clase para confurar la seguridad web de la aplicación
 * @author Administrador
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityconfig extends WebSecurityConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) {
		
	}
}
