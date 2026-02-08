package com.stayen.casa.userservice.controller.advice;

import com.stayen.casa.userservice.dto.ErrorResponseDTO;
import com.stayen.casa.userservice.exception.ProfileException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserProfileControllerAdvice {

    @ExceptionHandler(exception = ProfileException.class)
    public ResponseEntity<?> handleProfileException(ProfileException profileException) {

        HttpStatus status = switch (profileException.getError().getCode()) {
            case 1301, 1303 -> HttpStatus.NOT_FOUND;
            case 1302 -> HttpStatus.CONFLICT;
            default -> HttpStatus.FORBIDDEN;
        };

        return ResponseEntity
                .status(status)
                .body(new ErrorResponseDTO(profileException.getError()));
    }
}
