package com.stayen.casa.authenticationservice.constant;

public class Endpoints {

    public static class Auth {
        public static final String BASE_URL = "/api/v1/auth";

        public static final String UID_EXISTS = "/{uid}/exists";

        public static final String LOGIN = "/login";

        public static final String SIGNUP_OTP = "/signup-otp";

        public static final String SIGNUP = "/signup";

        public static final String LOGOUT = "/logout";

        public static final String FORGOT_PASSWORD = "/forgot-password";

        public static final String VERIFY_AND_CHANGE_PASSWORD = "/change-password";
    }

    public static class Token {
        public static final String BASE_URL = "/api/v1/auth/token";

        public static final String REFRESH_TOKEN = "/refresh";
    }
}
