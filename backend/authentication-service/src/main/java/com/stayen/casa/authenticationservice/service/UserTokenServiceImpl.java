package com.stayen.casa.authenticationservice.service;

import java.util.Optional;

import com.stayen.casa.authenticationservice.dto.LogoutRequestDTO;
import com.stayen.casa.authenticationservice.dto.RefreshTokenRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stayen.casa.authenticationservice.constant.TokenConstant;
import com.stayen.casa.authenticationservice.dto.AuthTokenResponseDTO;
import com.stayen.casa.authenticationservice.dto.SimpleResponseDTO;
import com.stayen.casa.authenticationservice.entity.UserToken;
import com.stayen.casa.authenticationservice.enums.AuthError;
import com.stayen.casa.authenticationservice.enums.TokenError;
import com.stayen.casa.authenticationservice.enums.TokenType;
import com.stayen.casa.authenticationservice.exception.AuthException;
import com.stayen.casa.authenticationservice.exception.TokenException;
import com.stayen.casa.authenticationservice.entity.DeviceToken;
import com.stayen.casa.authenticationservice.model.JwtModel;
import com.stayen.casa.authenticationservice.repository.UserTokenRepository;
import com.stayen.casa.authenticationservice.utils.JwtUtils;

@Service
public class UserTokenServiceImpl implements UserTokenService {
	private static final String CLASS_NAME = UserTokenServiceImpl.class.getSimpleName();

	private final JwtUtils jwtUtils;
	private final UserTokenRepository userTokenRepository;

	@Autowired
	public UserTokenServiceImpl(JwtUtils jwtUtils, UserTokenRepository userTokenRepository) {
		this.jwtUtils = jwtUtils;
		this.userTokenRepository = userTokenRepository;
	}
	
	private UserToken getOrDefaultUserToken(JwtModel jwtModel) {
		Optional<UserToken> userToken = userTokenRepository.findByUid(jwtModel.getUid());

		if (userToken.isPresent()) {
			return userToken.get();
		} else {
			return new UserToken(jwtModel.getUid(), jwtModel.getEmail());
		}
	}
	
	@Override
	public UserToken fetchUserToken(String uid, RuntimeException exception) {
		return userTokenRepository
				.findByUid(uid)
				.orElseThrow(() -> exception);
	}
	
	@Override
	public SimpleResponseDTO invalidateDeviceToken(LogoutRequestDTO logoutRequestDTO) {
		/**
		 * If no user token found
		 */
		UserToken userToken = fetchUserToken(
				logoutRequestDTO.getUid(),
				new AuthException(AuthError.NO_ACCOUNT_FOUND)
		);
		
		/**
		 * If token is valid, 
		 * but user has already logged out (or no device session found).
		 */
		if(userToken.removeDeviceToken(logoutRequestDTO.getDeviceId()) == false) {
			throw new AuthException(AuthError.SESSION_NOT_FOUND);
		}
		
		/**
		 * Saving Removed Device Token
		 */
		userToken.updateTimestamp();
		userTokenRepository.save(userToken);
		
		return new SimpleResponseDTO(TokenConstant.LOGOUT_MSG);
	}


	/**
	 * Generating new tokens (Access + Refresh),
	 *
	 * and saving it in DB
	 *
	 * @param jwtModel
	 * @return TokenResponseDTO
	 */
	@Override
	public AuthTokenResponseDTO generateToken(JwtModel jwtModel) {
		UserToken userToken = getOrDefaultUserToken(jwtModel);

		String newAccessToken = jwtUtils.generateJwtToken(jwtModel, TokenType.ACCESS);
		String newRefreshToken = jwtUtils.generateJwtToken(jwtModel, TokenType.REFRESH);
		String lastRefreshToken = jwtUtils.generateJwtToken(jwtModel, TokenType.TEMP);

		/**
		 *
		 */
		userToken.removeDeviceToken(jwtModel.getDeviceId());
		userToken.addDeviceToken(new DeviceToken(jwtModel.getDeviceId(), newRefreshToken, lastRefreshToken));

		/**
		 * Saving New Access, Refresh Token
		 */
		userToken.updateTimestamp();
		userTokenRepository.save(userToken);
		return new AuthTokenResponseDTO(jwtModel.getUid(), newAccessToken, newRefreshToken);
	}

	/**
	 *
	 */
	@Override
	public AuthTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
		UserToken userToken = fetchUserToken(
				refreshTokenRequestDTO.getUid(),
				new AuthException(AuthError.SESSION_NOT_FOUND)
		);

		if(userToken.getEmail().equals(refreshTokenRequestDTO.getEmail()) == false) {
			throw new AuthException(AuthError.UID_EMAIL_NOT_MATCHING);
		}

		//
		return rotateAndGenerateRefreshToken(refreshTokenRequestDTO, userToken);
	}

	private AuthTokenResponseDTO rotateAndGenerateRefreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO, UserToken userToken) {
		
		/**
		 * It never intercepts any token that has already expired,
		 * 
		 * If and only if lastRefreshToken is valid and un-expired,
		 * then only we block that user, 
		 * in offence of replay action of tokens
		 */
		if(userToken.isLastRefreshToken(refreshTokenRequestDTO.getRefreshToken())) {
			System.out.println(CLASS_NAME + " - BLOCKED -- due to using Last refresh token");
			
			/**
			 * Invalidating the login for the deviceId found in the token
			 */
			invalidateDeviceToken(new LogoutRequestDTO(refreshTokenRequestDTO.getUid(), refreshTokenRequestDTO.getDeviceId()));
			
			throw new TokenException(TokenError.BLOCKED);
		}
		
		if(userToken.findTokenAndRotate(refreshTokenRequestDTO.getRefreshToken()) == false) {
			throw new AuthException(AuthError.SESSION_NOT_FOUND);
		}

		JwtModel jwtModel = new JwtModel(refreshTokenRequestDTO.getUid(), refreshTokenRequestDTO.getEmail(), refreshTokenRequestDTO.getDeviceId());

		String newAccessToken = jwtUtils.generateJwtToken(jwtModel, TokenType.ACCESS);
		String newRefreshToken = jwtUtils.generateJwtToken(jwtModel, TokenType.REFRESH);

		userToken.setNewRefreshToken(refreshTokenRequestDTO.getDeviceId(), newRefreshToken);

		/**
		 * Saving Rotated Refresh Token
		 */
		userToken.updateTimestamp();
		userTokenRepository.save(userToken);

		return new AuthTokenResponseDTO(refreshTokenRequestDTO.getUid(), newAccessToken, newRefreshToken);
	}
	
	

}
