package com.stayen.casa.gatewayservice.validator;

import com.stayen.casa.gatewayservice.constant.BodyConstant;
import com.stayen.casa.gatewayservice.enums.TokenError;
import com.stayen.casa.gatewayservice.exception.TokenException;

import java.util.Map;

public class RequestValidator {

    public static boolean validateUid(String uid, Map<String, Object> requestBody) {
        String uidInBody = requestBody.get(BodyConstant.UID).toString();

        if(uidInBody != null && uidInBody.equals(uid)) {
            return true;
        }

        /**
         * If UID from Token,
         * not matches with the UID from RequestBody,
         * then throwing TokenException with Invalid-Token message.
         */
        throw new TokenException(TokenError.INVALID_USER);
    }

    public static void filterOutUidEmail(Map<String, Object> requestBody) {
        requestBody.put(BodyConstant.UID, "");
        requestBody.put(BodyConstant.EMAIL, "");
    }

}
