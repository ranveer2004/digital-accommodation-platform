package com.stayen.casa.authenticationservice.service;

import com.stayen.casa.authenticationservice.constant.BodyConstant;
import com.stayen.casa.authenticationservice.constant.EnvConstant;
import com.stayen.casa.authenticationservice.constant.HeaderConstant;
import com.stayen.casa.authenticationservice.exception.EmailException;
import com.stayen.casa.authenticationservice.utils.RestTemplateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.rmi.server.LogStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EmailService {

    public static final String RESEND_API_URL = "https://api.resend.com/emails";

    public static final String FROM_EMAIL = "StayEn.Casa <no-reply@stayen.casa>";
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

//    public static final String SUPPORT_EMAIL = "support@stayen.casa";

    private final String resendApiKey;

    private final RestTemplateUtils restTemplateUtils;

    @Autowired
    public EmailService(EnvConstant envConstant, RestTemplateUtils restTemplateUtils) {
        this.resendApiKey = envConstant.getResendMailApiKey();
        this.restTemplateUtils = restTemplateUtils;
    }

    private ResponseEntity<String> sendEmail(String toEmail, String subject, String body) {
        try {
            Map<String, String> header = new HashMap<>();
            header.put(HeaderConstant.AUTHORIZATION, (HeaderConstant.BEARER_TOKEN_TYPE + resendApiKey));

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put(BodyConstant.FROM_EMAIL, FROM_EMAIL);
            requestBody.put(BodyConstant.TO_EMAIL, List.of(toEmail));
            requestBody.put(BodyConstant.SUBJECT_EMAIL, subject);
            requestBody.put(BodyConstant.BODY_EMAIL, body);

            return restTemplateUtils.POST(RESEND_API_URL, header, requestBody, String.class);
        } catch(Exception e) {
            throw new EmailException(e.getMessage());
        }
    }

    public ResponseEntity<String> sendSignupOTPEmail(String toEmail, String otp) {
        return sendEmail(toEmail, "Welcome to StayEnCasa", createSignupOtpEmailBody(otp));
    }

    private String createSignupOtpEmailBody(String otp) {
        return String.format(signupOtpEmailBodyTemplate, otp, LocalDateTime.now().getYear());
    }

    /**
     * Send Forgot Password Email
     *
     * @param toEmail
     * @param otp
     * @return
     */
    public ResponseEntity<String> sendForgotPasswordEmail(String toEmail, String otp) {
        String subject = "Password reset request : " + toEmail;
        return sendEmail(toEmail, subject, createOtpEmailBody(toEmail, otp));
//        Map<String, String> header = new HashMap<>();
//        header.put(HeaderConstant.AUTHORIZATION, (HeaderConstant.BEARER_TOKEN_TYPE + resendApiKey));
//
//        StringBuilder subjectBuilder = new StringBuilder();
//        subjectBuilder.append("Password reset request : ");
//        subjectBuilder.append(toEmail);
//
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put(BodyConstant.FROM_EMAIL, FROM_EMAIL);
//        requestBody.put(BodyConstant.TO_EMAIL, List.of(toEmail));
//        requestBody.put(BodyConstant.SUBJECT_EMAIL, subjectBuilder.toString());
//        requestBody.put(BodyConstant.BODY_EMAIL, createOtpEmailBody(toEmail, otp));
//
//        return restTemplateUtils.POST(RESEND_API_URL, header, requestBody, String.class);
    }

    private String createOtpEmailBody(String userEmail, String otp) {
        return String.format(resetPasswordOtpEmailBodyTemplate, userEmail, otp, LocalDateTime.now().getYear());
    }

    /**
     * Send Password Changed Email
     *
     * @param toEmail
     * @return
     */
    public ResponseEntity<String> sendPasswordChangedEmail(String toEmail, String newPassword) {
        String subject = "Password changed : " + toEmail;
        return sendEmail(toEmail, subject, createPasswordChangedEmailBody(toEmail, newPassword));
//        Map<String, String> header = new HashMap<>();
//        header.put(HeaderConstant.AUTHORIZATION, (HeaderConstant.BEARER_TOKEN_TYPE + resendApiKey));
//
//        StringBuilder subjectBuilder = new StringBuilder();
//        subjectBuilder.append("Password changed : ");
//        subjectBuilder.append(toEmail);
//
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put(BodyConstant.FROM_EMAIL, FROM_EMAIL);
//        requestBody.put(BodyConstant.TO_EMAIL, List.of(toEmail));
//        requestBody.put(BodyConstant.SUBJECT_EMAIL, subjectBuilder.toString());
//        requestBody.put(BodyConstant.BODY_EMAIL, createPasswordChangedEmailBody(toEmail, newPassword));
//
//        return restTemplateUtils.POST(RESEND_API_URL, header, requestBody, String.class);
    }

    private String createPasswordChangedEmailBody(String userEmail, String newPassword) {
        ZonedDateTime zonedCurrentTime = ZonedDateTime.now(ZoneId.systemDefault());
        String timestamp = zonedCurrentTime.format(DateTimeFormatter.ofPattern("EEEE, dd MMM, yyyy HH:mm:ss z X"));

        return String.format(passwordChangedEmailBodyTemplate, userEmail, newPassword, timestamp, zonedCurrentTime.getYear());
    }

    private final String signupOtpEmailBodyTemplate = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
          <meta charset="UTF-8">
          <title>StayEn.Casa - Verify Your Email</title>
          <style>
            body {
              font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
              background-color: #f4f4f4;
              margin: 0;
              padding: 0;
            }
            .container {
              max-width: 600px;
              margin: 40px auto;
              background-color: #ffffff;
              padding: 30px;
              border-radius: 8px;
              box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            }
            .logo {
              text-align: center;
              margin-bottom: 20px;
            }
            .logo img {
              width: 100px;
              height: auto;
            }
            .header {
              font-size: 24px;
              font-weight: bold;
              color: #2c3e50;
              text-align: center;
              margin-bottom: 20px;
            }
            .message {
              font-size: 16px;
              color: #333333;
              line-height: 1.6;
            }
            .otp {
              font-size: 32px;
              font-weight: bold;
              color: #1abc9c;
              text-align: center;
              margin: 30px 0;
              letter-spacing: 4px;
            }
            .footer {
              font-size: 12px;
              color: #888888;
              margin-top: 40px;
              text-align: center;
            }
          </style>
        </head>
        <body>
          <div class="container">
            <div class="logo">
              <img src="https://cesyukrzbjmngxwtcqax.supabase.co/storage/v1/object/public/stay-en-casa/default-icons/stay_en_casa-nobg.png" alt="StayEn.Casa Logo">
            </div>
            <div class="header">Verify Your Email</div>
        
            <div class="message">
              Hi there,<br><br>
              Welcome to StayEn.Casa! To complete your signup, please use the following OTP to verify your email address:
            </div>
        
            <div class="otp">%s</div>
        
            <div class="message">
              Enter the OTP and proceed with password creation process. If you did not try to create an account, please ignore this email.
            </div>
        
            <div class="footer">
              &copy; %d StayEn.Casa | All rights reserved.
            </div>
          </div>
        </body>
        </html>
    """;

    private final String resetPasswordOtpEmailBodyTemplate = """
    <!DOCTYPE html>
    <html lang="en">
    <head>
      <meta charset="UTF-8">
      <title>StayEn.Casa</title>
        <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .email-container {
          max-width: 600px;
          margin: auto;
          background-color: #ffffff;
          padding: 20px;
          border-radius: 8px;
          box-shadow: 0 0 10px rgba(0,0,0,0.05);
        }
        .logo {
            text-align: center;
            margin-bottom: 20px;
        }
        .logo img {
            max-width: 150px;
            height: auto;
        }
        .header {
            font-size: 24px;
            font-weight: bold;
            color: #2c3e50;
            margin-bottom: 10px;
            text-align: center;
        }
        .message {
            font-size: 16px;
            color: #333333;
            line-height: 1.6;
        }
        .otp {
            font-size: 28px;
            font-weight: bold;
            color: #1abc9c;
            text-align: center;
            margin: 30px 0;
            letter-spacing: 4px;
        }
        .footer {
            font-size: 12px;
            color: #888888;
            margin-top: 40px;
            text-align: center;
        }
      </style>
    </head>
    <body>
      <div class="email-container">
        
        <div class="logo">
          <img src="https://cesyukrzbjmngxwtcqax.supabase.co/storage/v1/object/public/stay-en-casa/default-icons/stay_en_casa-nobg.png" alt="StayEn.Casa Logo">
        </div>
        
        <div class="header">StayEn.Casa - Reset Your Password</div>
        
        <div class="content">
          <div class="message">
            Hello <strong>%s</strong>,<br><br>
            We received a request to reset your password. Please use the following OTP to proceed with resetting your account:
          </div>
    
          <div class="otp">%s</div>
    
          <div class="message">
            If you did not request a password reset, please ignore this email or contact support.
          </div>
    
          <p>Thank you,<br/>StayEnCasa Team</p>
        </div>
    
        <div class="footer">
            &copy; %d StayEn.Casa | All rights reserved.
        </div>
      </div>
    </body>
    </html>
    """;


    private final String passwordChangedEmailBodyTemplate = """
    <!DOCTYPE html>
    <html lang="en">
    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
      <style>
        body {
          font-family: Arial, sans-serif;
          background-color: #f5f7fa;
          margin: 0;
          padding: 0;
        }
        .email-container {
          max-width: 600px;
          margin: auto;
          background-color: #ffffff;
          padding: 20px;
          border-radius: 8px;
          box-shadow: 0 0 10px rgba(0,0,0,0.05);
        }
        .logo {
          text-align: center;
          margin-bottom: 20px;
        }
        .logo img {
          width: 100px;
          height: auto;
        }
        .content h2 {
          color: #333;
        }
        .content p {
          color: #555;
          line-height: 1.6;
        }
        .info-box {
          background-color: #f0f0f0;
          padding: 12px;
          border-radius: 5px;
          margin: 10px 0;
          font-family: monospace;
        }
        .footer {
          font-size: 12px;
          color: #888;
          text-align: center;
          margin-top: 20px;
        }
      </style>
    </head>
    <body>
      <div class="email-container">
        
        <div class="logo">
          <img src="https://cesyukrzbjmngxwtcqax.supabase.co/storage/v1/object/public/stay-en-casa/default-icons/stay_en_casa-nobg.png" alt="Stay En Casa Logo" />
        </div>
        
        <div class="content">
          <h2>Your StayEn.Casa password has been successfully updated</h2>
          <p>Hello %s,</p>
          <p>Your password has been changed. Please find the details below:</p>
    
          <div class="info-box">
            <strong>New Password:</strong> %s
          </div>
          <div class="info-box">
            <strong>Changed At:</strong> %s
          </div>
    
          <p>
            If you did not request this change, please contact our support at
            <a href="mailto:support@stayen.casa">support@stayen.casa</a> immediately.
          </p>
    
          <p>Thank you,<br/>StayEnCasa Team</p>
        </div>
        
        <div class="footer">
            &copy; %d StayEn.Casa | All rights reserved.
        </div>
      </div>
    </body>
    </html>
    """;



}
