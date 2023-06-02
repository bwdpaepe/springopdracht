package service;

import java.util.List;

import domein.Book;
import domein.User;

public interface UserService {
	
	public User findByEmail(String email);
	
	public List<Book> booksOfUser(long id);

}
