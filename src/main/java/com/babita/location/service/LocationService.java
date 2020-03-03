package com.babita.location.service;

import java.util.List;
import java.util.Optional;

import com.babita.location.entities.Location;

public interface LocationService {

	Location saveLocation(Location location);

	Location updateLocation(Location location);

	void deleteLocation(Location location);

	Optional<Location> getLocationById(int id);

	List<Location> getAllLocations();

}
