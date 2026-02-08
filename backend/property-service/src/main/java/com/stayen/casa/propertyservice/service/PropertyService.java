package com.stayen.casa.propertyservice.service;

import java.util.List;
import java.util.Map;

import com.stayen.casa.propertyservice.dto.*;
import com.stayen.casa.propertyservice.entity.PropertyEntity;

/**
 * PropertyService defines all the business operations related to property management,
 * including CRUD operations, availability toggling, and filtered search.
 */

public interface PropertyService {
	
	/**
     * Retrieves a list of all properties.
     * @return List of all properties stored in the system.
     */
	List<PropertyEntity> getAllProperty();
	
	/**
     * Adds a new property under the specified owner.
     * @param propertyDetails The request body containing property information.
     * @param ownerId The unique ID of the owner adding the property.
     * @return APIResponse indicating success or failure.
     */
	OwnerAndPropertyDTO addNewProperty(String ownerId, PropertyRequest propertyDetails);
	
	/**
	 * Updates the list of image URLs for an existing property.
	 * @param propertyId ID of the property whose images will be updated. 
	 * @param imageUrls List of image URLs to set for the property.
	 * @return APIResponse indicating success or failure.
	 */
	SimpleResponseDTO updatePropertyImages(String propertyId, List<String> imageUrls);
	
	/**
     * Fetches a specific property using its ID.
     * @param propertyId The unique identifier of the property.
     * @return PropertyResponse containing the property details.
     */
	PropertyResponse getPropertyById(String propertyId);
	
	/**
     * Updates the full property details using the given ID.
     * @param updatedDetails The updated property information.
     * @param propertyId The unique ID of the property to update.
     * @return PropertyResponse containing updated property information.
     */
	PropertyResponse updatePropertyDetails(String propertyId, String ownerId, PropertyRequest updatedDetails);
	
	/**
     * Toggles or updates the availability status of a property.
     * @param propertyId The ID of the property whose availability is being changed.
     * @return PropertyResponse with the updated availability status.
     */
	Map<String, Object> isPropertyAvailable(String propertyId);

	OwnerAndPropertyDTO markPropertyAsSold(String propertyId, String ownerId);

	OwnerAndPropertyDTO markPropertyAsAvailable(String propertyId, String ownerId);

	/**
     * Updates specific fields of a property (partial update).
     * @param updatedFields The fields to be updated.
     * @param propertyId The ID of the property to update.
     * @return PropertyResponse with the updated fields.
     */
	PropertyResponse updatePartialProperty(UpdatePropertyRequest updatedFields, String propertyId);
	
	/**
     * Searches properties based on dynamic filters like price, category, location, etc.
     * @param searchFields Filters to apply during the search.
     * @return List of properties matching the criteria.
     */
	List<PropertyResponse> searchProperties(PropertySearchRequest searchFields);
	
	 /**
     * Deletes a property by its ID.
     * @param propertyId The unique ID of the property to delete.
     * @return APIResponse indicating whether deletion was successful.
     */
	OwnerAndPropertyDTO deletePropertyById(String propertyId);

	/**
	 * Retrieves all Properties associated with the Specific Owner
	 * @param ownerId The unique ID of the owner whose listed properties are to be retrieved. 
	 * @return A List of Property associated with the Given Owner.
	 */
	List<PropertyResponse> getPropertiesByOwner(String ownerId);

	

}