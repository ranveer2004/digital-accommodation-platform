package com.stayen.casa.authenticationservice.service;

import com.stayen.casa.authenticationservice.dto.*;

public interface UserCredentialService {

	SimpleResponseDTO isValidUser(String uid);

	SimpleResponseDTO generateSignupOTP(EmailDTO emailDTO);

	AuthTokenResponseDTO signupUser(SignupRequestDTO signupRequestDTO);
	
	AuthTokenResponseDTO loginUser(LoginRequestDTO loginRequestDTO);
	
	SimpleResponseDTO logoutUser(LogoutRequestDTO logoutRequestDTO);

	SimpleResponseDTO forgotPassword(EmailDTO emailDTO);

	SimpleResponseDTO verifyOTPAndChangePassword(OtpPasswordDTO otpPasswordDTO);

}
