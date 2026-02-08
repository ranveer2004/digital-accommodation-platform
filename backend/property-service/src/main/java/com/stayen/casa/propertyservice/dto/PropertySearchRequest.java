package com.stayen.casa.propertyservice.dto;

import java.math.BigDecimal;
import java.util.List;

import com.stayen.casa.propertyservice.customValidator.ValidRange;
import com.stayen.casa.propertyservice.enums.AreaUnit;
import com.stayen.casa.propertyservice.enums.Furnishing;
import com.stayen.casa.propertyservice.enums.ListingType;
import com.stayen.casa.propertyservice.enums.PropertyCategory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO to capture property search filters provided by users.
 * Allows filtering by location, price, area, bedrooms, and more.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ValidRange
public class PropertySearchRequest {

    // Basic Property Details

//    /**
//     * Name of the property to search for (partial match).
//     */
//    @Schema(description = "Property name (for partial match)")
//    private String propertyName;

    /**
     * Type of listing - RENT or SALE.
     */
    @Schema(description = "Listing type of the property")
    private ListingType listingType;

    /**
     * Furnishing type - FURNISHED, UNFURNISHED, etc.
     */
    @Schema(description = "Furnishing type")
    private Furnishing furnishing;

    /**
     * Property category like FLAT, VILLA, PLOT, etc.
     */
    @Schema(description = "Category of property")
    private PropertyCategory propertyCategory;


    // Price Range

    /**
     * Minimum price filter.
     */
    @Schema(description = "Minimum price (in INR)")
    @Min(value = 0, message = "Minimum Price must be Positive")
    private BigDecimal minPrice;

    /**
     * Maximum price filter.
     */
    @Schema(description = "Maximum price (in INR)")
    @Min(value = 0, message = "Maximum Price must be Positive")
    private BigDecimal maxPrice;


    // Area Filters

    /**
     * Minimum area filter.
     */
    @Schema(description = "Minimum area")
    @Positive(message = "Minimum Area must be Positive")
    private Double minArea;

    /**
     * Maximum area filter.
     */
    @Schema(description = "Maximum area")
    @Positive(message = "Maximum Area must be Positive")
    private Double maxArea;

    /**
     * Unit of area measurement (e.g., SQ_FT, SQ_M).
     */
    @Schema(description = "Unit for area measurement")
    private AreaUnit unit;


    // Bedrooms & Bathrooms

    /**
     * Number of bedrooms (minimum filter).
     */
    @Schema(description = "Minimum number of bedrooms")
    @Min(value = 0, message = "Number of Bedrooms must be Zero or more")
    private Integer bedrooms;

    /**
     * Number of bathrooms (minimum filter).
     */
    @Schema(description = "Minimum number of bathrooms")
    @Min(value = 0, message = "Number of Bathrooms must be Zero or more")
    private Integer bathrooms;


    // Other Property Attributes

    /**
     * List of required amenities (e.g., Parking, Gym).
     */
    @Schema(description = "Required amenities")
    private List<String> amenities;

    /**
     * Total floors in the building (minimum filter).
     */
    @Schema(description = "Minimum number of total floors in the building")
    @Min(value = 0, message = "Minimum Floors must be Zero or more")
    private Integer totalFloors;


    // Location Filters

    /**
     * City in which to search for property.
     */
    @Schema(description = "City filter")
    private String city;

    /**
     * State in which to search for property.
     */
    @Schema(description = "State filter")
    private String state;

    /**
     * Country in which the property is located (e.g., "India").
     */
    private String country;

    /**
     * Postal code or PIN code for the property's location.
     */
    private int pincode;

//    /**
//     * Locality or area name for more specific location filtering.
//     */
//    @Schema(description = "Locality filter")
//    private String locality;



}
