package com.stayen.casa.userservice.exception;

import com.stayen.casa.userservice.enums.ProfileError;
import lombok.Getter;

@Getter
public class ProfileException extends RuntimeException {

    ProfileError error;

    public ProfileException(ProfileError error) {
        super(error.getMessage());
        this.error = error;
    }

}
