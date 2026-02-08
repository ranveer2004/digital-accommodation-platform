package com.stayen.casa.propertyservice.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum indicating the furnishing status of the property.
 */

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Furnishing {
	
	/**
     * Property is partially furnished with essential items like wardrobes, kitchen cabinets, etc.
     */
	SEMI_FURNISHED("Semi Furnished"),
	
	/**
     * Property has no furnishings. Bare shell.
     */
	UNFURNISHED("Unfurnished"),
	
	/**
     * Property is fully furnished with all required furniture and appliances.
     */
	FURNISHED("Fully Furnished");
	
	private final String displayName;
	
	Furnishing(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

}
