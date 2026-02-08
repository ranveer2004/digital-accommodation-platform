package com.stayen.casa.authenticationservice.enums;

import lombok.Getter;

@Getter
public enum AuthError implements GenericError {
	
	NO_ACCOUNT_FOUND(1201, "We could not find an account matching this email."),
	
	SESSION_NOT_FOUND(1202, "No active session found. Please log in to continue."),
	
	ACCOUNT_ALREADY_EXIST(1203, "Account already exists. Please try logging in or use a different email."),
	
	INVALID_CREDENTIAL(1204, "Login failed. The email or password is incorrect."),

	UID_EMAIL_NOT_MATCHING(1205, "Authentication  failed. Please ensure your user ID and email are correct."),

	OTP_VERIFICATION_FAILED(1206, "The OTP you entered is incorrect or has expired. Please try again or request a new one.");

	/**
	 * 
	 */
	private int code;
	
	private String message;
    
    private AuthError(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
