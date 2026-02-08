package com.stayen.casa.propertyservice.exception;

import java.util.Map;

import com.stayen.casa.propertyservice.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stayen.casa.propertyservice.enums.PropertyError;

import lombok.extern.slf4j.Slf4j;

/**
 * Global exception handler for handling all types of exceptions
 * thrown from controllers or services in a centralized way.
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	/**
     * Handles custom PropertyException and maps it to a meaningful response.
     *
     * @param ex the PropertyException thrown
     * @return ResponseEntity with error message and BAD_REQUEST status
     */
	@ExceptionHandler(PropertyException.class)
	public ResponseEntity<?> handlePropertyException(PropertyException ex)
	{
		log.error("Property error occurred: [{}] - {}", ex.getGenericError().getCode(), ex.getGenericError().getMessage());

		HttpStatus status = switch(ex.getGenericError().getCode()) {
			case 1600, 1602, 1606 -> HttpStatus.NOT_FOUND;
			case 1601, 1603, 1604, 1605 -> HttpStatus.CONFLICT;
			default -> HttpStatus.NOT_FOUND;
		};

		return ResponseEntity
				.status(status)
//				.body(Map.of("status","error","message",error.getMessage()));
				.body(new ErrorResponseDTO(ex.getGenericError()));
	}
	
	
	/**
     * Handles any uncaught generic exceptions.
     *
     * @param ex the exception thrown
     * @return ResponseEntity with generic error message and INTERNAL_SERVER_ERROR status
     */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGenericException(Exception ex)
	{

		log.error("Unhandled Exception occurred: {}", ex.getMessage(),ex);
		
		PropertyError error = PropertyError.GENERIC_ERROR;
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("status","error","message",error.getMessage()));
		
	}

}
