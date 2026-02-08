package com.stayen.casa.authenticationservice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({"email", "password", "otp", "deviceId", "createdAt", "updatedAt"})
public class SignupRequestDTO extends BaseTimestampDTO {

    private String email;

    private String password;

    private String otp;

    private String deviceId;
}
