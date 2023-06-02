package service;

import java.util.List;

import domein.Author;
import domein.Book;
import domein.BookPopular;
import domein.BookRow;
import domein.Location;

public interface BookService {

	public List<Book> findAll();
	
	public Book findById(Long Id);
	
	public List<BookRow> allBookRows(); 
	
	public List<Author> findAuthorsById(Long Id);
	
	public List<Location> findLocationById(Long Id);
	
	public void save(Book book);
	
	public int getNumVotes(Long Id);
	
	public List<BookPopular> findPopularBooks();
	
	public Book findByISBN(long isbn);
	
	public Book createDummyBook();
}
