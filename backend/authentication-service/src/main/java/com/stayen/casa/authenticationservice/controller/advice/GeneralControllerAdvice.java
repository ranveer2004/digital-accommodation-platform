package com.stayen.casa.authenticationservice.controller.advice;

import com.stayen.casa.authenticationservice.exception.EmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;

@RestControllerAdvice
public class GeneralControllerAdvice {

    @ExceptionHandler(exception = EmailException.class)
    public ResponseEntity<?> handleEmailException(EmailException emailException) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Oops !!! Something went wrong. Please try again after sometime.");
    }

    @ExceptionHandler(exception = RestClientResponseException.class)
    public ResponseEntity<?> handleRestClientResponseException(RestClientResponseException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .headers(ex.getResponseHeaders())
                .body(ex.getResponseBodyAsString());
    }

}
