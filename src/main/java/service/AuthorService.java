package service;

import java.util.List;

import domein.Author;
import domein.Book;
import domein.BookRest;


public interface AuthorService {

	public List<Author> findByBookId(Long Id); 
	public List<Author> findAll();
	public Author findByName(String name);
	public List<BookRest> findByAuthor(String name);
	
	
	 
}
