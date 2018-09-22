package com.renu.look.house.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.renu.look.house.models.User;
import com.renu.look.house.repository.User_Repository;
import com.renu.look.house.web.service.SecurityService;
import com.renu.look.house.web.service.UserService;

@Controller
public class WebController {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);
	@Autowired
	private User_Repository user_Repository;
	@Autowired
	UserService userService;

	@Autowired
	SecurityService securityService;

	@RequestMapping(value = "/main")
	public String main() {
		return "sec-main";
	}

	@RequestMapping("/login")
	public String login(Model model, String error, String logout, HttpServletRequest request) {
		if (logout != null) {
			model.addAttribute("logout", "You have been logged out successfully.");
		}

		model.addAttribute("title", "Login");

		return "sec-login";
	}

	@RequestMapping(value = "/loginSuccess")
	public String loginSuccess(Model model, String username) {
		model.addAttribute("message", "Login has been completed successfully.");

		return "home";
	}

	@RequestMapping(value = "/loginError")
	public String loginError(Model model, String username) {
		model.addAttribute("error", "Your username and password is invalid.");
		model.addAttribute("title", "Login");

		return "sec-login";
	}

	@RequestMapping(value = "/aaslamaadminrregistration")
	public String adminRegistration(Model model) {
		model.addAttribute("userform", new User());
		model.addAttribute("title", "Registration");
		return "admin-registration";
	}

	
	@RequestMapping(value = "/adminregistration", method = {RequestMethod.POST,RequestMethod.GET})
	public String adminRegistration(@Valid @ModelAttribute("userform") User userform, BindingResult bindingResult,
			Model model, String[] roles) {
		LOGGER.info("From class : WebController, method : registration()");
		LOGGER.info("Getting password : " + userform.getPassword());
		LOGGER.info("Getting confirmPassword : " + userform.getConfirmPassword());

		if (bindingResult.hasErrors()) {
			model.addAttribute("message", "Your Registration has not been completed successfully !!!");
			return "admin-registration";
		}

		String password = userform.getPassword();
		if (!userform.getPassword().equals(userform.getConfirmPassword())) {
			model.addAttribute("message", "Your password and confirm password is not equal !!! ");
			return "admin-registration";
		}

		//can't retrieve values by digits
				User user1=user_Repository.getByUsername(userform.getUsername());
				
					if(user1!=null) {
	            	  model.addAttribute("message", "This email is already exist !!!");
	          		return "admin-registration";
	          				
				}
				else {
						
				userService.saveUser(userform, roles);
				securityService.autologin(userform.getUsername(), password);
				model.addAttribute("message", "Your Registration has been completed successfully !!!");
				return "admin-registration";
					}
				

	}

	@RequestMapping(value = "/showregistration")
	public String showRegistration(Model model) {
		LOGGER.info("From class : WebController, method :showRegistration()");
		model.addAttribute("userform", new User());
		model.addAttribute("title", "Registration");
		return "sec-registration";
	}

	@RequestMapping(value = "/registration",method= {RequestMethod.POST,RequestMethod.GET})
	public String registration(@Valid @ModelAttribute("userform") User userform
			, BindingResult bindingResult,Model model, String[] roles) {
		LOGGER.info("From class : WebController, method : registration()");
		LOGGER.info("Getting password : " + userform.getPassword());
		LOGGER.info("Getting confirmPassword : " + userform.getConfirmPassword());
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("message", "Your Registration has not been completed successfully !!!");
			return "sec-registration";
		}

		String password = userform.getPassword();
		if (!userform.getPassword().equals(userform.getConfirmPassword())) {
			model.addAttribute("message", "Your password and confirm password is not equal !!! ");
			return "sec-registration";
		}
		//can't retrieve values by digits
		User user1=user_Repository.getByUsername(userform.getUsername());
		
		if(user1!=null) {
        	  model.addAttribute("message", "This email is already exist !!!");
      		return "sec-registration";
      				
		}
		
		else {
				
		userService.saveUser(userform, roles);
		securityService.autologin(userform.getUsername(), password);
		model.addAttribute("message", "Your Registration has been completed successfully !!!");
		return "sec-registration";
		}
			
	}
	

	@RequestMapping("/admin")
	public String admin() {
		return "sec-admin";
	}

	@RequestMapping("/user")
	public String user() {
		return "sec-user";
	}

	@RequestMapping("/403")
	public String access() {
		return "sec-access";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		model.addAttribute("message", "Logout has been completed successfully");
		return "redirect:/login?logout";
	}

}
