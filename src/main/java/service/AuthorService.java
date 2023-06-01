package service;

import java.util.List;

import domein.Author;


public interface AuthorService {

	public List<Author> findByBookId(Long Id); 
	public List<Author> findAll();
	
	 
}
