package com.paypal.bfs.test.bookingserv.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.paypal.bfs.test.bookingserv.api.model.Booking;

public class RestExceptionHandler  extends ResponseEntityExceptionHandler
{
	@Value("${rest.api.err.msg}")
	private String errorMsg;
	
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<Booking> handleResourceNotFound(ResourceNotFoundException rnfe){
		logger.info("Inside handleResourceNotFound method..");
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
