package com.springBoot_opdracht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import domein.Authorities;
import domein.User;
import repository.AuthoritiesRepository;
import repository.UserRepository;

@Component
public class InitDataConfig implements CommandLineRunner {
	
	@Autowired 
	UserRepository uRepository;
	
	@Autowired
	AuthoritiesRepository aRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		uRepository.save(new User("sandra@example.com", passwordEncoder.encode("pass")));
		aRepository.save(new Authorities("sandra@example.com", uRepository.findByEmail("sandra@example.com")));
		uRepository.save(new User("tania@example.com", passwordEncoder.encode("pass")));
		aRepository.save(new Authorities("tania@example.com", uRepository.findByEmail("tania@example.com")));
		uRepository.save(new User("jurgen@example.com", passwordEncoder.encode("pass")));
		aRepository.save(new Authorities("jurgen@example.com", uRepository.findByEmail("jurgen@example.com")));
		uRepository.save(new User("ann@example.com", passwordEncoder.encode("pass")));
		aRepository.save(new Authorities("ann@example.com", uRepository.findByEmail("ann@example.com")));
		
	}

}
