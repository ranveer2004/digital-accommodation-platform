package com.stayen.casa.authenticationservice.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

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
