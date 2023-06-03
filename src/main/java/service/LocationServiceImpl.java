package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import domein.Location;
import repository.LocationRepository;

public class LocationServiceImpl implements LocationService {
	
	@Autowired
	LocationRepository locationRepository;

	@Override
	public List<Location> findByBookId(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Location location) {
		locationRepository.save(location);
		
	}

	@Override
	public Location findByName(String name) {
		
		System.out.println(locationRepository);
		System.out.println(name);
		
		return locationRepository.findByName(name);
	}

}
