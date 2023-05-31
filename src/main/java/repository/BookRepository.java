package repository;

import org.springframework.data.repository.CrudRepository;

import domein.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	Book findByName(String name);

}
