package com.stayen.casa.propertyservice.dto;

import java.util.List;

import com.stayen.casa.propertyservice.enums.AreaUnit;
import com.stayen.casa.propertyservice.enums.Furnishing;
import com.stayen.casa.propertyservice.enums.ListingType;
import com.stayen.casa.propertyservice.enums.PropertyCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for updating property details.
 * Fields can be partially or fully updated based on client input.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePropertyRequest {

    @Schema(description = "Updated property name")
    private String propertyName;

    @Schema(description = "Updated property description")
    private String propertyDescription;

    @Schema(description = "Listing type of the property (e.g., RENT, SALE)")
    private ListingType listingType;

    @Schema(description = "Category of the property (e.g., FLAT, VILLA, PLOT)")
    private PropertyCategory propertyCategory;

    @Schema(description = "Updated price of the property")
    private Double price;

    @Schema(description = "Updated area of the property")
    private Double area;

    @Schema(description = "Unit of area measurement")
    private AreaUnit unit;

    @Schema(description = "Updated number of bedrooms")
    private Integer bedrooms;

    @Schema(description = "Updated number of bathrooms")
    private Integer bathrooms;

    @Schema(description = "Floor number of the property")
    private Integer floorNumber;

    @Schema(description = "Total number of floors in the building")
    private Integer totalFloors;

    @Schema(description = "Furnishing status of the property")
    private Furnishing furnishing;

    @Schema(description = "List of amenities provided in the property")
    private List<String> amenities;

    @Schema(description = "Updated location details of the property")
    private LocationUpdateRequest location;

    @Schema(description = "List of image URLs for the property")
    private List<String> images;

    @Schema(description = "Availability status of the property")
    private Boolean isAvailable;
}
