package com.stayen.casa.authenticationservice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stayen.casa.authenticationservice.dto.ErrorResponseDTO;
import com.stayen.casa.authenticationservice.enums.AuthError;
import com.stayen.casa.authenticationservice.exception.AuthException;

@RestControllerAdvice
public class AuthControllerAdvice {
	
	@ExceptionHandler(AuthException.class)
	public ResponseEntity<?> handleAuthException(AuthException authException) {
		AuthError authError = authException.getAuthError();

		/**
		 *  NO_ACCOUNT_FOUND(1201, "We could not find an account matching this email."),
		 * 	SESSION_NOT_FOUND(1202, "No active session found. Please log in to continue."),
		 * 	ACCOUNT_ALREADY_EXIST(1203, "Account already exists. Please try logging in or use a different email."),
		 * 	INVALID_CREDENTIAL(1204, "Login failed. The email or password is incorrect."),
		 * 	UID_EMAIL_NOT_MATCHING(1205, "Authentication  failed. Please ensure your user ID and email are correct.");
		 */

		// TODO: Status Code changed from UNAUTHORIZED
		HttpStatus status;
		switch(authError.getCode()) {
			case 1201:
				status = HttpStatus.NOT_FOUND;
				break;
			case 1202, 1204, 1205, 1206:
				status = HttpStatus.FORBIDDEN;  // here
				break;
			case 1203:
				status = HttpStatus.CONFLICT;
				break;
			default:
				status = HttpStatus.BAD_REQUEST;
		}
		
		return ResponseEntity
				.status(status)
				.body(new ErrorResponseDTO(authError));
	}
	
}
