package repository;

import org.springframework.data.repository.CrudRepository;

import domein.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);

}
