package com.stayen.casa.bookingservice.exception;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stayen.casa.bookingservice.dto.APIResponse;


/**
 * Global exception handler for handling all types of exceptions
 * thrown from controllers or services in a centralized way.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

	/*
     * Handles Validation Errors triggered by @Valid annotation in request bodies
     * @param ex the exception thrown (ex: name of the method parameter)
     * @return the error response with Bad Request
     */
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		List<FieldError> fieldErrors = e.getFieldErrors();
		Map<String, String> map = fieldErrors.stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
	}
	
	/*
	 * Handles Custom Exceptions when the Booking Details are not found.
	 * @return error response with a Message. 
	 */
	
	@ExceptionHandler(BookingNotFoundException.class)
	public APIResponse handleBookingNotFoundException(BookingNotFoundException e)
	{
		return new APIResponse(e.getMessage());
	}
	
	/*
	 * Handles all Uncaught Exceptions
	 */
	
	@ExceptionHandler(RuntimeException.class)
	public APIResponse handleUncaughtException(RuntimeException e)
	{
		return new APIResponse(e.getMessage());
	}
}
