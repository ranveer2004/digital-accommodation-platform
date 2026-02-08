package com.stayen.casa.propertyservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerAndPropertyDTO {

    private String ownerId;

    private String propertyId;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String message;

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

}
