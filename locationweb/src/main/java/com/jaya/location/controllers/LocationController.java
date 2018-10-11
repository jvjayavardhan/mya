package com.jaya.location.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jaya.location.entities.Location;
import com.jaya.location.service.LocationService;

@Controller
public class LocationController {

	@Autowired
	LocationService service;
	
	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}
	
	@RequestMapping("/saveLoc")
	public String saveLocation( @ModelAttribute("location") Location location, ModelMap modelMap) {
		Location locationSaved = service.saveLocation(location);
		String msg = "Location saved with id:"+locationSaved.getId();
		modelMap.addAttribute("msg", msg);
		return "createLocation";
	}
	
	@RequestMapping("/displayLocations")
	public String displayLocation(ModelMap modelMap) {
		List<Location> allLocations = service.getAllLocations();
		System.out.println("gaurav -->"+allLocations.get(1).getId());
		modelMap.addAttribute("allLocations",allLocations);
		return "displayLocations";
		
	}
	
	@RequestMapping("/deleteLocation")
	public String deleteLocation( @RequestParam("id") int id,ModelMap modelMap) {
		Location l = new Location();
		l.setId(id);
		service.deleteLocation(l);
		List<Location> allLocations = service.getAllLocations();
		modelMap.addAttribute("allLocations",allLocations);
		return "displayLocations";
	}
	@RequestMapping("/showUpdate")
	public String showUpdate(@RequestParam("id") int id, ModelMap modelMap) {
		Optional<Location> location = service.getLocationById(id);
		modelMap.addAttribute("location", location.get());
		return "updateLocation";
	}
	
	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		
		service.updateLocation(location);
		List<Location> allLocations = service.getAllLocations();
		modelMap.addAttribute("allLocations",allLocations);
		return "displayLocations";
		
	}
}
