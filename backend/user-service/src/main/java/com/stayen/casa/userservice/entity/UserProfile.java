package com.stayen.casa.userservice.entity;

import com.stayen.casa.userservice.dto.OwnerAndPropertyDTO;
import com.stayen.casa.userservice.dto.UserProfileDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "user_profiles")
public class UserProfile extends BaseTimestampEntity {

    @MongoId
    private String uid;

    @Indexed(unique = true)
    private String email;

    private String name;

    private String phoneNo;

    private String address;

    private String photoUrl;

    private List<String> ownedPropertyId = new ArrayList<>();

    public UserProfile(UserProfileDTO userProfileDTO) {
        super(LocalDateTime.now(), LocalDateTime.now());
        this.uid = userProfileDTO.getUid();
        this.email = userProfileDTO.getEmail();
        this.name = userProfileDTO.getName();
        this.phoneNo = userProfileDTO.getPhoneNo();
        this.address = userProfileDTO.getAddress();
        this.photoUrl = userProfileDTO.getPhotoUrl();
    }

    public UserProfile(String uid, String email, String name, String phoneNo, String address, String photoUrl, List<String> ownedPropertyId) {
        super(LocalDateTime.now(), LocalDateTime.now());
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.phoneNo = phoneNo;
        this.address = address;
        this.photoUrl = photoUrl;
        this.ownedPropertyId = ownedPropertyId;
    }

    public UserProfile(String uid, String email, String name, String phoneNo, String address, String photoUrl, List<String> ownedPropertyId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.phoneNo = phoneNo;
        this.address = address;
        this.photoUrl = photoUrl;
        this.ownedPropertyId = ownedPropertyId;
    }

    /**
     * Used when user try to update their profile data
     *
     * Uid, Email cannot be updated
     */
    public void updateProfile(UserProfileDTO updatedUserProfileDTO) {
        this.name = updatedUserProfileDTO.getName();
        this.phoneNo = updatedUserProfileDTO.getPhoneNo();
        this.address = updatedUserProfileDTO.getAddress();
        this.photoUrl = updatedUserProfileDTO.getPhotoUrl();
        this.setUpdatedAt(LocalDateTime.now());
    }

    /**
     * Used when user checking if property exists
     */
    public boolean isPropertyIdExists(String propertyId) {
        return this.ownedPropertyId
                .stream()
                .anyMatch((propId) -> propertyId.equals(propId));
    }

    /**
     * Used when user post any new property
     *
     */
    public void addNewPropertyId(OwnerAndPropertyDTO ownerAndPropertyDTO) {
        this.ownedPropertyId.add(ownerAndPropertyDTO.getPropertyId());
        this.setUpdatedAt(LocalDateTime.now());
    }

    /**
     * Used when user delete any new property
     *
     */
    public boolean deletePropertyId(String propertyId) {
        return this.ownedPropertyId
                .removeIf((propId) ->  {
                    if(propId.equals(propertyId)) {
                        this.setUpdatedAt(LocalDateTime.now());
                        return true;
                    }
                    return false;
                });
    }
}
