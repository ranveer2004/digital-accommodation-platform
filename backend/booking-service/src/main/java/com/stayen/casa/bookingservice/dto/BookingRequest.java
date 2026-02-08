package com.stayen.casa.bookingservice.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * Data Transfer Object for creating a new Booking Request.
 */

@Getter
@Setter
@ToString
public class BookingRequest {

	/*
     * Unique identifier of the user(Buyer) who makes the booking.
     * Should refer to a valid user (Buyer) in the system.
     */
	@NotBlank(message = "Buyer ID must not be Blank")
	private String buyerId;
	
	/*
	 * Unique Identifier of the Property i.e. booked by a Buyer.
	 * Should refer to an available and active property.
	 */
	@NotBlank(message = "Property ID must not be Blank")
	private String propertyId;
	
	/*
	 * Booking Date must be today or a future date.
	 */
	@FutureOrPresent(message = "Booking Date must be Today or in the Future")
	private LocalDate bookingDate;
}
