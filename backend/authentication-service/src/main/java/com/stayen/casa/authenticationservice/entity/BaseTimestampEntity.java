package com.stayen.casa.authenticationservice.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * Base Entity - Abstract Class
 * 
 * Need to be Abstract to work with MongoDB
 * </pre>
 */
@Getter
@Setter
public abstract class BaseTimestampEntity {
	
	@CreatedDate
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime updatedAt;

	public BaseTimestampEntity() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public BaseTimestampEntity(LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
//	protected void currentTimestamp() {
//		this.createdAt = LocalDateTime.now();
//		this.updatedAt = LocalDateTime.now();
//	}
//
//	protected void updateTimestamp(LocalDateTime updatedAt) {
//		this.updatedAt = updatedAt;
//	}
}
