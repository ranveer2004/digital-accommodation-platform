package com.stayen.casa.propertyservice.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.stayen.casa.propertyservice.enums.AreaUnit;
import com.stayen.casa.propertyservice.enums.Furnishing;
import com.stayen.casa.propertyservice.enums.ListingType;
import com.stayen.casa.propertyservice.enums.PropertyCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a real estate property listed for rent or sale.
 */
@Document(collection = "properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyEntity {
	
	/**
     * Unique identifier for the property.
     */
	@Id
	private String propertyId;
	
	/**
     * Name of the property listing.
     */
	private String propertyName;
	
	/**
     * Detailed description of the property.
     */
	private String propertyDescription;
	
	/**
     * Type of listing - Rent or Sale.
     */
	private ListingType listingType;
	
	/**
     * Category of the property - Flat, Villa, Plot, etc.
     */
	private PropertyCategory propertyCategory;
	
	 /**
     * Unique identifier of the owner/user who listed the property.
     */
	private String ownerId;
	
	/**
     * Price of the property (either rent or selling price).
     */
	private double price;
	
	/**
     * Total area of the property in square feet or meters.
     */
	private double area;
	
	/**
	 * Unit of Area of Property listed like Sq.Ft., Sq.Yard, Sq.m and so on.
	 */
	private AreaUnit unit;
	
	/**
     * Number of bedrooms in the property.
     */
	private int bedrooms;
	
	/**
     * Number of bathrooms in the property.
     */
	private int bathrooms;
	
	/**
     * The floor on which the property is located.
     */
	private int floorNumber;
	
	/**
     * Total number of floors in the building or Villa.
     */
	private int totalFloors;
	
	/**
     * Furnishing status - Furnished, Semi-Furnished, or UnFurnished.
     */
	private Furnishing furnishing;
	
	/**
     * List of amenities available in the property (e.g., lift, parking, gym).
     */
	private List<String> amenities;
	
	/**
     * Location details including coordinates and address.
     */
	private Location location;
	
	/**
     * List of image URLs representing the property.
     */
	private List<String> images;
	
	/**
     * Flag indicating if the property is currently available.
     */
	private boolean available;
	
	/**
     * Timestamp when the property was listed.
     */
	private LocalDateTime listedAt;
	
	/**
     * Timestamp when the property was last updated.
     */
	private LocalDateTime updatedAt;
	
	/**
     * Number of times the listing has been viewed.
     */
    private int viewCount;

}
