package com.stayen.casa.propertyservice.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Enum for categorizing the type of property.
 */

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum PropertyCategory {
	
	/**
     * A flat or apartment unit, typically in a multi-storey building.
     */
	FLAT,
	
	/**
     * A standalone villa or independent house.
     */
	VILLA,
	
	/**
     * A plot of land, usually for residential or commercial development.
     */
	PLOT,
	
	/**
     * Commercial property like office space, shops, etc.
     */
	COMMERCIAL

}
