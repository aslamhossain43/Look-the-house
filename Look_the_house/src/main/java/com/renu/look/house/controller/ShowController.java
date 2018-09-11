package com.renu.look.house.controller;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.renu.look.house.repository.AddServiceRepository;

@Controller
public class ShowController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ShowController.class);
	@Autowired
	private AddServiceRepository addServiceRepository;
	public static String categories = "";
	public static String category = "";
	public static String country = "";

	@RequestMapping(value = "/view-services")
	public String viewServices(Model model) {
		LOGGER.info("From class:ShowController,,method : viewServices");
		model.addAttribute("heading", "Available Services");
		model.addAttribute("title", "View-Services");
		model.addAttribute("jsonurl", "/viewServices");
        Collection<SimpleGrantedAuthority>authorities=(Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		String role=authorities.toString().replace("[","").replace("]","") ;
		LOGGER.info("From class:ShowController,,method : viewServices(), authority : "+role);
		model.addAttribute("role",role );
		return "view-services";
	}
	
	
	@RequestMapping(value = "/view-by-categories")
	public String viewByCategory(@RequestParam("rentTypes") String category, Model model) {
		LOGGER.info("From class:ShowController,,method : viewByCategory");
		LOGGER.info("get by @RequestParam : " + categories);
		model.addAttribute("heading", "Available Services");
		model.addAttribute("title", "Viewservices");
		categories = category;
		addServiceRepository.findByCategory(categories);
		model.addAttribute("jsonurl", "/json-category");
		 Collection<SimpleGrantedAuthority>authorities=(Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			
			String role=authorities.toString().replace("[","").replace("]","") ;
			LOGGER.info("From class:ShowController,,method : viewServices(), authority : "+role);
			model.addAttribute("role",role );
			return "view-services";
	}

	@RequestMapping(value = "/view-by-countries")

	public String viewByCountry(@RequestParam("country") String country, Model model) {
		LOGGER.info("From class:ShowController,,method : viewByCountry");
		LOGGER.info("get by @RequestParam : " + country);
		model.addAttribute("heading", "Available Services");
		model.addAttribute("title", "Viewservices");
		
		model.addAttribute("databycountry", addServiceRepository.findByCountry(country));

		return "view-by-country";
	}

	@RequestMapping(value = "/view-by-categories-countries")
	public String viewByCountryCategory(@RequestParam Map<String, String> requesrParam, Model model) {
		LOGGER.info("From class:ShowController,,method : viewByCountryCategory");
		category = requesrParam.get("rentTypes");
		country = requesrParam.get("countries");
		LOGGER.info("get by @RequestParam : " + category + " ," + country);
		model.addAttribute("heading", "Available Services");
		model.addAttribute("title", "Viewservices");
		addServiceRepository.findByCountryCategory(category, country);
		model.addAttribute("jsonurl", "/json-country-category");
		 Collection<SimpleGrantedAuthority>authorities=(Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			
			String role=authorities.toString().replace("[","").replace("]","") ;
			LOGGER.info("From class:ShowController,,method : viewServices(), authority : "+role);
			model.addAttribute("role",role );
			return "view-services";
	}
}
