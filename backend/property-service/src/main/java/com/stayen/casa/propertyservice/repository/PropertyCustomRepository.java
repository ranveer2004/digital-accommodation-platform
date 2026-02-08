package com.stayen.casa.propertyservice.repository;

import java.util.List;

import com.stayen.casa.propertyservice.dto.PropertySearchRequest;
import com.stayen.casa.propertyservice.entity.PropertyEntity;

/**
 * Custom repository interface for complex or dynamic property search operations
 * that cannot be handled by standard method naming conventions in MongoRepository.
 */

public interface PropertyCustomRepository {
	
	
	/**
     * Performs dynamic search for properties based on various optional filters
     * like category, price range, area, bedrooms, location, etc.
     *
     * @param searchFields object containing all possible search criteria
     * @return list of properties matching the search criteria
     */
	List<PropertyEntity> searchProperties(PropertySearchRequest searchFields);

}
