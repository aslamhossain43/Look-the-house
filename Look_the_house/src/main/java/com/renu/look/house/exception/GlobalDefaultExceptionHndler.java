package com.renu.look.house.exception;

import java.io.Serializable;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHndler extends Exception implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(Exception.class)
	public String requestRejectedException() {
		
		
		return "errors";
	}
	
	
	
	
	
	
	
	
}
