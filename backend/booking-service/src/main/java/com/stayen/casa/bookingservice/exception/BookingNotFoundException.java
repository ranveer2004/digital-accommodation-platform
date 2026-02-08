package com.stayen.casa.bookingservice.exception;

/**
 * Custom exception thrown when a Booking Details are not found in the system.
 */

public class BookingNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BookingNotFoundException(String message)
	{
		super(message);
	}

}
