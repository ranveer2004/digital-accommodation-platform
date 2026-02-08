package com.stayen.casa.propertyservice.enums;

import lombok.Getter;

/**
 * Enum representing various error codes and messages related to property operations.
 * Used to standardize error handling and provide meaningful error responses.
 */

@Getter
public enum PropertyError implements GenericError {

	INVALID_PROPERTY_ID(1600, "No property found with the given owner Id. Please verify it once."),

	PROPERTY_ALREADY_EXIST(1601, "Property Already Listed, Please try Listing different Property"),

	NO_PROPERTY_FOUND(1602, "Not able to find any property of your matching right now. Please try searching after some time."),
	
	PROPERTY_CREATION_FAILED(1603, "Failed to create the property. Please try again."),
	
	PROPERTY_UPDATE_FAILED(1604, "Failed to update the property. Please check the input."),
	
	PROPERTY_DELETION_FAILED(1605, "Failed to delete the property. Please verify the Property ID."),
	
	PROPERTY_SEARCH_FAILED(1606, "Property search failed due to invalid filters or internal error."),
	
	TOO_MANY_IMAGES(1607,"You can upload a maximum of 3 images per property."),
	
	GENERIC_ERROR(1699,"An Unknown Error Occurred. Please try again later....");
	
	/**
     * Error code used to identify the type of error.
     */
	private int code;
	
	/**
     * Human-readable error message describing the issue.
     */
	private String message;

	private PropertyError(int code, String message) {
		this.code = code;
		this.message = message;
	}

}
