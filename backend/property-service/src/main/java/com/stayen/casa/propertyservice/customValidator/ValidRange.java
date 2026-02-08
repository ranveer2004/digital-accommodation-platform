package com.stayen.casa.propertyservice.customValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = RangeValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

/**
 * Custom annotation to validate min-max ranges in PropertySearchRequest,
 * such as area and price ranges.
 */
public @interface ValidRange {
String message() default "Minimum value cannot be greater than maximum value.";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
