package com.stayen.casa.userservice.controller;

import com.stayen.casa.userservice.constant.Endpoints;
import com.stayen.casa.userservice.constant.UserContext;
import com.stayen.casa.userservice.dto.OwnerAndPropertyDTO;
import com.stayen.casa.userservice.dto.UserProfileDTO;
import com.stayen.casa.userservice.model.User;
import com.stayen.casa.userservice.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoints.USER_BASE_URL + Endpoints.PROFILE)
public class UserProfileController {

	private final UserProfileService userProfileService;

	@Autowired
	public UserProfileController(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	@GetMapping("/test")
	public String test() {
		return "Request completed !!!";
	}

	// -----   /{uid}/exists
	@GetMapping(Endpoints.PROFILE_EXISTS)
	public ResponseEntity<?> isValidUser(@PathVariable String uid) {
		return ResponseEntity
				.ok(userProfileService.isProfileExists(uid));
	}

//	// -----   /{uid}/property/{propertyId}/exists
//	@GetMapping(Endpoints.PROPERTY_EXISTS)
//	public ResponseEntity<?> isPropertyExists(@PathVariable String uid, @PathVariable String propertyId) {
//		return ResponseEntity
//				.ok(userProfileService.isPropertyExists(uid, propertyId));
//	}

	@GetMapping
	public ResponseEntity<?> fetchUserDetail() {
		User loggedInUser = UserContext.getLoggedInUser();

		return ResponseEntity
				.ok(userProfileService.fetchUserProfile(loggedInUser.getUid()));
	}

	@PostMapping
	public ResponseEntity<?> createUserProfile(@RequestBody UserProfileDTO userProfileDTO) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(userProfileService.createUserProfile(userProfileDTO));
	}

	@PutMapping
	public ResponseEntity<?> updateUserProfile(@RequestBody UserProfileDTO userProfileDTO) {
		User loggedInUser = UserContext.getLoggedInUser();

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(userProfileService.updateUserProfile(loggedInUser, userProfileDTO));
	}

	@PutMapping(Endpoints.ADD_NEW_PROPERTY_ID)
	public ResponseEntity<?> addNewPropertyId(@RequestBody OwnerAndPropertyDTO ownerAndPropertyDTO) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(userProfileService.addNewPropertyId(ownerAndPropertyDTO));
	}

	@PutMapping(Endpoints.DELETE_PROPERTY_ID)
	public ResponseEntity<?> deletePropertyId(@RequestBody OwnerAndPropertyDTO ownerAndPropertyDTO) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(userProfileService.deletePropertyId(ownerAndPropertyDTO));
	}

}
