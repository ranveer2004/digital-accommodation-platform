package com.stayen.casa.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefreshTokenRequestDTO {

    private String uid;

    private String email;

    private String deviceId;

    private String refreshToken;

}
