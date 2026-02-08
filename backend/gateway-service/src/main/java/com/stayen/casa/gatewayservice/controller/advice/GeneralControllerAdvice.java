package com.stayen.casa.gatewayservice.controller.advice;

import com.stayen.casa.gatewayservice.dto.ErrorResponseDTO;
import com.stayen.casa.gatewayservice.enums.CommonError;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;

import java.net.ConnectException;

@RestControllerAdvice
public class GeneralControllerAdvice {

    @ExceptionHandler(exception = RestClientResponseException.class)
    public ResponseEntity<?> handleRestClientResponseException(RestClientResponseException ex) {
        System.out.println("CAUGHT in Advice (RestClientResponseException) :: " + ex.getMessage());

        return ResponseEntity
                .status(ex.getStatusCode())
//                .contentType(MediaType.APPLICATION_JSON)
                .headers(ex.getResponseHeaders())
                .body(ex.getResponseBodyAsString());
    }

    @ExceptionHandler(exception = ConnectException.class)
    public ResponseEntity<?> handleConnectException(ConnectException connectException) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO(CommonError.SERVER_NOT_ACTIVE));
    }

    @ExceptionHandler(exception = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ErrorResponseDTO(CommonError.METHOD_NOT_FOUND));
    }

}
