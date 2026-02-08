package com.stayen.casa.authenticationservice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({"email", "password", "deviceId", "createdAt", "updatedAt"})
public class LoginRequestDTO extends BaseTimestampDTO {

	private String email;

	private String password;

	private String deviceId;

	public LoginRequestDTO(String email, String deviceId, String password) {
		this.email = email;
		this.password = password;
		this.deviceId = deviceId;
	}

}
