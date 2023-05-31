package repository;

import org.springframework.data.repository.CrudRepository;

import domein.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
	Author findByName(String name);

}
