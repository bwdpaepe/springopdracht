package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import domein.Author;
import domein.Book;
import domein.BookRest;
import domein.Location;
import domein.User;
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
	public List<BookRest> findByAuthor(String name) {
		Author author = this.findByName(name);
		if(author == null) {
			throw new AuthorNotFoundException(name);
		}
		List<Book> bookList = authorRepository.booksFromAuthor(author.getId());
		List<BookRest> restList = new ArrayList<>();
		bookList.forEach(book -> {
			List<Author> authorList = book.getAuthorList();
			List<Location> locationList = book.getLocationList();
			List<User> userList = book.getUserList();
			
			List<String> authors = authorList.stream().map(Author::getName).collect(Collectors.toList());
			List<String> locations = locationList.stream().map(Location::getName).collect(Collectors.toList());
			List<String> users = userList.stream().map(User::getEmail).collect(Collectors.toList());
			
			restList.add(new BookRest(book.getId(),book.getName(), book.getImage(),book.getIsbn(), book.getPrice(), authors, locations, users));
		});
		return restList;
	}

	

}
