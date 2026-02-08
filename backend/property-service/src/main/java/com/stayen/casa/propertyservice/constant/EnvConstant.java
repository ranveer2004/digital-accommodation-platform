package com.stayen.casa.propertyservice.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class EnvConstant {

    @Value("${internal-service-auth-key}")
    private String internalServiceAuthKey;

}
