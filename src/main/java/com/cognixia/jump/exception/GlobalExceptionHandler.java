package com.cognixia.jump.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		
		ErrorDetails errorDetails = new ErrorDetails(
				new Date(), // makes a timestamp at current system time
				ex.getResourceMessage(), // message is already formatted in the custom exception
				request.getDescription(false));  // info on client that sent request
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalException(Exception ex, WebRequest request) {
		
		ErrorDetails errorDetails = new ErrorDetails(
				new Date(), // makes a timestamp at current system time
				ex.getMessage(), // message is already formatted in the custom exception
				request.getDescription(false));  // info on client that sent request
		
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
