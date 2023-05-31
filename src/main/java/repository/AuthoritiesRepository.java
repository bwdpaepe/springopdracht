package repository;

import org.springframework.data.repository.CrudRepository;

import domein.Authorities;

public interface AuthoritiesRepository extends CrudRepository<Authorities, Long> {
	
	Authorities findByEmail(String email);

}
