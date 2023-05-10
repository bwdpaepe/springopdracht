package com.springBoot_opdracht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import domein.Guest;
import domein.Role;
import domein.User;
import repository.GuestRepository;
import repository.UserRepository;

@Component
public class InitDataConfig implements CommandLineRunner {

	@Autowired
	private UserRepository uRepository;
	
	@Autowired
	private GuestRepository repository;

	@Override
	public void run(String... args) {

		uRepository.save(new User("sandra.keters@spring.boot", "paswoord", Role.ADMIN));
		uRepository.save(new User("tania.blondeel@spring.boot", "paswoord", Role.USER));
		uRepository.save(new User("jurgen.blondeel@spring.boot", "paswoord", Role.USER));
		uRepository.save(new User("ann.blondeels@spring.boot", "paswoord", Role.USER));
		repository.save(new Guest("Keters", "Sandra"));
		repository.save(new Guest("Blondeel", "Tania"));
		repository.save(new Guest("Blondeel", "Jurgen"));
		repository.save(new Guest("Blondeels", "Ann"));
	}
	
	
}