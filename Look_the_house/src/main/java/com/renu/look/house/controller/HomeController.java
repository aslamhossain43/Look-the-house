package com.renu.look.house.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private static final Logger LOGGER=LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value="/")
	public String home(Model model) {
		
		LOGGER.info("From home method");
		model.addAttribute("title","Home");
		return "home";
		
	}
	
	@RequestMapping(value="/about")
	public String about(Model model) {
		
		LOGGER.info("From about method");
		model.addAttribute("title","About");
		return "about";
		
	}
	
	
}
