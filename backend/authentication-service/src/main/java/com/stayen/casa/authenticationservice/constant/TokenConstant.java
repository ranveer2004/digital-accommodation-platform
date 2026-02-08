package com.stayen.casa.authenticationservice.constant;

public class TokenConstant {
	private static final String CLASS_NAME = TokenConstant.class.getSimpleName();

	public static final String JWT_KEY_ALGORITHM_NAME = "HmacSHA256";

	public static final String TOKEN_TYPE = "tokenType";

	public static final String TOKEN_ISSUER = "StayEn.Casa";
	
	public static final String EMAIL = "email";
	
	public static final String DEVICE_ID = "deviceId";
	
	public static final String LOGOUT_MSG = "Logged out successfully !!!";
	
	public static final int SALTING_ROUND = 5;
	
}
