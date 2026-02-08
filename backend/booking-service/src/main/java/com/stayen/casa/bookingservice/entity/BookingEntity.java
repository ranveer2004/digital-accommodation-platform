package com.stayen.casa.bookingservice.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
 * Represents a Booking made by a User for a Property.
 * Stores Booking Status, time-stamps and References to the Buyer and Property.
 */


@Document(collection = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {

	/*
     * Unique identifier for the booking.
     * Acts as the primary key in the bookings collection.
     */
	
	@Id
	private String bookingId;
	
	/*
	 * ID of the User(Buyer) who made the Booking 
	 * Should reference a valid user from the User Service.
	 */
	
	private String buyerId;
	
	/*
     * ID of the property that is being booked.
     * Should reference a valid property from the Property Service.
     */
	
	private String propertyId;
	
	/*
	 * Date on which the booking has been done. 
	 * Only the date is stored. No time component.
	 */
	
	private LocalDate bookingDate;
	
	/*
	 * Current Status of the Booking (e.g., APPROVED, PENDING, CANCELLED)
	 * Stored as an Enum string
	 */
	
	private BookingStatus status;
	
	/*
	 * Timestamp of when the Booking Document is Created
	 */
	
	private LocalDateTime createdAt;
	
	/*
	 * Timestamp of when the Booking Document is Last Updated   
	 */
	
	private LocalDateTime updatedAt;
}
