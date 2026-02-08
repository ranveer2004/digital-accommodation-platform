package com.stayen.casa.propertyservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for updating the location details of a property.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationUpdateRequest {

	/**
     * Latitude of the property location.
     */
	@Schema(description = "Latitude of the property location")
	private Double latitude;
	
	/**
     * Longitude of the property location.
     */
	@Schema(description = "Longitude of the property location")
	private Double longitude;
	
	/**
     * Full address (street + number) of the property.
     */
	@Schema(description = "Full address of the property")
	private String address;
	
	/**
     * Locality or neighborhood within the city.
     */
	@Schema(description = "Locality or neighborhood of the property")
	private String locality;
	
	/**
     * Name of the city where the property is located.
     */
	@Schema(description = "City where the property is located")
	private String city;
	
	/**
     * State where the property is located.
     */
	@Schema(description = "State where the property is located")
	private String state;
	
	/**
     * Country where the property is located.
     */
	@Schema(description = "Country where the property is located")
	private String country;
	
	/**
     * 6-digit postal code of the location.
     */
	@Schema(description = "6-digit postal code")
	private Integer pincode;
	
}
