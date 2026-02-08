package com.stayen.casa.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LogoutRequestDTO {

    private String uid;

    private String deviceId;

}
