package com.stayen.casa.bookingservice.service;

import java.util.List;

import com.stayen.casa.bookingservice.dto.APIResponse;
import com.stayen.casa.bookingservice.dto.BookingRequest;
import com.stayen.casa.bookingservice.dto.BookingResponse;
import com.stayen.casa.bookingservice.entity.BookingEntity;
import com.stayen.casa.bookingservice.entity.BookingStatus;

/**
 * Service interface for handling booking-related business logic.
 * Provides methods for creating, retrieving, and managing bookings.
 */

public interface BookingService {

	
	/*
	 * Get the List of all Bookings (For Admin)
	 */
	List<BookingEntity> getAllBookings();
	
	
	/**
     * Creates a new booking for a given buyer and property.
     *
     * @param bookingRequest DTO containing buyerId, propertyId, and bookingDate
     * @return BookingResponse containing booking confirmation details
     */
	APIResponse createBooking(BookingRequest bookingRequest);
	
	
	/**
     * Retrieves booking details by booking ID.
     *
     * @param bookingId the unique identifier of the booking
     * @return BookingResponse containing booking information
     */
	BookingResponse getBookingDetailsById(String bookingID);
	
	
	/**
     * Retrieves a list of bookings for a specific buyer.
     *
     * @param buyerId the ID of the buyer
     * @return List of BookingResponse objects for the buyer
     */
	List<BookingResponse> getBookingsByBuyerId(String buyerID);
	
	
	 /**
     * Retrieves all bookings associated with a specific property.
     *
     * @param propertyId the ID of the property
     * @return List of BookingResponse objects for the property
     */
	BookingResponse getBookingsByPropertyID(String propertyID);
	
	
	/**
     * Updates the booking status (e.g., PENDING -> CONFIRMED or CANCELLED).
     *
     * @param bookingId the ID of the booking to be updated
     * @param newStatus the new status to apply
     */
	APIResponse updateBookingStatusByBookingId(String bookingId, BookingStatus status);

}
