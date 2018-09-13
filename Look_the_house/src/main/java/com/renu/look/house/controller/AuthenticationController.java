package com.renu.look.house.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.renu.look.house.models.User;
import com.renu.look.house.repository.User_Repository;

@ControllerAdvice
public class AuthenticationController {
 @Autowired
 private User_Repository user_Repository;
	
	@ModelAttribute("authorityObject")
	public User getEmail() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		User user=user_Repository.getByUsername(email);
		
		return user;
		
	}
	
	
	
	
	
	
	
	
}
