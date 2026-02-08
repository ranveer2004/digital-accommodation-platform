package com.stayen.casa.authenticationservice.enums;

import lombok.Getter;

@Getter
public enum CommonError implements GenericError {

    ORIGIN_NOT_ALLOWED(2001, "Request rejected. Origin is not authorized to access this resource."),

    USER_DETAIL_NOT_FOUND(2002, "Request rejected. User detail not found in header. Must have all of these (uid, email and device id).");

    private int code;
    private String message;

    private CommonError(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
