package com.stayen.casa.gatewayservice.constant;

public class TokenConstant {

    /**
     * Auth header name
     * used to retrieve Authentication Request Header
     */
    public static final String AUTH_HEADER_NAME = "Authorization";

    /**
     * Auth token name
     * used to retrieve Token string from Request Header
     */
    public static final String AUTH_TOKEN_NAME = "Bearer ";

    /**
     * Algorithm name
     * used to create JWT Secret Key
     */
    public static final String JWT_KEY_ALGORITHM_NAME = "HmacSHA256";

    /**
     * Token issuer name key
     * used in JWT to store the name of issuer of token
     */
    public static final String TOKEN_ISSUER = "StayEn.Casa";

    /**
     * TokenType key name
     * used to store the type of token
     * in Jwt Claims
     */
    public static final String TOKEN_TYPE = "tokenType";

    /**
     * TokenType key name
     * used to store user's email
     * in Jwt Claims
     */
    public static final String EMAIL = "email";

    /**
     * TokenType key name
     * used to store user's deviceId for which the token is issued
     * in Jwt Claims
     */
    public static final String DEVICE_ID = "deviceId";

}
