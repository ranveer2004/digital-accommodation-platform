package com.stayen.casa.bookingservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stayen.casa.bookingservice.dto.BookingRequest;
import com.stayen.casa.bookingservice.dto.UpdateStatusRequest;
import com.stayen.casa.bookingservice.entity.BookingStatus;
import com.stayen.casa.bookingservice.service.BookingService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

/*
 * BookingController handles all HTTP requests related to property bookings.
 */

@RestController
@RequestMapping("/api/bookings")
@AllArgsConstructor
public class BookingController {

	// Injecting the BookingService to handle business logic
	private final BookingService bookingService;
	
	/*
	 * Retrieves all Bookings (For Admin Login)
	 */
	@GetMapping
	public ResponseEntity<?> getAllBookings(){
		
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllBookings());
	}
	
	/*
	 * Creates a new booking for a given buyer and property.
	 *
	 * @param bookingRequest contains buyerId, propertyId, and bookingDate
	 * @return BookingResponse with booking details and generated ID
	 */
	@PostMapping
	public ResponseEntity<?> createBooking(@Valid @RequestBody BookingRequest bookingRequest) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(bookingRequest));
	}
	
	/*
	 * Fetches booking details by booking ID.
	 *
	 * @param bookingId the ID of the booking
	 * @return BookingResponse for the given ID
	 */
	@GetMapping("/{bookingId}")
	public ResponseEntity<?> getBookingDetailsById(@PathVariable String bookingId){
		
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookingDetailsById(bookingId));
	}
	
	
	/*
	 * Retrieves all bookings made by a specific buyer.
	 *
	 * @param buyerId the ID of the buyer
	 * @return list of BookingResponse for the given buyer
	 */
	@GetMapping("/buyer/{buyerId}")
	public ResponseEntity<?> getBookingDetailsByBuyerId(@PathVariable String buyerId){
		
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookingsByBuyerId(buyerId));
	}
	
	
	/*
	 * Retrieves all bookings for a specific property.
	 *
	 * @param propertyId the ID of the property
	 * @return list of BookingResponse for the given property
	 */
	@GetMapping("/property/{propertyId}")
	public ResponseEntity<?> getBookingDetailsByPropertyId(@PathVariable String propertyId){
		
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookingsByPropertyID(propertyId));
	}
	
	
	/*
	 * Updates the status of a booking.
	 *
	 * @param bookingId the ID of the booking to be updated
	 * @param status the new status to be set (CONFIRMED, CANCELLED)
	 * @return ResponseEntity with a success message
	 */
	@PatchMapping("/{bookingId}/status")
	public ResponseEntity<?> updateBookingStatusByBookingId(@PathVariable String bookingId,@Valid @RequestBody UpdateStatusRequest request)
	{
		BookingStatus newStatus = BookingStatus.valueOf(request.getStatus().toUpperCase());
		
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.updateBookingStatusByBookingId(bookingId, newStatus));
	}
}
