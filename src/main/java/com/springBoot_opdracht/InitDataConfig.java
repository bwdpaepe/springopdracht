package com.springBoot_opdracht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import domein.User;
import repository.UserRepository;

@Component
public class InitDataConfig implements CommandLineRunner {
	
	@Autowired 
	UserRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		repository.save(new User("sandra@example.com", passwordEncoder.encode("pass")));
		repository.save(new User("tania@example.com", passwordEncoder.encode("pass")));
		repository.save(new User("jurgen@example.com", passwordEncoder.encode("pass")));
		repository.save(new User("ann@example.com", passwordEncoder.encode("pass")));
		
	}

}
