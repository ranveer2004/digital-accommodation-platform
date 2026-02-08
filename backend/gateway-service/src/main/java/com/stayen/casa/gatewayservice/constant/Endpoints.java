package com.stayen.casa.gatewayservice.constant;

public class Endpoints {


    public static class Auth {

        public static final String TEST = "/test";

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

    public static class User {
        public static final String BASE_URL = "/api/v1/users";

        public static final String PROFILE = "/profile";

        public static final String ADD_NEW_PROPERTY_ID = "/add-property-id";

        public static final String DELETE_PROPERTY_ID = "/delete-property-id";

        /**
         * Just for reference
         */
        public static final String PROFILE_EXISTS = "/{uid}/exists";
        public static final String PROPERTY_EXISTS = "/{uid}/property/{propertyId}/exists";
    }

    public static class Property {
        public static final String BASE_URL = "/api/v1/properties";

        public static final String PROPERTY_BY_ID_param = "/{propertyId}";

        public static final String PROPERTY_BY_OWNER = "/owner";

        public static final String IMAGES = "/images";

        public static final String SEARCH = "/search";

        public static final String AVAILABILITY = "/availability";

        public static final String MARK_PROPERTY_SOLD = "/mark-as-sold";

        public static final String MARK_PROPERTY_AVAILABLE = "/mark-as-available";

    }

}