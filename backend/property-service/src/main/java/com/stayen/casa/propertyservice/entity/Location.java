package com.stayen.casa.propertyservice.entity;

import lombok.Data;

/**
 * Represents the geographical and address details of a property.
 */
@Data
public class Location {
	
	/**
     * Latitude coordinate of the property (ranges from -90 to 90).
     */
	private double latitude;
	
	/**
     * Longitude coordinate of the property (ranges from -180 to 180).
     */
	private double longitude;
	
	/**
     * Full address of the property (e.g., "Plot 5, MG Road").
     */
	private String address;
	
	/**
     * Local landmark or area name (e.g., "near Akurdi railway station").
     */
	private String locality;
	
	/**
     * City in which the property is located (e.g., "Pune").
     */
	private String city;
	
	/**
     * State in which the property is located (e.g., "Maharashtra").
     */
	private String state;
	
	/**
     * Country in which the property is located (e.g., "India").
     */
	private String country;
	
	/**
     * Postal code or PIN code for the property's location.
     */
	private int pincode;

}
