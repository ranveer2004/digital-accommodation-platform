package com.stayen.casa.authenticationservice.service;

import com.stayen.casa.authenticationservice.dto.OtpPasswordDTO;

public interface OtpService {

    String generateSave6DigitOtp(String email);

    boolean verifyAndDeleteOtp(String userEmail, String userOtp);

}
