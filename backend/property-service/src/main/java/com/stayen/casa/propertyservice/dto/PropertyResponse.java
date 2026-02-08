package com.stayen.casa.propertyservice.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stayen.casa.propertyservice.enums.AreaUnit;
import com.stayen.casa.propertyservice.enums.Furnishing;
import com.stayen.casa.propertyservice.enums.ListingType;
import com.stayen.casa.propertyservice.enums.PropertyCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PropertyResponse DTO
 * 
 * Represents full property details sent to the client, including
 * owner, price, category, and location information.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyResponse {
	
	@Schema(description = "Unique identifier of the property")
	private String propertyId;
	
	@Schema(description = "Title or name of the property")
    private String propertyName;
    
	@Schema(description = "Detailed Description of the property")
    private String propertyDescription;
    
	@Schema(description = "Type of Listing of the property - RENT or SALE")
    private ListingType listingType;
    
	@Schema(description = "Category of the Property - PLOT, VILLA, FLAT or COMMERCIAL")
    private PropertyCategory propertyCategory;
    
	@Schema(description = "User ID of the Property owner")
    private String ownerId;
    
	@Schema(description = "Price of the Property")
    private Double price;
    
	@Schema(description = "Area of the Property")
    private Double area;
	
	@Schema(description = "Unit of Area of Property listed")
	private AreaUnit unit;
    
	@Schema(description = "No. of Bedrooms")
    private Integer bedrooms;
    
	@Schema(description = "No. of Bathrooms")
    private Integer bathrooms;
    
	@Schema(description = "Floor Number the Property is located on")
    private Integer floorNumber;
    
	@Schema(description = "Total Number of Floors")
    private Integer totalFloors;
    
	@Schema(description = "Furnishing Status")
    private Furnishing furnishing;
    
	@Schema(description = "List of Available Amenities")
    private List<String> amenities;
    
	@Schema(description = "Location of the Property")
    private LocationResponse location;
    
	@Schema(description = "Images of the Property")
    private List<String> images;
    
	@Schema(description = "Availability status of the Property")
	private boolean available;


	@Schema(description = "Timestamp when the Property was Listed")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime listedAt;
    
	@Schema(description = "Timestamp when the Property was last Updated")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
    
	@Schema(description = "Number of Views of the Property")
    private Integer viewCount;

}
