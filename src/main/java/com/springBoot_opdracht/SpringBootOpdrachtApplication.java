package com.springBoot_opdracht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import perform.PerformRestExample;
import service.AuthorService;
import service.AuthorServiceImpl;
import service.BookService;
import service.BookServiceImpl;
import service.LocationService;
import service.LocationServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import validator.BookFormLocation1Validation;
import validator.BookFormLocation2Validation;
import validator.BookFormLocation3Validation;
import validator.LocationValidation;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domein")
public class SpringBootOpdrachtApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOpdrachtApplication.class, args);
		
		// RUN WEB CLIENT REACTIVE SPRING
		try {
			new PerformRestExample();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/welcome");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	BookService bookService() {
		return new BookServiceImpl();
	}
	
	@Bean
	UserService userService() {
		return new UserServiceImpl();
	}
	
	@Bean
	AuthorService authorService() {
		return new AuthorServiceImpl();
	}
	
	@Bean
	LocationService locationService() {
		return new LocationServiceImpl();
	}
	
	@Bean
	LocationValidation locationValidation() {
		return new LocationValidation();
	}
	
	@Bean
	BookFormLocation1Validation bfl1Validation() {
		return new BookFormLocation1Validation();
	}
	
	@Bean
	BookFormLocation2Validation bfl2Validation() {
		return new BookFormLocation2Validation();
	}
	
	@Bean
	BookFormLocation3Validation bfl3Validation() {
		return new BookFormLocation3Validation();
	}

}
