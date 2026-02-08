package com.stayen.casa.propertyservice.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing units used for measuring area.
 */

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum AreaUnit {
	
	/**
     * Square Meter - commonly used unit in most countries.
     */
	SQ_M("Sq. M."),
	
	/**
     * Square Feet - commonly used unit in real estate (especially in the US and India).
     */
	SQ_FT("Sq. Ft."),
	
	/**
     * Square Yard - commonly used in plots and land measurement.
     */
	SQ_YARD("Sq.Yard"),
	
	/**
     * Hectare - used for measuring large land areas, especially in agriculture.
     */
	HECTARE("Hectare"),
	
	/**
     * Acre - used for measuring land, especially in rural or farmland contexts.
     */
	ACRE("Acre");
	
	private final String displayName;

	private AreaUnit(String displayName) {
		this.displayName = displayName;
	}

	@JsonValue
	public String getDisplayName() {
		return displayName;
	}
	
	
	
	

}
