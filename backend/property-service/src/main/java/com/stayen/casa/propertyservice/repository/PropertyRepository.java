package com.stayen.casa.propertyservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.stayen.casa.propertyservice.entity.PropertyEntity;

/**
 * MongoDB repository for accessing PropertyEntity documents.
 */

@Repository
public interface PropertyRepository extends MongoRepository<PropertyEntity, String>{

	
	/**
     * Finds a property by owner ID, address, and pincode.
     * Typically used to check for duplicate property listings by the same owner.
     *
     * @param ownerID the owner's unique identifier
     * @param address the address of the property
     * @param pincode the pincode of the property location
     * @return an Optional containing the matching property if found
     */
	@Query("{ 'ownerId': ?0, 'location.address': ?1, 'location.pincode': ?2 }")
	Optional<PropertyEntity> findByOwnerIdAndLocationAddressAndLocationPincode(String ownerID, String address, Integer pincode);

	List<PropertyEntity> findByOwnerId(String ownerId);

}
