package com.stayen.casa.propertyservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.stayen.casa.propertyservice.dto.*;
import com.stayen.casa.propertyservice.enums.AreaUnit;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stayen.casa.propertyservice.entity.Location;
import com.stayen.casa.propertyservice.entity.PropertyEntity;
import com.stayen.casa.propertyservice.enums.PropertyError;
import com.stayen.casa.propertyservice.exception.PropertyException;
import com.stayen.casa.propertyservice.repository.PropertyCustomRepository;
import com.stayen.casa.propertyservice.repository.PropertyRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
	
	private final PropertyRepository propertyRepository;
	
	private final ModelMapper modelMapper;
	
	private final PropertyCustomRepository customRepository;

	@Override
	public List<PropertyEntity> getAllProperty() {
		
		return propertyRepository.findAll();
	}

	@Override
	public OwnerAndPropertyDTO addNewProperty(String ownerId, PropertyRequest propertyDetails) {
//		//Check if the property is already Listed
//		Optional<PropertyEntity> existingProperty = propertyRepository
//				.findByOwnerIdAndLocationAddressAndLocationPincode(
//						ownerID,
//						propertyDetails.getLocation().getAddress(),
//						propertyDetails.getLocation().getPincode()
//				);
//
//		if(existingProperty.isPresent()) {
//			throw new PropertyException(PropertyError.PROPERTY_ALREADY_EXIST);
//		}
		
		//Map the Property Details to entity
		PropertyEntity propertyEntity = modelMapper.map(propertyDetails, PropertyEntity.class);
		
		//Set Owner ID
		propertyEntity.setOwnerId(ownerId);
		
		//Generate random unique Property ID
		String propertyId = "prop-" + UUID.randomUUID().toString().replace("-", "");
		
		propertyEntity.setPropertyId(propertyId);

		// by default property area will be in Sq.Feet
		// Conversion from Sq.Feet to other unit will be done on UI
		propertyEntity.setUnit(AreaUnit.SQ_FT);
		
		//Make property as available while listing
		propertyEntity.setAvailable(true);
		
		//Set Additional Field
		propertyEntity.setUpdatedAt(LocalDateTime.now());
		
		propertyEntity.setListedAt(LocalDateTime.now());
		
		//Save to Database
		try {
			propertyRepository.save(propertyEntity);
		} catch (Exception e) {
			throw new PropertyException(PropertyError.PROPERTY_CREATION_FAILED);
		}
		
		//5. Return the Listing Confirmation Message
//		return new SimpleResponseDTO("Property is Listed !!!Your Property ID : " + propertyEntity.getPropertyId());
		return new OwnerAndPropertyDTO(
				ownerId,
				propertyId,
				"Property posted successfully.",
				propertyEntity.getListedAt()
		);
	}
	
	@Override
	public SimpleResponseDTO updatePropertyImages(String propertyId, List<String> imageUrls) {
		
		PropertyEntity propertyEntity =  propertyRepository
				.findById(propertyId)
				.orElseThrow(()-> new PropertyException(PropertyError.NO_PROPERTY_FOUND));
		
		if (imageUrls.size() > 3) {
	        throw new PropertyException(PropertyError.TOO_MANY_IMAGES);
	    }
		
		propertyEntity.setImages(imageUrls);
		propertyEntity.setUpdatedAt(LocalDateTime.now());

		propertyRepository.save(propertyEntity);

		return new SimpleResponseDTO("Images Updated Successfully!!!!!!");
	}

	@Override
	public PropertyResponse getPropertyById(String propertyId) {
		PropertyEntity propertyEntity =  propertyRepository
				.findById(propertyId)
				.orElseThrow(()-> new PropertyException(PropertyError.NO_PROPERTY_FOUND));
		
		return modelMapper.map(propertyEntity, PropertyResponse.class);
	}

	@Override
	public PropertyResponse updatePropertyDetails(String propertyId, String ownerId, PropertyRequest updatedDetails) {
		PropertyEntity propertyEntity = propertyRepository
				.findById(propertyId)
				.orElseThrow(()-> new PropertyException(PropertyError.NO_PROPERTY_FOUND));

		if(ownerId.equals(propertyEntity.getOwnerId()) == false) {
			throw new PropertyException(PropertyError.INVALID_PROPERTY_ID);
		}
		
		
		// Map all matching fields from DTO to entity
		modelMapper.map(updatedDetails, propertyEntity);
		propertyEntity.setUpdatedAt(LocalDateTime.now());

		try {
			propertyEntity = propertyRepository.save(propertyEntity);
		} catch (Exception e) {
			throw new PropertyException(PropertyError.PROPERTY_UPDATE_FAILED);
		}
		
		// Map entity to response
		return modelMapper.map(propertyEntity, PropertyResponse.class);
	}

	@Override
	public Map<String, Object> isPropertyAvailable(String propertyId) {
		PropertyEntity propertyEntity = propertyRepository
				.findById(propertyId)
				.orElseThrow(() -> new PropertyException(PropertyError.NO_PROPERTY_FOUND));
		
		return Map.of(
				"propertyId", propertyId,
				"isAvailable", propertyEntity.isAvailable()
		);
	}


	@Override
	public OwnerAndPropertyDTO markPropertyAsSold(String propertyId, String ownerId) {
		PropertyEntity propertyEntity = propertyRepository
				.findById(propertyId)
				.orElseThrow(() -> new PropertyException(PropertyError.NO_PROPERTY_FOUND));

		if(propertyEntity.getOwnerId().equals(ownerId) == false) {
			throw new PropertyException(PropertyError.INVALID_PROPERTY_ID);
		}

		propertyEntity.setAvailable(false);
		propertyEntity.setUpdatedAt(LocalDateTime.now());
		propertyEntity = propertyRepository.save(propertyEntity);

		return new OwnerAndPropertyDTO(
				ownerId,
				propertyId,
				"Property marked as sold.",
				propertyEntity.getUpdatedAt()
		);
	}

	@Override
	public OwnerAndPropertyDTO markPropertyAsAvailable(String propertyId, String ownerId) {
		PropertyEntity propertyEntity = propertyRepository
				.findById(propertyId)
				.orElseThrow(() -> new PropertyException(PropertyError.NO_PROPERTY_FOUND));

		if(propertyEntity.getOwnerId().equals(ownerId) == false) {
			throw new PropertyException(PropertyError.INVALID_PROPERTY_ID);
		}

		propertyEntity.setAvailable(true);
		propertyEntity.setUpdatedAt(LocalDateTime.now());
		propertyEntity = propertyRepository.save(propertyEntity);

		return new OwnerAndPropertyDTO(
				ownerId,
				propertyId,
				"Property marked as available.",
				propertyEntity.getUpdatedAt()
		);
	}

	@Override
	public PropertyResponse updatePartialProperty(UpdatePropertyRequest updatedFields, String propertyId) {

		PropertyEntity propertyEntity = propertyRepository.findById(propertyId)
				.orElseThrow(()->new PropertyException(PropertyError.NO_PROPERTY_FOUND));

		if(updatedFields.getPropertyName()!=null)
		{
			propertyEntity.setPropertyName(updatedFields.getPropertyName());
		}

		if(updatedFields.getPropertyDescription()!=null)
		{
			propertyEntity.setPropertyDescription(updatedFields.getPropertyDescription());
		}

		if(updatedFields.getListingType()!=null)
		{
			propertyEntity.setListingType(updatedFields.getListingType());
		}

		if(updatedFields.getPropertyCategory()!=null)
		{
			propertyEntity.setPropertyCategory(updatedFields.getPropertyCategory());
		}

		if(updatedFields.getPrice()!=null)
		{
			propertyEntity.setPrice(updatedFields.getPrice());
		}

		if(updatedFields.getArea()!=null)
		{
			propertyEntity.setArea(updatedFields.getArea());
		}

		if(updatedFields.getUnit()!=null)
		{
			propertyEntity.setUnit(updatedFields.getUnit());
		}

		if(updatedFields.getBedrooms()!=null)
		{
			propertyEntity.setBedrooms(updatedFields.getBedrooms());
		}

		if(updatedFields.getBathrooms()!=null)
		{
			propertyEntity.setBathrooms(updatedFields.getBathrooms());
		}

		if(updatedFields.getFloorNumber()!=null)
		{
			propertyEntity.setFloorNumber(updatedFields.getFloorNumber());
		}

		if(updatedFields.getTotalFloors()!=null)
		{
			propertyEntity.setTotalFloors(updatedFields.getTotalFloors());
		}

		if(updatedFields.getFurnishing()!=null)
		{
			propertyEntity.setFurnishing(updatedFields.getFurnishing());
		}

		if(updatedFields.getAmenities()!=null)
		{
			propertyEntity.setAmenities(updatedFields.getAmenities());
		}

		if(updatedFields.getLocation()!=null)
		{
			if(propertyEntity.getLocation()==null)
			{
				propertyEntity.setLocation(new Location());
			}

			updateLocationFields(propertyEntity.getLocation(), updatedFields.getLocation());
		}

		if(updatedFields.getImages()!=null)
		{
			propertyEntity.setImages(updatedFields.getImages());
		}

		if(updatedFields.getIsAvailable()!=null)
		{
			propertyEntity.setAvailable(updatedFields.getIsAvailable());
		}

		propertyEntity.setUpdatedAt(LocalDateTime.now());


		try {

			propertyRepository.save(propertyEntity);

		} catch (Exception e) {

			throw new PropertyException(PropertyError.PROPERTY_UPDATE_FAILED);

		}


		return modelMapper.map(propertyEntity, PropertyResponse.class);
	}
	
	/**
	 * Helper method that updates the fields of an existing Location entity with the non-null values 
	 * provided in the LocationUpdateRequest.
	 *
	 * Only fields that are not null in the request will be updated.
	 *
	 * @param existingLocation The current Location object to be updated.
	 * @param updatedLocations The update request containing new field values.
	 */
	public void updateLocationFields(Location existingLocation, LocationUpdateRequest updatedLocations)
	{
		
		
		if(updatedLocations.getLatitude()!=null)
		{
			existingLocation.setLatitude(updatedLocations.getLatitude());
		}
		
		if(updatedLocations.getLongitude()!=null)
		{
			existingLocation.setLongitude(updatedLocations.getLongitude());
		}
		
		if(updatedLocations.getAddress()!=null)
		{
			existingLocation.setAddress(updatedLocations.getAddress());
		}
		
		if(updatedLocations.getLocality()!=null)
		{
			existingLocation.setLocality(updatedLocations.getLocality());
		}
		
		if(updatedLocations.getCity()!=null)
		{
			existingLocation.setCity(updatedLocations.getCity());
		}
		
		if(updatedLocations.getState()!=null)
		{
			existingLocation.setState(updatedLocations.getState());
		}
		
		if(updatedLocations.getCountry()!=null)
		{
			existingLocation.setCountry(updatedLocations.getCountry());
		}
		
		if(updatedLocations.getPincode()!=null)
		{
			existingLocation.setPincode(updatedLocations.getPincode());
		}
		
	}

	@Override
	public List<PropertyResponse> searchProperties(PropertySearchRequest searchFields) {
		
		List<PropertyEntity> properties =  customRepository.searchProperties(searchFields);
		
		if(properties.isEmpty())
		{
			throw new PropertyException(PropertyError.PROPERTY_SEARCH_FAILED);
		}
		
		return properties
				.stream()
				.map(p-> modelMapper.map(p, PropertyResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public OwnerAndPropertyDTO deletePropertyById(String propertyId) {
		PropertyEntity propertyEntity = propertyRepository
				.findById(propertyId)
				.orElseThrow(() -> new PropertyException(PropertyError.NO_PROPERTY_FOUND));

		try {
			propertyRepository.deleteById(propertyId);
		} catch (Exception e) {
			throw new PropertyException(PropertyError.PROPERTY_DELETION_FAILED);
		}

		/**
		 * Returning Owner Id so that
		 * user profile can also update its ownedProperties section
		 */
		return new OwnerAndPropertyDTO(
				propertyEntity.getOwnerId(),
				propertyId,
				"Property deleted successfully.",
				LocalDateTime.now()
		);
	}

	@Override
	public List<PropertyResponse> getPropertiesByOwner(String ownerId) {
		List<PropertyEntity> properties = propertyRepository.findByOwnerId(ownerId);

		if(properties.isEmpty()) {
			throw new PropertyException(PropertyError.INVALID_PROPERTY_ID);
		}

        return properties.stream()
                .map(property -> modelMapper.map(property, PropertyResponse.class))
                .toList();
    }
}
