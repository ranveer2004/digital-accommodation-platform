package com.stayen.casa.gatewayservice.enums;

import lombok.Getter;

@Getter
public enum ProfileError implements GenericError {
	
	NO_PROFILE_FOUND(1301, "We could not find your profile. Please try to log in and create a profile first."),

	NO_PROPERTY_FOUND(1303, "We were unable to find or delete a property corresponding to the provided propertyId. Kindly verify the details and try again.");

	/**
	 * 
	 */
	private int code;
	
	private String message;
    
    private ProfileError(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
