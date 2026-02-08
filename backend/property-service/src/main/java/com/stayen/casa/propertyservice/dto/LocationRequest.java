package com.stayen.casa.propertyservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO for property location details.
 * Used during property creation and full updates.
 */

@Data
public class LocationRequest {
	
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
	@NotBlank(message = "Address is Required")
	private String address;
	
	/**
     * Locality or neighborhood within the city.
     */
	@Schema(description = "Locality or neighborhood of the property")
	@NotBlank(message = "Locality is Required")
	private String locality;
	
	/**
     * Name of the city where the property is located.
     */
	@Schema(description = "City where the property is located")
	@NotBlank(message = "City Name is Required")
	private String city;
	
	/**
     * State where the property is located.
     */
	@Schema(description = "State where the property is located")
	@NotBlank(message = "State is Required")
	private String state;
	
	/**
     * Country where the property is located.
     */
	@Schema(description = "Country where the property is located")
	@NotBlank(message = "Country is Required")
	private String country;
	
	/**
     * 6-digit postal code of the location.
     */
	@Schema(description = "6-digit postal code")
	@NotNull(message = "Pincode is Required")
	@Min(value = 100000, message = "Pincode must be a 6-digit Number")
	@Max(value = 999999, message = "Pincode must be a 6-digit Number")
	private Integer pincode;
		
}
