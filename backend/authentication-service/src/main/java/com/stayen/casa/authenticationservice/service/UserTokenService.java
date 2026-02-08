package com.stayen.casa.authenticationservice.service;

import com.stayen.casa.authenticationservice.dto.AuthTokenResponseDTO;
import com.stayen.casa.authenticationservice.dto.LogoutRequestDTO;
import com.stayen.casa.authenticationservice.dto.RefreshTokenRequestDTO;
import com.stayen.casa.authenticationservice.dto.SimpleResponseDTO;
import com.stayen.casa.authenticationservice.entity.UserToken;
import com.stayen.casa.authenticationservice.model.JwtModel;

public interface UserTokenService {
	
	UserToken fetchUserToken(String uid, RuntimeException throwException);
	
	SimpleResponseDTO invalidateDeviceToken(LogoutRequestDTO logoutRequestDTO);

	AuthTokenResponseDTO generateToken(JwtModel jwtModel);
	
	AuthTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
	
}
