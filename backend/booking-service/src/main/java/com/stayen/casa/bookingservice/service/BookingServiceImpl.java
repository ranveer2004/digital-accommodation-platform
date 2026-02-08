package com.stayen.casa.bookingservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stayen.casa.bookingservice.dto.APIResponse;
import com.stayen.casa.bookingservice.dto.BookingRequest;
import com.stayen.casa.bookingservice.dto.BookingResponse;
import com.stayen.casa.bookingservice.entity.BookingEntity;
import com.stayen.casa.bookingservice.entity.BookingStatus;
import com.stayen.casa.bookingservice.exception.BookingNotFoundException;
import com.stayen.casa.bookingservice.repository.BookingRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
	
	
	private final BookingRepository bookingRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<BookingEntity> getAllBookings() {
		
		return bookingRepository.findAll();
	}
	
	@Override
	public APIResponse createBooking(BookingRequest bookingRequest) 
	{
		//Check if the property is already Booked
		Optional<BookingEntity> existingBooking = bookingRepository.findByPropertyIdAndStatus(bookingRequest.getPropertyId()
				,BookingStatus.CONFIRMED);
		if(existingBooking.isPresent())
			throw new IllegalStateException("Property Already Booked");
		
		//Map the request to entity
		BookingEntity bookingEntity = modelMapper.map(bookingRequest, BookingEntity.class);
		
		// Set default status as PENDING
	    bookingEntity.setStatus(BookingStatus.PENDING);
		
		//Set Additional Fields
		bookingEntity.setCreatedAt(LocalDateTime.now());
	    bookingEntity.setUpdatedAt(LocalDateTime.now());
		
		//Save to Database
		bookingRepository.save(bookingEntity);
		
		//Return the Booking Confirmation Message
		return new APIResponse("Booking Confirmed!!!! Your Booking ID: "+bookingEntity.getBookingId());
	}

	@Override
	public BookingResponse getBookingDetailsById(String bookingID) {
		
		BookingEntity bookingEntity = bookingRepository.findById(bookingID).orElseThrow(()->new BookingNotFoundException("Invalid Booking ID"));
		
		return modelMapper.map(bookingEntity, BookingResponse.class);
	}

	@Override
	public List<BookingResponse> getBookingsByBuyerId(String buyerID) {
		
		List<BookingEntity> bookingEntity = bookingRepository.findByBuyerId(buyerID);
		
		if(bookingEntity.isEmpty())
				throw new BookingNotFoundException("Invalid Buyer ID");
		
		return bookingEntity.stream().map(entity->modelMapper.map(entity,BookingResponse.class)).toList();
	}

	@Override
	public BookingResponse getBookingsByPropertyID(String propertyID) {
		
		BookingEntity bookingEntity = bookingRepository.findByPropertyId(propertyID)
				.orElseThrow(()->new BookingNotFoundException("Property Not Found or Invalid Property ID"));
		
		return modelMapper.map(bookingEntity, BookingResponse.class);
	}

	@Override
	public APIResponse updateBookingStatusByBookingId(String bookingId, BookingStatus status) {
		BookingEntity bookingEntity = bookingRepository.findById(bookingId).orElseThrow(()->new BookingNotFoundException("Invalid Booking ID"));
		BookingStatus currentStatus = bookingEntity.getStatus();
		
		if(!isValidStatusTransition(currentStatus,status))
			throw new IllegalArgumentException("Cannot Change Status from "+currentStatus+" to "+status);
		
		bookingEntity.setStatus(status);
		bookingEntity.setUpdatedAt(LocalDateTime.now());
		bookingRepository.save(bookingEntity);
		return new APIResponse("Status Updated Successfully!!!!!!!");
	}

	
	/**
	 * Validates allowed booking status transitions.
	 *
	 * Allowed transitions:
	 * PENDING -> CONFIRMED or CANCELLED
	 * CONFIRMED -> CANCELLED
	 * CANCELLED -> No transitions allowed
	 *
	 * @param current the current booking status
	 * @param next the intended new status
	 * @return true if transition is valid, false otherwise
	 */
	public static boolean isValidStatusTransition(BookingStatus currentStatus, BookingStatus status) {
		
		return switch (currentStatus) {
		case PENDING -> status == BookingStatus.CONFIRMED || status == BookingStatus.CANCELLED;
		case CONFIRMED -> status == BookingStatus.CANCELLED;
		case CANCELLED -> false;
		};
	}

}
