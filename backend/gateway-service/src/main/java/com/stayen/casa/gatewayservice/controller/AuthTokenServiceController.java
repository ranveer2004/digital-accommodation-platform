package com.stayen.casa.gatewayservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stayen.casa.gatewayservice.constant.BodyConstant;
import com.stayen.casa.gatewayservice.constant.Endpoints;
import com.stayen.casa.gatewayservice.constant.EnvConstant;
import com.stayen.casa.gatewayservice.constant.UserContext;
import com.stayen.casa.gatewayservice.dto.AuthTokenResponseDTO;
import com.stayen.casa.gatewayservice.helper.RestTemplateHelper;
import com.stayen.casa.gatewayservice.model.User;
import com.stayen.casa.gatewayservice.validator.ResponseValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
//@PostMapping("/api/v1/auth/token/")
@RequestMapping(Endpoints.Token.BASE_URL)
public class AuthTokenServiceController {

    private final String appDomain;
    private final String authServiceDomain;
    private final RestTemplateHelper restTemplateHelper;

    public AuthTokenServiceController(EnvConstant envConstant, RestTemplateHelper restTemplateHelper) {
        this.appDomain = envConstant.getAppDomain();
        this.authServiceDomain = envConstant.getAuthServiceDomain();
        this.restTemplateHelper = restTemplateHelper;
    }


//    @PostMapping("/refresh")
    @PostMapping(Endpoints.Token.REFRESH_TOKEN)
    public ResponseEntity<?> refreshToken() {
        String url = authServiceDomain + Endpoints.Token.BASE_URL + Endpoints.Token.REFRESH_TOKEN;

        User loggedInUser = UserContext.getLoggedInUser();

        Map<String, Object> sendingPayload = new HashMap<>();
        sendingPayload.put(BodyConstant.UID, loggedInUser.getUid());
        sendingPayload.put(BodyConstant.EMAIL, loggedInUser.getEmail());
        sendingPayload.put(BodyConstant.DEVICE_ID, loggedInUser.getDeviceId());
        sendingPayload.put(BodyConstant.REFRESH_TOKEN, loggedInUser.getToken());

        System.out.println("Refreshing tokens of :: " + loggedInUser);
        System.out.println("Request Body :: " + sendingPayload);

//        return restTemplateHelper.POST(url, sendingPayload, String.class);
        ResponseEntity<AuthTokenResponseDTO> response = restTemplateHelper.POST(url, sendingPayload, AuthTokenResponseDTO.class);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(ResponseValidator.getHeaderWithJwtRefreshTokenCookies(appDomain, response))
                .body(response.getBody());
//        return ResponseEntity.ok("OK Refresh");
    }

}
