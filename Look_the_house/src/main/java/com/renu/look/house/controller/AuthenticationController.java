package com.renu.look.house.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AuthenticationController {

	
	@ModelAttribute("authorityname")
	public String getEmail() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		return email;
		
	}
	
	
	
	
	
	
	
	
}
