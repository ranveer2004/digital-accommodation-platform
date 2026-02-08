package com.stayen.casa.authenticationservice.exception;

import com.stayen.casa.authenticationservice.enums.AuthError;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

	private AuthError authError;
	
	public AuthException(AuthError authError) {
		super(authError.getMessage());
		this.authError = authError;
	}
	
}
