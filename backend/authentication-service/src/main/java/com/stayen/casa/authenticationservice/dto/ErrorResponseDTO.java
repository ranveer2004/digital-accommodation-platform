package com.stayen.casa.authenticationservice.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stayen.casa.authenticationservice.enums.GenericError;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"errorCode", "errorMessage", "timestamp"})
public class ErrorResponseDTO {

	private int errorCode;

	private String errorMessage;
	
	private LocalDateTime timestamp;

	public ErrorResponseDTO(GenericError genericError) {
		this.errorCode = genericError.getCode();
		this.errorMessage = genericError.getMessage();
		this.timestamp = LocalDateTime.now();
	}

}
