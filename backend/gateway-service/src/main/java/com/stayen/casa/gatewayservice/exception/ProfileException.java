package com.stayen.casa.gatewayservice.exception;

import com.stayen.casa.gatewayservice.enums.ProfileError;
import lombok.Getter;

@Getter
public class ProfileException extends RuntimeException {

	private ProfileError profileError;
	
	public ProfileException(ProfileError profileError) {
		super(profileError.getMessage());
		this.profileError = profileError;
	}
	
}
