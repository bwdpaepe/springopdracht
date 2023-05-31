package service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import domein.Book;
import domein.BookRow;
import repository.BookRepository;

public class BookServiceImpl implements BookService {
	
	@Autowired
	BookRepository bookRepository;

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

}
