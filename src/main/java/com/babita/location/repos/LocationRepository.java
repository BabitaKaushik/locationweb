package com.babita.location.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.babita.location.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
	
	@Query("Select type, Count(type) from Location group by type") 
	List<Object[]> findTypeAndTypeCount();

}
