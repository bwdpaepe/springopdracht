package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import domein.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findByEmail(String email);

}
