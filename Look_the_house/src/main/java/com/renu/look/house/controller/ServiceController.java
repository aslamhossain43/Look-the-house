package com.renu.look.house.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.renu.look.house.models.AddService;
import com.renu.look.house.models.User;
import com.renu.look.house.repository.AddServiceRepository;
import com.renu.look.house.repository.User_Repository;
import com.renu.look.house.utility.FileUploadUtility;
import com.renu.look.house.validator.ImageFileValidator;
import com.renu.look.house.validator.VideoFileValidator;

@Controller
public class ServiceController {

	private static final Logger LOGGER=LoggerFactory.getLogger(ServiceController.class);
	@Autowired
	private AddServiceRepository addServiceRepository;
	@Autowired
	private User_Repository user_Repository;
	@RequestMapping(value="/showAddservice")
	public String showAddService(Model model) {
		LOGGER.info("From showService method");
		model.addAttribute("services", new AddService());
		model.addAttribute("title","AddServices");
		return "add-services";
	}
	
	
	@RequestMapping(value="/updateAddservice",method=RequestMethod.GET)
	public String showupdateAddService(@RequestParam("id") long id, Model model) {
		LOGGER.info("From showupdateAddService method");
		AddService addService=addServiceRepository.getOne(id);
		model.addAttribute("title","AddServices");
		model.addAttribute("services", addService);
		return "add-services";
	}
	
	@RequestMapping(value="/showUpdateWordByUser",method=RequestMethod.GET)
	public String showUpdateWordByUser(@RequestParam("id") long id, Model model) {
		LOGGER.info("From Class : ServiceController,method : showUpdateWordByUser()");
		LOGGER.info("Getting id : "+id);
		
		model.addAttribute("id",id);
		return "update-word";
	}
	
	@RequestMapping(value="/update-word")
	public String updateServiceByUser(@RequestParam("id") long id,@RequestParam("word") String word,
			Model model) {
		LOGGER.info("From Class : ServiceController,method : updateServiceByUser()");
		LOGGER.info("Getting id : "+id);
		LOGGER.info("Getting word : "+word);
		AddService serviceById=addServiceRepository.getOne(id);
		LOGGER.info("Getting Word by id : "+serviceById.getWord());
		
		if (word.equals(serviceById.getWord())) {
			model.addAttribute("services", serviceById);
			return "add-services";
		}else {
		model.addAttribute("message","Your word is wrong ! please enter a word which added during registration");	
		model.addAttribute("id",id);
		return "update-word";
		
		}
	}
	
	
	
	@RequestMapping(value="/addservices",method=RequestMethod.POST)
	public String addService(@Valid @ModelAttribute("services") AddService services,BindingResult bindingResult,
		    Model model,HttpServletRequest vRequest,HttpServletRequest iRequest) {
		LOGGER.info("From Class : ServiceController,method : addService()");
		LOGGER.info("From Class : ServiceController,method : addService(),,Getting id : "+services.getId());
		   if (services.getId()==null) {
			
			new VideoFileValidator().validate(services, bindingResult);
		    new ImageFileValidator().validate(services, bindingResult);
		   }
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("message", "Your operation has not been completed successfully !!!");
			return "add-services";
		}

		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=user_Repository.getByUsername(email);
		LOGGER.info("Getting email : "+email);
		if (!services.getWord().equals(user.getWord())) {
			model.addAttribute("message","Your characters or symbol not matching which you added during registration !");
		return "add-services";
		}
		if (!services.getvFile().getOriginalFilename().equals("")) {
			FileUploadUtility.videoUploadFile(vRequest, services.getvFile(), services.getvCode());
		}
		if (!services.getiFile().getOriginalFilename().equals("")) {
			FileUploadUtility.imageUploadFile(iRequest, services.getiFile(),services.getiCode());
		}
		if (services.getId()==null) {
			
			addServiceRepository.save(services);	
			services.setId(null);
		}else {
			addServiceRepository.save(services);
			
		}
		
		model.addAttribute("message", "Your operation has been completed successfully !!!");
		
		return "add-services";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
