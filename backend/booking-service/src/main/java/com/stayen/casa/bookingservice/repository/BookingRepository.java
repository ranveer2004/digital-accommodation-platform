package com.stayen.casa.bookingservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stayen.casa.bookingservice.entity.BookingEntity;
import com.stayen.casa.bookingservice.entity.BookingStatus;

/*
 * Repository interface for BookingEntity.
 * Provides access to MongoDB methods for Booking Collection
 */

@Repository
public interface BookingRepository  extends MongoRepository<BookingEntity, String>{

	/*
	 * Get all Bookings by a Buyer
	 */
	
	List<BookingEntity> findByBuyerId(String buyerId);
	
	/*
	 * Filter Bookings by Status
	 */
	
	List<BookingEntity> findByStatus(BookingStatus status);
	
	/*
	 * Get the Booking details of Property
	 */
	
	Optional<BookingEntity> findByPropertyId(String propertyId);

	/*
	 * Get the Booking Details by Property ID and Status
	 */
	
	Optional<BookingEntity> findByPropertyIdAndStatus(String propertyId, BookingStatus status);
}
