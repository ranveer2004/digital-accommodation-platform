package com.stayen.casa.gatewayservice.enums;

import lombok.Getter;

@Getter
public enum TokenError implements GenericError {

    /**
     * <pre>
     * Denotes request doesn't contain authentication header
     * </pre>
     */
    EMPTY(1001, "Authorization header missing or invalid."),

    /**
     * <pre>
     * Denotes JWT token has been expired
     * </pre>
     */
    EXPIRED(1002, "Token has expired. Please log in again to continue."),

    /**
     * <pre>
     * Denotes JWT token has been blacklisted,
     * and we need to block
     * </pre>
     */
    BLACKLISTED(1003, "This token is invalid or has already been invalidated."),

    /**
     * <pre>
     * Denotes, JWT token is invalid.
     * When ACCESS token is used for token refreshing
     * Or
     * When REFRESH token is used for general request
     * Or
     * When TEMP token is used for any purpose
     * </pre>
     */
    INVALID(1004, "Invalid authorization token."),

    /**
     * <pre>
     * Denotes, JWT token doesn't contain user information claims
     * </pre>
     */
    INVALID_USER(1005, "Invalid user details provided in authorization token."),

    /**
     * <pre>
     * Denotes, JWT token has been malformed.
     * Token header, payload, signature has changed.
     * </pre>
     */
    MALFORMED(1006, "Malformed authorization. The authorization token format is invalid or corrupted."),

    /**
     * <pre>
     * Denotes, JWT token key is algorithm is not supported
     * </pre>
     */
    UNSUPPORTED(1007, "The algorithm used in the authorization token is not supported."),

    /**
     * <pre>
     * Denotes,
     * Last Refresh Token is used instead of Current Refresh Token
     * </pre>
     */
    BLOCKED(1010, "Access blocked. The token is not valid. Please log in again to continue. !!!");

    private int code;
    private String message;

    private TokenError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
