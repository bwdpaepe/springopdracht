package service;

import org.springframework.beans.factory.annotation.Autowired;

import domein.User;
import repository.UserRepository;

public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
