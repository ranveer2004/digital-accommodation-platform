package com.stayen.casa.bookingservice.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stayen.casa.bookingservice.entity.BookingStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * Data Transfer Object representing the response after a booking is created or retrieved.
 * Contains all relevant details to be sent to the client side.
 */

@Getter
@Setter
@ToString
public class BookingResponse {

	 	@Schema(description = "Unique ID of the booking")
	    private String bookingId;

	    @Schema(description = "User ID of the buyer who made the booking")
	    private String buyerId;

	    @Schema(description = "Property ID associated with the booking")
	    private String propertyId;

	    @Schema(description = "Date when the booking was made", example = "2025-06-12")
	    @JsonFormat(pattern = "yyyy-MM-dd")
	    private LocalDate bookingDate;

	    @Schema(description = "Current status of the booking", example = "PENDING")
	    private BookingStatus status;

	    @Schema(description = "Timestamp when the booking was created", example = "2025-06-12T10:15:30")
	    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	    private LocalDateTime createdAt;

	    @Schema(description = "Timestamp when the booking was last updated", example = "2025-06-12T11:45:00")
	    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	    private LocalDateTime updatedAt;
}
