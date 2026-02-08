package com.stayen.casa.propertyservice.dto;

import java.util.List;

import com.stayen.casa.propertyservice.customValidator.ValidPropertyDetails;
import com.stayen.casa.propertyservice.enums.AreaUnit;
import com.stayen.casa.propertyservice.enums.Furnishing;
import com.stayen.casa.propertyservice.enums.ListingType;
import com.stayen.casa.propertyservice.enums.PropertyCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO used to create or update property listings.
 */

@ValidPropertyDetails
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyRequest {

	/**
     * Name of the property listing.
     */
	@Schema(description = "Title or name of the property")
	@NotBlank(message = "Property Name is Required")
	private String propertyName;
	
	/**
     * Detailed description of the property.
     */
	@Schema(description = "Detailed Description of the property")
	@NotBlank(message = "Property Description is Required")
	private String propertyDescription;
	
	/**
     * Type of listing - Rent or Sale.
     */
	@Schema(description = "Type of Listing of the property - RENT or SALE")
	@NotNull(message = "Listing Type is Required")
	private ListingType listingType;
	
	/**
     * Category of the property - Flat, Villa, Plot, etc.
     */
	@Schema(description = "Category of the Property - PLOT, VILLA, FLAT or COMMERCIAL")
	@NotNull(message = "Property Category is Required")
	private PropertyCategory propertyCategory;
	
	/**
     * Price of the property (either rent or selling price) and must be greater than 0.
     */
	@Schema(description = "Price of the Property")
	@NotNull(message = "Price is Required")
	@Positive(message = "Price must be Greater than 0")
	private Double price;
	
	/**
     * Total area of the property in square feet or meters and must be greater than 0.
     */
	@Schema(description = "Area of the Property")
	@NotNull(message = "Area is Required")
	@Positive(message = "Area must be a Positve Number")
	private Double area;


	// NOTE ::
	// by default property area will be in Sq.Feet
	// Conversion from Sq.Feet to other unit will be done on UI

//	/**
//	 * Unit of Area of Property listed like Sq.Ft., Sq.Yard, Sq.m and so on.
//	 */
//	@Schema(description = "Unit of Area of Property listed")
//	@NotNull(message = "Area Unit is Required")
//	private AreaUnit unit;
	
	/**
     * Number of bedrooms in the property.
     */
	@Schema(description = "No. of Bedrooms")
	private Integer bedrooms;
	
	/**
     * Number of bathrooms in the property.
     */
	@Schema(description = "No. of Bathrooms")
	private Integer bathrooms;
	
	/**
     * The floor on which the property is located.
     */
	@Schema(description = "Floor Number the Property is located on")
	private Integer floorNumber;
	
	/**
     * Total number of floors in the building or Villa.
     */
	@Schema(description = "Total Number of Floors")
	private Integer totalFloors;
	
	/**
     * Furnishing status - Furnished, Semi-Furnished, or UnFurnished.
     */
	@Schema(description = "Furnishing Status")
	private Furnishing furnishing;
	
	/**
     * List of amenities available in the property (e.g., lift, parking, gym).
     */
	@Schema(description = "List of Available Amenities")
	private List<String> amenities;
	
	/**
     * Location details including coordinates and address.
     */
	@Schema(description = "Location of the Property")
	@NotNull(message = "Location is Required")
	@Valid
	private LocationRequest location;
	
	/**
     * List of image URLs representing the property.
     */
	@Schema(description = "Images of the Property")
	@NotNull(message = "Image is Required")
	private List<@NotNull(message = "Image URL is Required") String> images;
	
	/**
     * Flag indicating if the property is currently available.
     */
	@Schema(description = "Availability status of the Property")
	@NotNull(message = "Specification of Availability Status is must")
	private boolean available;
}
