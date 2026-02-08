package com.stayen.casa.userservice.dto;

import com.stayen.casa.userservice.enums.CommonError;
import com.stayen.casa.userservice.enums.GenericError;
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
