package com.stayen.casa.gatewayservice.security.utils;

import com.stayen.casa.gatewayservice.constant.EnvConstant;
import com.stayen.casa.gatewayservice.constant.TokenConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component
public class JwtUtils {

    private final EnvConstant envConstant;

    public JwtUtils(EnvConstant envConstant) {
        this.envConstant = envConstant;
    }

    @Bean
    private SecretKey getSecretKey() {
        byte[] keyBytes = envConstant.getJwtSecretKey().getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(keyBytes, TokenConstant.JWT_KEY_ALGORITHM_NAME);
    }

    public Claims validateToken(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
