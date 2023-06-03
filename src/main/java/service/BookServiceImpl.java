package service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import domein.Author;
import domein.Book;
import domein.BookRest;
import domein.BookPopular;
import domein.BookRow;
import domein.Location;
import domein.User;
import exceptions.BookNotFoundException;
import repository.BookRepository;

public class BookServiceImpl implements BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthorService authorService;
	
	@Override
	public List<Book> findAll() {
		return (List<Book>) bookRepository.findAll();
	}

	@Override
	public Book findById(Long Id) {
		Optional<Book> oBook = bookRepository.findById(Id);
		return oBook.get();
		
	}

	@Override
	public List<BookRow> allBookRows() {
		List<Book> catalog = this.findAll();
		List<BookRow> catalogBookRow = catalog.stream()
				                              .map(book -> new BookRow(book.getId(),
				                            		                   book.getName(), 
				                            		                   book.getImage(), 
				                            		                   !book.getAuthorList().isEmpty() && book.getAuthorList().get(0) != null ? book.getAuthorList().get(0).getName() : "", 
				                            		                   book.getAuthorList().size() > 1 && book.getAuthorList().get(1) != null ? book.getAuthorList().get(1).getName() : "", 
				                            		                   book.getAuthorList().size() > 2 && book.getAuthorList().get(2) != null ? book.getAuthorList().get(2).getName() : ""
				                            		                   )
				                            		).collect(Collectors.toList());
		return catalogBookRow;
	}

	@Override
	public List<Author> findAuthorsById(Long Id) {
		return bookRepository.authorsOfBook(Id);
	}

	@Override
	public List<Location> findLocationById(Long Id) {
		return bookRepository.locationsOfBook(Id);
	}

	@Override
	public void save(Book book) {
		bookRepository.save(book);
		
	}

	@Override
	public int getNumVotes(Long Id) {
		return bookRepository.votesOfBook(Id);
	}

	@Override
	public List<BookPopular> findPopularBooks() {
		return bookRepository.popularBooks();
	}

	@Override
	public BookRest findByISBN(long isbn) {
		Book book = bookRepository.findByIsbn(isbn);
		if(book == null) {
			throw new BookNotFoundException(isbn);
		}
		List<Author> authorList = book.getAuthorList();
		List<Location> locationList = book.getLocationList();
		List<User> userList = book.getUserList();
		
		List<String> authors = authorList.stream().map(Author::getName).collect(Collectors.toList());
		List<String> locations = locationList.stream().map(Location::getName).collect(Collectors.toList());
		List<String> users = userList.stream().map(User::getEmail).collect(Collectors.toList());
		
		BookRest bookRest = new BookRest(book.getId(),book.getName(), book.getImage(),book.getIsbn(), book.getPrice(), authors, locations, users);
		return bookRest;
	}

	@Override
	public Book createDummyBook() {
		Book aBook = new Book("Romeo and Juliet", "https://www.gutenberg.org/cache/epub/1513/pg1513.cover.medium.jpg", 9787698540261L);
		aBook.addAuthor(authorService.findByName("Shakespeare, William"));
		return aBook;
	}

}
