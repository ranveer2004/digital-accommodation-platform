package com.stayen.casa.authenticationservice.service;

import java.util.Optional;
import java.util.UUID;

import com.stayen.casa.authenticationservice.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.stayen.casa.authenticationservice.entity.UserCredential;
import com.stayen.casa.authenticationservice.enums.AuthError;
import com.stayen.casa.authenticationservice.exception.AuthException;
import com.stayen.casa.authenticationservice.model.JwtModel;
import com.stayen.casa.authenticationservice.repository.UserCredentialRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialServiceImpl implements UserCredentialService {
	private static final String CLASS_NAME = UserCredentialServiceImpl.class.getSimpleName();

	private final UserCredentialRepository userCredentialRepository;
	private final UserTokenService userTokenService;
	private final PasswordEncoder passwordEncoder;

	private final EmailService emailService;
	private final OtpService otpService;

	@Autowired
	public UserCredentialServiceImpl(UserTokenService userTokenService,
			UserCredentialRepository userCredentialRepository, PasswordEncoder passwordEncoder, EmailService emailService, OtpService otpService) {
		this.userTokenService = userTokenService;
		this.userCredentialRepository = userCredentialRepository;
		this.passwordEncoder = passwordEncoder;
		this.emailService = emailService;
		this.otpService = otpService;
	}

	@Override
	public SimpleResponseDTO isValidUser(String uid) {
		userCredentialRepository
				.findById(uid)
				.orElseThrow(() -> new AuthException(AuthError.NO_ACCOUNT_FOUND));
		return new SimpleResponseDTO("User exists.");
	}

	/**
	 * 
	 * Logout Process 
	 * 
	 */
	public SimpleResponseDTO logoutUser(LogoutRequestDTO logoutRequestDTO) {
		return userTokenService.invalidateDeviceToken(logoutRequestDTO);
	}

	/**
	 *
	 * Forgot Password
	 *
	 * @param emailDTO
	 * @return
	 */
	@Override
	public SimpleResponseDTO forgotPassword(EmailDTO emailDTO) {
		userCredentialRepository
				.findByEmail(emailDTO.getEmail())
				.orElseThrow(() -> new AuthException(AuthError.NO_ACCOUNT_FOUND));

		String otp = otpService.generateSave6DigitOtp(emailDTO.getEmail());

		emailService.sendForgotPasswordEmail(emailDTO.getEmail(), otp);

		return new SimpleResponseDTO("Password reset OTP sent successfully.");
	}

	/**
	 * Verify OTP and change password
	 *
	 * @param otpPasswordDTO
	 * @return
	 */
	@Override
	public SimpleResponseDTO verifyOTPAndChangePassword(OtpPasswordDTO otpPasswordDTO) {
		otpService.verifyAndDeleteOtp(otpPasswordDTO.getEmail(), otpPasswordDTO.getOtp());

		UserCredential userCredential = userCredentialRepository.findByEmail(otpPasswordDTO.getEmail())
				.orElseThrow(() -> new AuthException(AuthError.NO_ACCOUNT_FOUND));

		userCredential.updatePassword(passwordEncoder.encode(otpPasswordDTO.getNewPassword()));
		userCredentialRepository.save(userCredential);

		emailService.sendPasswordChangedEmail(otpPasswordDTO.getEmail(), otpPasswordDTO.getNewPassword());

		return new SimpleResponseDTO("Password updated successfully.");
	}

	/**
	 *
	 */
	@Override
	public AuthTokenResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {
		// Getting verified user credentials
		UserCredential credential = verifyUser(loginRequestDTO);
		System.out.println(CLASS_NAME + " : user verified");
		
		JwtModel jwtModel = new JwtModel(credential.getUid(), loginRequestDTO.getEmail(), loginRequestDTO.getDeviceId());
		
		AuthTokenResponseDTO authResponseDTO = userTokenService.generateToken(jwtModel);
		System.out.println(CLASS_NAME + " : token generated");
		return authResponseDTO;
	}

	@Override
	public SimpleResponseDTO generateSignupOTP(EmailDTO emailDTO) {
		Optional<UserCredential> userCredential = userCredentialRepository.findByEmail(emailDTO.getEmail());

		if(userCredential.isPresent()) {
			throw new AuthException(AuthError.ACCOUNT_ALREADY_EXIST);
		}

		/**
		 * Before generating,
		 * we can check if OTP already generated or not,
		 * to prevent OTP abuse
		 *
		 * Pending for future update
		 */
		String otp = otpService.generateSave6DigitOtp(emailDTO.getEmail());

		emailService.sendSignupOTPEmail(emailDTO.getEmail(), otp);

		return new SimpleResponseDTO("OTP sent successfully on the given email, please continue with account creation process.");
	}

	/**
	 * 
	 */
	@Override
	public AuthTokenResponseDTO signupUser(SignupRequestDTO signupRequestDTO) {
		otpService.verifyAndDeleteOtp(signupRequestDTO.getEmail(), signupRequestDTO.getOtp());

		UserCredential credential = createNewUserCredential(signupRequestDTO);
		
		System.out.println(CLASS_NAME + "New User Creation : ");
		System.out.println(CLASS_NAME + "User cred : " + credential);
		userCredentialRepository.save(credential);
		
		JwtModel jwtModel = new JwtModel(credential.getUid(), credential.getEmail(), signupRequestDTO.getDeviceId());
		
		AuthTokenResponseDTO authResponseDTO = userTokenService.generateToken(jwtModel);
		return authResponseDTO;
	}
	
	/**
	 * Validates the credentials of user with 
	 * the raw information entered by user on front-end
	 * 
	 * @param loginRequestDTO
	 * @return UserCredential validated user credential
	 * @throws AuthException if no account found with the given email / if credential not matched with the credential stored in database
	 */
	private UserCredential verifyUser(LoginRequestDTO loginRequestDTO) {
		Optional<UserCredential> credential = userCredentialRepository.findByEmail(loginRequestDTO.getEmail());
		
		if(credential.isEmpty()) {
			throw new AuthException(AuthError.NO_ACCOUNT_FOUND);
		}
		
		if(!passwordEncoder.matches(loginRequestDTO.getPassword(), credential.get().getPasswordHash())) {
			throw new AuthException(AuthError.INVALID_CREDENTIAL);
		}
		
		return credential.get();
	}
	
	
	/**
	 * 
	 * Login Process Above ^
	 * 
	 * 
	 * Registration Process Below v
	 * 
	 */
	
	private UserCredential createNewUserCredential(SignupRequestDTO signupRequestDTO) {
		Optional<UserCredential> credential = userCredentialRepository.findByEmail(signupRequestDTO.getEmail());

		if(credential.isPresent()) {
			throw new AuthException(AuthError.ACCOUNT_ALREADY_EXIST);
		}
		
		// TODO : optional, check if user is disabled or not
		
		/**
		 * Creating new User based on 
		 * Details received from registration form
		 */
		String newUid = UUID.nameUUIDFromBytes(signupRequestDTO.getEmail().getBytes()).toString();
		String passwordHash = passwordEncoder.encode(signupRequestDTO.getPassword());
		
		return new UserCredential(newUid, signupRequestDTO.getEmail(), passwordHash);
	}

	
}
