package com.stayen.casa.propertyservice.customValidator;

import com.stayen.casa.propertyservice.dto.PropertySearchRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Custom validator to ensure that the min-max range fields in a
 * PropertySearchRequest are logically correct.
 *
 * Validates:
 * - minArea should not be greater than maxArea
 * - minPrice should not be greater than maxPrice
 */

public class RangeValidator implements ConstraintValidator<ValidRange, PropertySearchRequest> {

	/**
     * Validates that minArea ≤ maxArea and minPrice ≤ maxPrice.
     *
     * @param value   The PropertySearchRequest to validate.
     * @param context The context in which the constraint is evaluated.
     * @return true if valid; false if any range is invalid.
     */
	@Override
	public boolean isValid(PropertySearchRequest value, ConstraintValidatorContext context) {
		
		if(value == null) return true;
		
		// Disable default message before adding custom ones
		context.disableDefaultConstraintViolation();
		
		boolean valid = true;
		
		//Validate Area Range
		if(value.getMinArea() != null && value.getMaxArea() != null && value.getMinArea()>value.getMaxArea())
		{
			context.buildConstraintViolationWithTemplate("Minimum Area cannot be greater than Maximum Area")
			.addPropertyNode("minArea").addConstraintViolation();
			
			valid = false;
		}
		
		//Validate Price Range
		if(value.getMinPrice() != null && value.getMaxPrice() != null && value.getMinPrice().compareTo(value.getMaxPrice())>0)
		{
			context.buildConstraintViolationWithTemplate("Minimum Price cannot be greater than Maximum Price")
			.addPropertyNode("minPrice").addConstraintViolation();
			
			valid = false;
		}
		return valid;
	}

}
