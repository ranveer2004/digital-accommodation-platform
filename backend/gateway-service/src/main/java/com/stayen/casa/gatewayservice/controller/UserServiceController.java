package com.stayen.casa.gatewayservice.controller;

import com.stayen.casa.gatewayservice.constant.Endpoints;
import com.stayen.casa.gatewayservice.constant.EnvConstant;
import com.stayen.casa.gatewayservice.constant.HeaderConstant;
import com.stayen.casa.gatewayservice.constant.UserContext;
import com.stayen.casa.gatewayservice.helper.RestTemplateHelper;
import com.stayen.casa.gatewayservice.model.User;
import com.stayen.casa.gatewayservice.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/api/v1/users")
@RequestMapping(Endpoints.User.BASE_URL)
public class UserServiceController {

    private final String userServiceDomain;

    private final RestTemplateHelper restTemplateHelper;

    @Autowired
    public UserServiceController(EnvConstant envConstant, RestTemplateHelper restTemplateHelper) {
        this.userServiceDomain = envConstant.getUserServiceDomain();
        this.restTemplateHelper = restTemplateHelper;
    }

    /**
     * <pre>
     * URI : /api/v1/users/profile
     * Method : <b> GET </b>
     * Headers : Authorization (required - JWT - AccessToken)
     * Payload : {}
     * Response : UserProfileDTO (from User-Service)
     * </pre>
     *
     * @return ResponseEntity
     */
    @GetMapping(Endpoints.User.PROFILE)
    public ResponseEntity<?> getProfile() {
        String url = userServiceDomain + Endpoints.User.BASE_URL + Endpoints.User.PROFILE;

        User loggedInUser = UserContext.getLoggedInUser();

        Map<String, String> requestHeader = new HashMap<>();
        requestHeader.put(HeaderConstant.USER_ID, loggedInUser.getUid());
        requestHeader.put(HeaderConstant.USER_EMAIL, loggedInUser.getEmail());
        requestHeader.put(HeaderConstant.USER_DEVICE_ID, loggedInUser.getDeviceId());

        return restTemplateHelper.GET(url, requestHeader, String.class);
//        return ResponseEntity.ok().body("profile -- fetching");
    }

    /**
     * <pre>
     * URI : /api/v1/users/profile
     * Method : <b> POST </b>
     * Headers : Authorization (required - JWT - AccessToken)
     * Payload : {
     *     "uid": "uid-of-user",
     *     "email": "example@gmail.com",
     *     "name": "Name of the user",
     *     "phoneNo": "Phone Number",
     *     "address": "Complete Address",
     *     "photoUrl": "photo-url--from-storage"
     * }
     * Response : UserProfileDTO (newly created profile from User-Service)
     * </pre>
     *
     * @return ResponseEntity
     */
    @PostMapping(Endpoints.User.PROFILE)
    public ResponseEntity<?> createProfile(@RequestBody Map<String, Object> requestBody) {
        String url = userServiceDomain + Endpoints.User.BASE_URL + Endpoints.User.PROFILE;

        User loggedInUser = UserContext.getLoggedInUser();

        RequestValidator.validateUid(loggedInUser.getUid(), requestBody);

        return restTemplateHelper.POST(url, requestBody, String.class);
//        return ResponseEntity.ok().body("profile -- creation");
    }

    /**
     * <pre>
     * URI : /api/v1/users/profile
     * Method : <b> PUT </b>
     * Headers : Authorization (required - JWT - AccessToken)
     * Payload : {
     *     "name": "Name to be updated",
     *     "phoneNo": "Phone Number",
     *     "address": "Complete Address",
     *     "photoUrl": "photo-url--from-storage"
     * }
     * Response : UserProfileDTO (updated profile from User-Service)
     * </pre>
     *
     * @return ResponseEntity
     */
    @PutMapping(Endpoints.User.PROFILE)
    public ResponseEntity<?> updateProfile(@RequestBody Map<String, Object> requestBody) {
        String url = userServiceDomain + Endpoints.User.BASE_URL + Endpoints.User.PROFILE;

        User loggedInUser = UserContext.getLoggedInUser();

        Map<String, String> requestHeader = new HashMap<>();
        requestHeader.put(HeaderConstant.USER_ID, loggedInUser.getUid());
        requestHeader.put(HeaderConstant.USER_EMAIL, loggedInUser.getEmail());
        requestHeader.put(HeaderConstant.USER_DEVICE_ID, loggedInUser.getDeviceId());

        RequestValidator.filterOutUidEmail(requestBody);

        return restTemplateHelper.PUT(url, requestHeader, requestBody, String.class);
//        return ResponseEntity.ok().body("profile -- updation");
    }


}
