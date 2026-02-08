package com.stayen.casa.userservice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stayen.casa.userservice.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"uid", "email", "name", "phoneNo", "address", "photoUrl", "createdAt", "updatedAt"})
public class UserProfileDTO extends BaseTimestampDTO {

    private String uid;

    private String email;

    private String name;

    private String phoneNo;

    private String address;

    private String photoUrl;

    public UserProfileDTO(UserProfile userProfile) {
        super(userProfile.getCreatedAt(), userProfile.getUpdatedAt());
        this.uid = userProfile.getUid();
        this.email = userProfile.getEmail();
        this.name = userProfile.getName();
        this.phoneNo = userProfile.getPhoneNo();
        this.address = userProfile.getAddress();
        this.photoUrl = userProfile.getPhotoUrl();
    }

    public UserProfileDTO(String uid, String email, String name, String phoneNo, String address, String photoUrl) {
        super(LocalDateTime.now(), LocalDateTime.now());
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.phoneNo = phoneNo;
        this.address = address;
        this.photoUrl = photoUrl;
    }

    public UserProfileDTO(String uid, String email, String name, String phoneNo, String address, String photoUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.phoneNo = phoneNo;
        this.address = address;
        this.photoUrl = photoUrl;
    }

}
