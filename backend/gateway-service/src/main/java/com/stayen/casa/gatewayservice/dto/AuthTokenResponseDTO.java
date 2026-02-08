package com.stayen.casa.gatewayservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({"uid", "accessToken", "refreshToken", "createdAt", "updatedAt"})
public class AuthTokenResponseDTO extends BaseTimestampDTO {

	private String uid;
	
	private String accessToken;

	/**
	 * Marking this field as WriteOnly,
	 * so that the field do not participate in ResponseBody (deserialization)
	 */
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String refreshToken;
	
	public AuthTokenResponseDTO(String uid, String accessToken, String refreshToken) {
//		super();
		this.uid = uid;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	public AuthTokenResponseDTO(String uid, String accessToken, String refreshToken, LocalDateTime createdAt, LocalDateTime updatedAt) {
//		super(createdAt, updatedAt);
		this.uid = uid;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

}
