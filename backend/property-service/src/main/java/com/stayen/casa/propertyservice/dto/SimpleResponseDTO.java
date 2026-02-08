package com.stayen.casa.propertyservice.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//DTO :  Reponse DTO : to send API response from REST Server ---> Rest Client

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"message", "timestamp"})
public class SimpleResponseDTO {

	private String message;
	private LocalDateTime timeStamp;
	
	public SimpleResponseDTO(String message)
	{
		this.message = message;
		this.timeStamp = LocalDateTime.now();
	}
	
}
