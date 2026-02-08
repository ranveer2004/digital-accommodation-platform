package com.stayen.casa.gatewayservice.enums;

import lombok.Getter;

@Getter
public enum CommonError implements GenericError {

    SERVER_NOT_ACTIVE(5001, "Unable to process request. The server is temporarily unavailable."),

    METHOD_NOT_FOUND(405, "No HTTP method found for the given URL. Please check API documentation.");

    private int code;
    private String message;

    private CommonError(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
