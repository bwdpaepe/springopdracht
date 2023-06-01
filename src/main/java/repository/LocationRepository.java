package repository;

import org.springframework.data.repository.CrudRepository;

import domein.Location;

public interface LocationRepository extends CrudRepository<Location, Long> {
	Location findByName(String name);
}
