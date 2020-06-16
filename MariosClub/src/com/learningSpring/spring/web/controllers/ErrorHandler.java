package com.learningSpring.spring.web.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(DataAccessException.class)
    public String handleDatabseException(DataAccessException ex)
    {
	System.out.println(ex.getMessage());
		return "error";
    }
	
	@ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex)
    {
		System.out.println(ex.getMessage());
		return "denied";
    }
	
}
