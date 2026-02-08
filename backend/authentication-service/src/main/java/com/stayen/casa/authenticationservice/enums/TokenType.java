package com.stayen.casa.authenticationservice.enums;

import lombok.Getter;

@Getter
public enum TokenType {
	
	/**
	 * 1000 * 60 ==> 1 minute  (60,000)
	 * 
	 * 1000 * 60 * 15 ==> 15 minutes  (9,00,000)
	 * 
	 * 1000 * 60 * 60 ==> 60 minutes / 1 hour  (36,00,000)
	 * 
	 * 1000 * 60 * 60 * 24 ==> 24 hours  (8,64,00,000)
	 */

	/**
	 * <pre>
	 * Access Token
	 * 
	 * with 15 minutes validity
	 * </pre>
	 */
	ACCESS(900000),
	
	/**
	 * <pre>
	 * Refresh Token
	 * 
	 * with 24 hours validity
	 * </pre>
	 */
	REFRESH(86400000),
	
	/**
	 * <pre>
	 * Temporary Token
	 * 
	 * with 1 milli-second validity
	 * </pre>
	 */
	TEMP(1000);
	
	private long validity;

	private TokenType(long validity) {
		this.validity = validity;
	}
	
}
