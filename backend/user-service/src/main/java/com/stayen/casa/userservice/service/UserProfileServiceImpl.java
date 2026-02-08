package com.stayen.casa.userservice.service;

import com.stayen.casa.userservice.dto.OwnerAndPropertyDTO;
import com.stayen.casa.userservice.dto.SimpleResponseDTO;
import com.stayen.casa.userservice.dto.UserProfileDTO;
import com.stayen.casa.userservice.entity.UserProfile;
import com.stayen.casa.userservice.enums.ProfileError;
import com.stayen.casa.userservice.exception.ProfileException;
import com.stayen.casa.userservice.model.User;
import com.stayen.casa.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserRepository userRepository;

    @Autowired
    public UserProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SimpleResponseDTO isProfileExists(String uid) {
        userRepository
                .findById(uid)
                .orElseThrow(() -> new ProfileException(ProfileError.NO_PROFILE_FOUND));

        return new SimpleResponseDTO("User exists.");
    }

//    @Override
//    public SimpleResponseDTO isPropertyExists(String uid, String propertyId) {
//        UserProfile userProfile = userRepository
//                .findById(uid)
//                .orElseThrow(() -> new ProfileException(ProfileError.NO_PROFILE_FOUND));
//
//        if(userProfile.isPropertyIdExists(propertyId) == false) {
//            throw new ProfileException(ProfileError.NO_PROPERTY_FOUND);
//        }
//
//        return new SimpleResponseDTO("Property exists.");
//    }

    @Override
    public UserProfileDTO fetchUserProfile(String uid) {
        UserProfile userProfile = userRepository
                .findById(uid)
                .orElseThrow(() -> new ProfileException(ProfileError.NO_PROFILE_FOUND));

        return new UserProfileDTO(userProfile);
    }

    @Override
    public UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO) {
        Optional<UserProfile> anyPrevProfile = userRepository.findById(userProfileDTO.getUid());
        if(anyPrevProfile.isPresent()) {
            throw new ProfileException(ProfileError.PROFILE_ALREADY_CREATED);
        }

        UserProfile userProfile = new UserProfile(userProfileDTO);
        userProfile = userRepository.save(userProfile);
        return new UserProfileDTO(userProfile);
    }

    @Override
    public UserProfileDTO updateUserProfile(User loggedInUser, UserProfileDTO updatedUserProfileDTO) {
        Optional<UserProfile> optionalProfile = userRepository
                .findById(loggedInUser.getUid());

        UserProfile userProfile;
        if(optionalProfile.isPresent()) {
            userProfile = optionalProfile.get();

            userProfile.updateProfile(updatedUserProfileDTO);
        } else {
            updatedUserProfileDTO.setUid(loggedInUser.getUid());
            updatedUserProfileDTO.setEmail(loggedInUser.getEmail());

            userProfile = new UserProfile(updatedUserProfileDTO);
        }

        userProfile = userRepository.save(userProfile);
        return new UserProfileDTO(userProfile);
    }

    /**
     *
     */
    @Override
    public OwnerAndPropertyDTO addNewPropertyId(OwnerAndPropertyDTO ownerAndPropertyDTO) {
        UserProfile userProfile = userRepository
                .findById(ownerAndPropertyDTO.getOwnerId())
                .orElseThrow(() -> new ProfileException(ProfileError.NO_PROFILE_FOUND));

        userProfile.addNewPropertyId(ownerAndPropertyDTO);

        userRepository.save(userProfile);
        return ownerAndPropertyDTO;
    }

    /**
     *
     */
    @Override
    public OwnerAndPropertyDTO deletePropertyId(OwnerAndPropertyDTO ownerAndPropertyDTO) {
        UserProfile userProfile = userRepository
                .findById(ownerAndPropertyDTO.getOwnerId())
                .orElseThrow(() -> new ProfileException(ProfileError.NO_PROFILE_FOUND));

        boolean isDeleted = userProfile.deletePropertyId(ownerAndPropertyDTO.getPropertyId());
        if(!isDeleted) {
            throw new ProfileException(ProfileError.NO_PROPERTY_ID_FOUND);
        }

        userRepository.save(userProfile);
        return ownerAndPropertyDTO;
    }
}
