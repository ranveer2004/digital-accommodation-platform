package com.stayen.casa.authenticationservice.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.stayen.casa.authenticationservice.constant.EnvConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.stayen.casa.authenticationservice.constant.TokenConstant;
import com.stayen.casa.authenticationservice.enums.TokenType;
import com.stayen.casa.authenticationservice.model.JwtModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtils {

	private final EnvConstant envConstant;

	public JwtUtils(EnvConstant envConstant) {
		this.envConstant = envConstant;
	}
	
	/**
	 * <pre>
	 * Create an object of SecretKey class
	 * Based on keyBytes, Algorithm
	 * </pre>
	 * 
	 * @return SecretKey Object
	 */
	@Bean
	private SecretKey getJwtSecretKey() {
		byte[] jwtSecretKeyBytes = envConstant.getJwtSecretKey().getBytes(StandardCharsets.UTF_8);
		return new SecretKeySpec(jwtSecretKeyBytes, TokenConstant.JWT_KEY_ALGORITHM_NAME);
	}
	
	/**
	 * 
	 * 
	 * @param jwtModel
	 * @param tokenType
	 * @return String - JWT token
	 */
	public String generateJwtToken(JwtModel jwtModel, TokenType tokenType) {
		long currentTimeInMillis = System.currentTimeMillis();
		
		long expirationTimeInMillis = currentTimeInMillis + tokenType.getValidity();
		
		Map<String, Object> claims = new HashMap<>();
		claims.put(TokenConstant.EMAIL, jwtModel.getEmail());
		claims.put(TokenConstant.DEVICE_ID, jwtModel.getDeviceId());
		claims.put(TokenConstant.TOKEN_TYPE, tokenType);
		
		return Jwts.builder()
			.subject(jwtModel.getUid())
			.claims(claims)
			.issuedAt(new Date(currentTimeInMillis))
			.expiration(new Date(expirationTimeInMillis))
			.issuer(TokenConstant.TOKEN_ISSUER)
			.signWith(getJwtSecretKey())
			.compact();
	}
	
	
}
