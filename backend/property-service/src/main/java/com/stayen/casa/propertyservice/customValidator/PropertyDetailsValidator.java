package com.stayen.casa.propertyservice.customValidator;

import com.stayen.casa.propertyservice.dto.PropertyRequest;
import com.stayen.casa.propertyservice.enums.PropertyCategory;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Custom validator to ensure that certain property details are provided
 * when the property is not a plot.
 * 
 * Applies to {@link PropertyRequest} objects annotated with @ValidPropertyDetails.
 */

public class PropertyDetailsValidator implements ConstraintValidator<ValidPropertyDetails, PropertyRequest> {

	/**
     * Validates a PropertyRequest based on property category.
     * If the category is not PLOT, validates bathrooms, bedrooms, floor number,
     * furnishing, amenities, and total floors.
     *
     * @param value The PropertyRequest to validate.
     * @param context ConstraintValidatorContext to add custom violation messages.
     * @return true if valid, false otherwise.
     */
	@Override
	public boolean isValid(PropertyRequest value, ConstraintValidatorContext context) {
		
		// Allow null value or null category to skip validation
		if(value.getPropertyCategory()==null) return true;
		
		boolean isPlot = value.getPropertyCategory() == PropertyCategory.PLOT;
		
		boolean valid = true;
		
		if(!isPlot) {
			
			// Disable default validation message
			context.disableDefaultConstraintViolation();
			
			if(value.getBathrooms()==null)
			{
				context.buildConstraintViolationWithTemplate("Please enter the Total Number of Bathrooms")
				.addPropertyNode("bathrooms").addConstraintViolation();
				
				valid = false;
			}
			
			if(value.getBedrooms()==null)
			{
				context.buildConstraintViolationWithTemplate("Please enter the Total Number of Bedrooms")
				.addPropertyNode("bedrooms").addConstraintViolation();
				
				valid = false;
			}
			
			if(value.getFloorNumber()==null)
			{
				context.buildConstraintViolationWithTemplate("Please enter the Correct Floor Number")
				.addPropertyNode("floorNumber").addConstraintViolation();
				
				valid = false;
			}
			
			if(value.getFurnishing()==null)
			{
				context.buildConstraintViolationWithTemplate("Please Jusify the Furnishing (Furnished, Unfurnished, Semi-Furnished)")
				.addPropertyNode("furnishing").addConstraintViolation();
				
				valid = false;
			}
			
			if(value.getAmenities()==null || value.getAmenities().isEmpty())
			{
				context.buildConstraintViolationWithTemplate("Please enter the List of Amenities available")
				.addPropertyNode("amenities").addConstraintViolation();
				
				valid = false;
			}
			if(value.getTotalFloors()==null)
			{
				context.buildConstraintViolationWithTemplate("Please Enter the Total Number of Floors")
				.addPropertyNode("totalFloors").addConstraintViolation();
				
				valid = false;
			}

		}
		
		return valid;
	}

}
