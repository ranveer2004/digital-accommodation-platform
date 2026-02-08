package com.stayen.casa.gatewayservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
	
	private String uid;
	
	private String email;
	
	private String deviceId;
	
	private String token;

}
