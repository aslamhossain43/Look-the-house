package com.renu.s_vs_t.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(Exception.class)
	public String handleError(HttpServletRequest request, Exception e) {
		return "error";
	}

}