package service;

import java.util.List;

import domein.Author;
import domein.Book;
import domein.BookRow;
import domein.Location;

public interface BookService {

	public List<Book> findAll();
	
	public Book findById(Long Id);
	
	public List<BookRow> allBookRows(); 
	
	public List<Author> findAuthorsById(Long Id);
	
	public List<Location> findLocationById(Long Id);
}
