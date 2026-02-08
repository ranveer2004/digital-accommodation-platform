package com.stayen.casa.authenticationservice.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stayen.casa.authenticationservice.model.JwtModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({"uid", "accessToken", "refreshToken", "createdAt", "updatedAt"})
public class AuthTokenResponseDTO extends BaseTimestampDTO {

	private String uid;
	
	private String accessToken;
	
	private String refreshToken;
	
	public AuthTokenResponseDTO(String uid, String accessToken, String refreshToken) {
		super.currentTimestamp();
		this.uid = uid;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	public AuthTokenResponseDTO(String uid, String accessToken, String refreshToken, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super.updateTimestamp(createdAt, updatedAt);;
		this.uid = uid;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

}
