package com.stayen.casa.authenticationservice.controller;

import com.stayen.casa.authenticationservice.constant.Endpoints;
import com.stayen.casa.authenticationservice.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stayen.casa.authenticationservice.service.UserCredentialService;

/**
 * <pre>
 * Auth Controller for handling 
 * 	1. login 
 * 	2. logout 
 * 	3. signup
 * 	4. forgot password
 * 	5. change password
 * </pre>
 */
@RestController
@RequestMapping(Endpoints.Auth.BASE_URL) // http://localhost:9091/api/v1/auth/XXX
public class AuthController {

	private final UserCredentialService userCredentialService;
	
	@Autowired
	public AuthController(UserCredentialService userCredentialService) {
		this.userCredentialService = userCredentialService;
	}
	
	@GetMapping("/test")
	public String test() {
		return "Hello World !!!";
	}

	// -----   /{uid}/exists
	@GetMapping(Endpoints.Auth.UID_EXISTS)
	public ResponseEntity<?> isValidUser(@PathVariable String uid) {
		return ResponseEntity
				.ok(userCredentialService.isValidUser(uid));
	}

	@PostMapping(Endpoints.Auth.LOGIN)
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		System.out.println(loginRequestDTO);
		return ResponseEntity
				.ok(userCredentialService.loginUser(loginRequestDTO));
	}

	@PostMapping(Endpoints.Auth.SIGNUP_OTP)
	public ResponseEntity<?> generateSignupOtp(@RequestBody EmailDTO emailDTO) {
		return ResponseEntity
				.ok(userCredentialService.generateSignupOTP(emailDTO));
	}
	
	@PostMapping(Endpoints.Auth.SIGNUP)
	public ResponseEntity<?> signup(@RequestBody SignupRequestDTO signupRequestDTO) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(userCredentialService.signupUser(signupRequestDTO));
	}
	
	@PostMapping(Endpoints.Auth.LOGOUT)
	public ResponseEntity<?> logout(@RequestBody LogoutRequestDTO logoutRequestDTO) {
		return ResponseEntity
				.ok(userCredentialService.logoutUser(logoutRequestDTO));
	}

	@PostMapping(Endpoints.Auth.FORGOT_PASSWORD)
	public ResponseEntity<?> forgotPassword(@RequestBody EmailDTO emailDTO) {
		return ResponseEntity
				.ok(userCredentialService.forgotPassword(emailDTO));
	}

	@PutMapping(Endpoints.Auth.VERIFY_AND_CHANGE_PASSWORD)
	public ResponseEntity<?> verifyOTPAndChangePassword(@RequestBody OtpPasswordDTO otpPasswordDTO) {
		return ResponseEntity
				.ok(userCredentialService.verifyOTPAndChangePassword(otpPasswordDTO));
	}


	
}
