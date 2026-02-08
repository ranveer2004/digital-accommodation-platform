package com.stayen.casa.authenticationservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
	
	private String uid;
	
	private String email;
	
	private String deviceId;
	
}
