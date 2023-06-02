package service;

import domein.User;

public interface UserService {
	
	public User findByEmail(String email);

}
