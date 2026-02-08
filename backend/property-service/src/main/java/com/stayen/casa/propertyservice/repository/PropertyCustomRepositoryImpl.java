package com.stayen.casa.propertyservice.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.stayen.casa.propertyservice.dto.PropertySearchRequest;
import com.stayen.casa.propertyservice.entity.PropertyEntity;

import lombok.AllArgsConstructor;

@Repository
//@AllArgsConstructor
public class PropertyCustomRepositoryImpl implements PropertyCustomRepository {
	
	private final MongoTemplate mongoTemplate;

	@Autowired
	public  PropertyCustomRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<PropertyEntity> searchProperties(PropertySearchRequest searchFieldsDTO) {
		
		List<Criteria> filters = new ArrayList<>();
		
//		if(searchFieldsDTO.getPropertyName() != null && !searchFieldsDTO.getPropertyName().isBlank())
//		{
//			filters.add(Criteria.where("propertyName")
//			.regex(searchFieldsDTO.getPropertyName(), "i"));
//		}
		
		if(searchFieldsDTO.getListingType() != null)
		{
			filters.add(Criteria.where("listingType")
					.is(searchFieldsDTO.getListingType()));
		}
		
		 if (searchFieldsDTO.getFurnishing() != null) {
	            filters.add(Criteria.where("furnishing")
						.is(searchFieldsDTO.getFurnishing()));
	        }
		
		if(searchFieldsDTO.getPropertyCategory() != null)
		{
			filters.add(Criteria.where("propertyCategory")
					.is(searchFieldsDTO.getPropertyCategory()));
		}
		
		if(searchFieldsDTO.getMinPrice() != null && searchFieldsDTO.getMaxPrice() != null)
		{
			filters.add(Criteria.where("price")
					.gte(searchFieldsDTO.getMinPrice().doubleValue())
					.lte(searchFieldsDTO.getMaxPrice().doubleValue()));
		}
		else if(searchFieldsDTO.getMinPrice()!=null)
		{
			filters.add(Criteria.where("price")
					.gte(searchFieldsDTO.getMinPrice().doubleValue()));
		}
		else if(searchFieldsDTO.getMaxPrice() != null)
		{
			filters.add(Criteria.where("price")
					.lte(searchFieldsDTO.getMaxPrice().doubleValue()));
		}
		
		if(searchFieldsDTO.getMinArea() != null && searchFieldsDTO.getMaxArea() != null)
		{
			filters.add(Criteria.where("area")
					.gte(searchFieldsDTO.getMinArea())
					.lte(searchFieldsDTO.getMaxArea()));
		}
		else if(searchFieldsDTO.getMinArea() != null)
		{
			filters.add(Criteria.where("area")
					.gte(searchFieldsDTO.getMinArea()));
		}
		else if(searchFieldsDTO.getMaxArea() != null)
		{
			filters.add(Criteria.where("area")
					.lte(searchFieldsDTO.getMaxArea()));
		}
		
		if(searchFieldsDTO.getUnit() != null)
		{
			filters.add(Criteria.where("unit")
					.is(searchFieldsDTO.getUnit()));
		}
		
		if(searchFieldsDTO.getBedrooms() != null)
		{
			filters.add(Criteria.where("bedrooms")
					.gte(searchFieldsDTO.getBedrooms()));
		}
		
		if(searchFieldsDTO.getBathrooms() != null)
		{
			filters.add(Criteria.where("bathrooms")
					.gte(searchFieldsDTO.getBathrooms()));
		}
		
		if(searchFieldsDTO.getAmenities() != null && !searchFieldsDTO.getAmenities().isEmpty())
		{
			filters.add(Criteria.where("amenities")
					.all(searchFieldsDTO.getAmenities()));
		}
		
		if(searchFieldsDTO.getTotalFloors() != null)
		{
			filters.add(Criteria.where("totalFloors")
					.gte(searchFieldsDTO.getTotalFloors()));
		}
		
		if(searchFieldsDTO.getCity() != null && !searchFieldsDTO.getCity().isBlank())
		{
			filters.add(Criteria.where("location.city")
					.regex(searchFieldsDTO.getCity(), "i"));
		}
		
		if(searchFieldsDTO.getState() != null && !searchFieldsDTO.getState().isBlank())
		{
			filters.add(Criteria.where("location.state")
					.regex(searchFieldsDTO.getState(), "i"));
		}

		if(searchFieldsDTO.getCountry() != null && searchFieldsDTO.getCountry().isBlank()) {
			filters.add(Criteria.where("location.country")
					.regex(searchFieldsDTO.getCountry(), "i"));
		}

		if(searchFieldsDTO.getPincode() != 0 && (Math.log10(searchFieldsDTO.getPincode()+1) >= 3)) {
			filters.add(Criteria.where("location.pincode")
					.is(searchFieldsDTO.getPincode()));
		}
		
//		if(searchFieldsDTO.getLocality()!=null && !searchFieldsDTO.getLocality().isBlank())
//		{
//			filters.add(Criteria.where("location.locality")
//			.regex(searchFieldsDTO.getLocality(), "i"));
//		}
		
		Query query = new Query();
		
		if(!filters.isEmpty())
		{
			query.addCriteria(new Criteria().andOperator(filters.toArray(new Criteria[0])));
		}
		
		return mongoTemplate.find(query, PropertyEntity.class);
	}

}
