package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import domein.Author;
import domein.Book;

public interface AuthorRepository extends CrudRepository<Author, Long> {
	Author findByName(String name);
	
	List<Book> booksFromAuthor(@Param("Id") long Id);
	
	
}
