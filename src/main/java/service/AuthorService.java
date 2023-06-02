package service;

import java.util.List;

import domein.Author;
import domein.Book;


public interface AuthorService {

	public List<Author> findByBookId(Long Id); 
	public List<Author> findAll();
	public Author findByName(String name);
	public List<Book> findByAuthor(String name);
	
	
	 
}
