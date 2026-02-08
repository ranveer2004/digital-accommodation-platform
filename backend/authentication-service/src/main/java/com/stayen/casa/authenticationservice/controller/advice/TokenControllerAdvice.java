package com.stayen.casa.authenticationservice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stayen.casa.authenticationservice.dto.ErrorResponseDTO;
import com.stayen.casa.authenticationservice.enums.TokenError;
import com.stayen.casa.authenticationservice.exception.TokenException;

@RestControllerAdvice
public class TokenControllerAdvice {
	
	@ExceptionHandler(TokenException.class)
	public ResponseEntity<?> handleTokenException(TokenException tokenException) {
		TokenError tokenError = tokenException.getTokenError();

		// TODO: Status Code changed from UNAUTHORIZED
		return ResponseEntity
				.status(HttpStatus.FORBIDDEN)  // here
				.body(new ErrorResponseDTO(tokenError));
	}
	
}
