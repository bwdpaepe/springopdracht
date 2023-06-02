package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import domein.Author;
import domein.Book;
import domein.BookPopular;
import domein.Location;

public interface BookRepository extends CrudRepository<Book, Long> {
	Book findByName(String name);
	
	List<Author> authorsOfBook(@Param("Id") long Id);
	
	List<Location> locationsOfBook(@Param("Id") long Id);
	
	int votesOfBook(@Param("Id") long Id);
	
	List<BookPopular> popularBooks();
	
	Book findByIsbn(long isbn);

}
