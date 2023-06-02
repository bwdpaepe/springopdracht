package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import domein.Author;
import domein.Book;
import exceptions.AuthorNotFoundException;
import repository.AuthorRepository;

public class AuthorServiceImpl implements AuthorService {
	
	@Autowired 
	AuthorRepository authorRepository;
	
	@Override
	public List<Author> findByBookId(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Author> findAll() {
		return (List<Author>) authorRepository.findAll();
	}

	@Override
	public Author findByName(String name) {
		return authorRepository.findByName(name);
	}
	
	@Override
	public List<Book> findByAuthor(String name) {
		Author author = this.findByName(name);
		if(author == null) {
			throw new AuthorNotFoundException(name);
		}
		return authorRepository.booksFromAuthor(author.getId());
	}

	

}
