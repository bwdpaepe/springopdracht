package service;

import java.util.List;

import domein.Location;

public interface LocationService {

	public List<Location> findByBookId(Long Id); 
	
	public Location findByName(String name);
	
	public void save(Location location);
}

