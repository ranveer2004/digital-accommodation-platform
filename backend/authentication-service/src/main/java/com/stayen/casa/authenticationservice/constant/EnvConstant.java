package com.stayen.casa.authenticationservice.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class EnvConstant {

    @Value("${jwt-secret-key}")
    private String jwtSecretKey;

    @Value("${internal-service-auth-key}")
    private String internalServiceAuthKey;

    @Value("${resend-mail-api-key}")
    private String resendMailApiKey;

}
