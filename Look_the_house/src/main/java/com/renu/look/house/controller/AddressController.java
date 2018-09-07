package com.renu.look.house.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.renu.look.house.models.AddService;
import com.renu.look.house.repository.AddServiceRepository;


@ControllerAdvice
public class AddressController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);

	@Autowired
	AddServiceRepository addServiceRepository;

	@ModelAttribute("servicestable")
	public List<AddService> getAllServices() {
		LOGGER.info("From class : AddressController,method :getAllServices()");
		return addServiceRepository.getServicesTable();

	}
	
	
	
	
	
	@ModelAttribute("uniquecountry")
	public Set<String> getAllUniqueCountries() {
		LOGGER.info("From class : AddressController,method : getAllUniqueCountries()");
		Set<String>uniquecountry=new HashSet<>();
		
		
		for(AddService addService:addServiceRepository.getServicesTable()) {
			uniquecountry.add(addService.getCountry());
			
		}
		
		return uniquecountry;

	}
	
	
	
	

}
