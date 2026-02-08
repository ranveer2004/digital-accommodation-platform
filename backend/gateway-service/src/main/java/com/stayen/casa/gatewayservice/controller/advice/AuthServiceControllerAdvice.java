package com.stayen.casa.gatewayservice.controller.advice;

import com.stayen.casa.gatewayservice.dto.ErrorResponseDTO;
import com.stayen.casa.gatewayservice.exception.TokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthServiceControllerAdvice {

    @ExceptionHandler(exception = TokenException.class)
    public ResponseEntity<?> handleRestClientResponseException(TokenException tokenException) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponseDTO(tokenException.getTokenError()));
    }

}
