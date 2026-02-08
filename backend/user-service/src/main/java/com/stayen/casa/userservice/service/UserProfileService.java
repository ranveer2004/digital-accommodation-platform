package com.stayen.casa.userservice.service;

import com.stayen.casa.userservice.dto.OwnerAndPropertyDTO;
import com.stayen.casa.userservice.dto.SimpleResponseDTO;
import com.stayen.casa.userservice.dto.UserProfileDTO;
import com.stayen.casa.userservice.model.User;

public interface UserProfileService {

    SimpleResponseDTO isProfileExists(String uid);

//    SimpleResponseDTO isPropertyExists(String uid, String propertyId);

    UserProfileDTO fetchUserProfile(String uid);

    UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO);

    UserProfileDTO updateUserProfile(User loggedInUser, UserProfileDTO updatedUserProfileDTO);

    OwnerAndPropertyDTO addNewPropertyId(OwnerAndPropertyDTO ownerAndPropertyDTO);

    OwnerAndPropertyDTO deletePropertyId(OwnerAndPropertyDTO ownerAndPropertyDTO);

}
