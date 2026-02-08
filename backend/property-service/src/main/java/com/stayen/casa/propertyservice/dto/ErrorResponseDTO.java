package com.stayen.casa.propertyservice.dto;

import com.stayen.casa.propertyservice.enums.GenericError;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponseDTO {

    private int errorCode;

    private String errorMessage;

    private LocalDateTime timestamp;

    public ErrorResponseDTO(GenericError genericError) {
        this.errorCode = genericError.getCode();
        this.errorMessage = genericError.getMessage();
        this.timestamp = LocalDateTime.now();
    }

}
