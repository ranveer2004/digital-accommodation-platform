package com.stayen.casa.authenticationservice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stayen.casa.authenticationservice.dto.SimpleResponseDTO;

@RestControllerAdvice
public class CredentialControllerAdvice {
	
//	@ExceptionHandler(exception = NoActiveSessionFoundException.class)
//	public ResponseEntity<?> handleSessionNotFoundException(NoActiveSessionFoundException sessionNotFoundException) {
//		return ResponseEntity
//				.status(HttpStatus.UNAUTHORIZED)
//				.body(new SimpleResponseDTO(sessionNotFoundException.getMessage()));
//	}
//
//	@ExceptionHandler(exception = NoAccountFoundException.class)
//	public ResponseEntity<?> handleNoAccountFoundException(NoAccountFoundException noAccountFoundException) {
//		return ResponseEntity
//				.status(HttpStatus.BAD_REQUEST)
//				.body(new SimpleResponseDTO(noAccountFoundException.getMessage()));
//	}
//
//	@ExceptionHandler(exception = AccountAlreadyExistException.class)
//	public ResponseEntity<?> handleUserAlredyExistsException(AccountAlreadyExistException accountAlreadyExistException) {
//		return ResponseEntity
//				.status(HttpStatus.CONFLICT)
//				.body(new SimpleResponseDTO(accountAlreadyExistException.getMessage()));
//	}
//	
//	@ExceptionHandler(exception = InvalidCredentialException.class)
//	public ResponseEntity<?> handleInvalidCredentialException(InvalidCredentialException invalidCredentialException) {
//		return ResponseEntity
//				.status(HttpStatus.UNAUTHORIZED)
//				.body(new SimpleResponseDTO(invalidCredentialException.getMessage()));
//	}
	
}
