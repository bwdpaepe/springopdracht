package service;

import java.util.List;

import domein.Book;
import domein.BookRow;

public interface BookService {

	public List<Book> findAll();
	
	public Book findById(Long Id);
	
	public List<BookRow> allBookRows(); 
}
