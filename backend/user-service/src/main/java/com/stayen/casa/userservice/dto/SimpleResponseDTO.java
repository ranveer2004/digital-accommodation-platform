package com.stayen.casa.userservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SimpleResponseDTO {
	
	private String message;
	
	private LocalDateTime timestamp;

	public SimpleResponseDTO(String message) {
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}
	
}
