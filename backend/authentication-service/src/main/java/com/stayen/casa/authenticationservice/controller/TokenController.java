package com.stayen.casa.authenticationservice.controller;

import com.stayen.casa.authenticationservice.constant.Endpoints;
import com.stayen.casa.authenticationservice.dto.RefreshTokenRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stayen.casa.authenticationservice.service.UserTokenService;

/**
 * <pre>
 * Auth Controller for handling 
 * 	1. login 
 * 	2. logout 
 * 	3. signup
 * </pre>
 */
@RestController
@RequestMapping(Endpoints.Token.BASE_URL)
public class TokenController {
	
	private final UserTokenService userTokenService;
	
	@Autowired
	public TokenController(UserTokenService userTokenService) {
		this.userTokenService = userTokenService;
	}

	@GetMapping("/test")
	public String test() {
		return "Hello World !!!";
	}
	
	@PostMapping(Endpoints.Token.REFRESH_TOKEN)
	public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
		return ResponseEntity.ok(userTokenService.refreshToken(refreshTokenRequestDTO));
	}
	
}
