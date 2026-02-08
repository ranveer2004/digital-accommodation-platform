package com.stayen.casa.authenticationservice.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class BaseTimestampDTO {
	
	@CreatedDate
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime updatedAt;
	
	protected void currentTimestamp() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	
	protected void updateTimestamp(LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
}
