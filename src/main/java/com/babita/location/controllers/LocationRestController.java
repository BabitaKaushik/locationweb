package com.babita.location.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.babita.location.entities.Location;
import com.babita.location.repos.LocationRepository;

@RestController
@RequestMapping("/locations")
public class LocationRestController {

	@Autowired
	LocationRepository locationRepository;

	@GetMapping
	public List<Location> getLocations() {

		List<Location> findAll = locationRepository.findAll();
		return findAll;

	}

	@PostMapping()
	public Location saveLocation(@RequestBody Location location) {
		Location location2 = locationRepository.save(location);

		return location2;
	}

	@PutMapping	
	public Location updateLocation(@RequestBody Location location) {
		Location location2 = locationRepository.save(location);
		return location;
	}
	
	@DeleteMapping
	public void deleteLocation(@RequestBody Location location) {
		locationRepository.delete(location);
	}

	@DeleteMapping("/{id}")
	public void deleteLocationById(@PathVariable("id") int id) {
		locationRepository.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Location getById(@PathVariable("id") int id) {
		 Optional<Location> locations = locationRepository.findById(id);
		 Location location = null;
		 if(locations.isPresent()) {
			  location = locations.get();
		 }
		return location;
	}

}
