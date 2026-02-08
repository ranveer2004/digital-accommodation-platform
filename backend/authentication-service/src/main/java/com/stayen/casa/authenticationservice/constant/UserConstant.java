package com.stayen.casa.authenticationservice.constant;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.stayen.casa.authenticationservice.enums.AuthError;
import com.stayen.casa.authenticationservice.enums.TokenError;
import com.stayen.casa.authenticationservice.exception.AuthException;
import com.stayen.casa.authenticationservice.exception.TokenException;
import com.stayen.casa.authenticationservice.model.User;

public class UserConstant {
	public static User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null || auth.getDetails() == null) {
            throw new AuthException(AuthError.SESSION_NOT_FOUND);
        }

        if((auth.getDetails() instanceof User) == false) {
            throw new TokenException(TokenError.INVALID_USER);
        }
        
        return (User)auth.getDetails();
	}
}
