package com.stayen.casa.gatewayservice.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class EnvConstant {

    /**
     * For JWT creation
     */
    @Value("${jwt-secret-key}")
    private String jwtSecretKey;

    /**
     * For inter-service communication
     */
    @Value("${internal-service-auth-key}")
    private String internalServiceAuthKey;

    @Value("${auth-service-domain}")
    private String authServiceDomain;

    @Value("${user-service-domain}")
    private String userServiceDomain;

    @Value("${property-service-domain}")
    private String propertyServiceDomain;

    /**
     * Cookies and Cors
     */
    @Value("${app-domain}")
    private String appDomain;

    @Value("${gateway-service-domain}")
    private String gatewayServiceDomain;

    @Value("${frontend-domain}")
    private String frontendDomain;

}
