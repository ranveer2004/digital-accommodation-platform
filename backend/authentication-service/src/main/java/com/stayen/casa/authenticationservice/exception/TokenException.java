package com.stayen.casa.authenticationservice.exception;

import com.stayen.casa.authenticationservice.enums.TokenError;

import lombok.Getter;

@Getter
public class TokenException extends RuntimeException {

	private TokenError tokenError;
	
	public TokenException(TokenError tokenError) {
		super(tokenError.getMessage());
		this.tokenError = tokenError;
	}
	
}
