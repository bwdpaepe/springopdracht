package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import domein.Book;
import domein.User;
import repository.UserRepository;

public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<Book> booksOfUser(long id) {
		return userRepository.booksOfUser(id);
	}

}
