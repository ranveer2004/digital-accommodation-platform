package com.stayen.casa.propertyservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO containing property location details.
 * Returned when fetching property data.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response object containing location details of the property.")
public class LocationResponse {
	
	 	@Schema(description = "Latitude of the property location")
	    private Double latitude;

	    @Schema(description = "Longitude of the property location")
	    private Double longitude;

	    @Schema(description = "Full address of the property")
	    private String address;

	    @Schema(description = "Locality or neighborhood of the property")
	    private String locality;

	    @Schema(description = "City where the property is located")
	    private String city;

	    @Schema(description = "State where the property is located")
	    private String state;

	    @Schema(description = "Country where the property is located")
	    private String country;

	    @Schema(description = "6-digit postal code")
	    private Integer pincode;

}
