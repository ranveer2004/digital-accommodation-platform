package com.stayen.casa.gatewayservice.exception;

import com.stayen.casa.gatewayservice.enums.TokenError;
import lombok.Getter;

@Getter
public class TokenException extends RuntimeException {

    TokenError tokenError;

    public TokenException(TokenError tokenError) {
        super(tokenError.getMessage());
        this.tokenError = tokenError;
    }

}
