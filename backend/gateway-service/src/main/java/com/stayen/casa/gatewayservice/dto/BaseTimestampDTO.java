package com.stayen.casa.gatewayservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
public abstract class BaseTimestampDTO {

	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime createdAt;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime updatedAt;
	
	protected BaseTimestampDTO() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	
	protected BaseTimestampDTO(LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
}
