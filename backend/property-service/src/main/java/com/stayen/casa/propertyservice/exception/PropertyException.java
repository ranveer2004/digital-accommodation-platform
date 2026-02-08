package com.stayen.casa.propertyservice.exception;

import com.stayen.casa.propertyservice.enums.GenericError;
import com.stayen.casa.propertyservice.enums.PropertyError;

import lombok.Getter;

/**
 * Custom exception used to handle property-related errors in a standardized way.
 * Wraps a {@link PropertyError} enum for consistent error codes and messages.
 */

@Getter
public class PropertyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
     * The specific property error associated with this exception.
     */
	private GenericError genericError;
	

	/**
     * Constructs a new PropertyException with the specified {@link PropertyError}.
     *
     * @param genericError the specific error to associate with this exception
     */
	public PropertyException(GenericError genericError) {
		super(genericError.getMessage());
		this.genericError = genericError;
	}
}
