package com.stayen.casa.authenticationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * Credential Controller for handling 
 * 	1. change password 
 * </pre>
 */
@RestController
@RequestMapping("/api/credential")
public class CredentialController {

	@GetMapping("/test")
	public String test() {
		return "Hello World !!!";
	}
}
