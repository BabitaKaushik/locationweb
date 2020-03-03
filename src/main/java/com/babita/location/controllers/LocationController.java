package com.babita.location.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.babita.location.entities.Location;
import com.babita.location.repos.LocationRepository;
import com.babita.location.service.LocationService;
import com.babita.location.util.EmailUtil;
import com.babita.location.util.ReportUtil;

@Controller
public class LocationController {

	@Autowired
	private LocationService locationService;
	@Autowired
	private LocationRepository locationRepo;
	@Autowired
	private ReportUtil reportUtil;

	@Autowired
	private EmailUtil emailUtil;
	
	@Autowired
	ServletContext servletContext;

	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}

	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		Location locationSaved = locationService.saveLocation(location);
		String message = "Location saved with ID: " + locationSaved.getId();
		modelMap.addAttribute("msg", message);

		emailUtil.sendEmail("babitaavkaushik@gmail.com", "Location Saved", "Location saved Successfully");

		return "createLocation";
	}

	@RequestMapping("/displayLocations")
	public String displayLocations(ModelMap modelMap) {
		List<Location> locations = locationService.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}

	@RequestMapping("/deleteLocation")
	public String deleteLocation(@RequestParam("id") int id, ModelMap modelMap) {
		/*
		 * Optional<Location> optionalLocation = locationService.getLocationById(id);
		 * 
		 * if (optionalLocation.isPresent()) { Location location =
		 * optionalLocation.get(); locationService.deleteLocation(location); }
		 */

		Location location = new Location();
		location.setId(id);
		locationService.deleteLocation(location);
		List<Location> locations = locationService.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}

	@RequestMapping("/showUpdate")
	public String showUpdate(@RequestParam("id") int id, ModelMap modelMap) {

		Optional<Location> optionalLocation = locationService.getLocationById(id);
		Location location = new Location();
		if (optionalLocation.isPresent()) {
			location = optionalLocation.get();
		}
		modelMap.addAttribute("location", location);

		return "updateLocation";
	}

	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {

		locationService.updateLocation(location);

		List<Location> locations = locationService.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}
	
	@RequestMapping("/generateReport")
	public String generateReport() {
		List<Object[]> data = locationRepo.findTypeAndTypeCount();
		String path = servletContext.getRealPath("/");
		reportUtil.generatePieChart("", data);
		
		return "report";
	}

}
