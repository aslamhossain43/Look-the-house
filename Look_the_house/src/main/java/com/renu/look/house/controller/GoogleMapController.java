package com.renu.look.house.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.renu.look.house.models.AddService;
import com.renu.look.house.repository.AddServiceRepository;

@Controller
public class GoogleMapController {
	private static final Logger LOGGER=LoggerFactory.getLogger(GoogleMapController.class);
	@Autowired
	private AddServiceRepository addServiceRepository;
	
	@RequestMapping("/map")
	private String showMap(@RequestParam("id")long id,Model model) {
		LOGGER.info(" From class : GoogleMapController, Method : showMap");
		LOGGER.info("Getting id : "+id);
		AddService addService=addServiceRepository.getOne(id);
		LOGGER.info("Getting Country : "+addService.getCountry());
		LOGGER.info("Getting District : "+addService.getDistrict());
		LOGGER.info("Getting Sub-District : "+addService.getSubDistrict());
		model.addAttribute("mapData", addService);
		return "google-map";
	}
	
	
}
