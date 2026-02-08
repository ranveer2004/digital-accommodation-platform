package com.stayen.casa.gatewayservice.controller;

import com.stayen.casa.gatewayservice.constant.Endpoints;
import com.stayen.casa.gatewayservice.constant.EnvConstant;
import com.stayen.casa.gatewayservice.constant.UserContext;
import com.stayen.casa.gatewayservice.dto.OwnerAndPropertyDTO;
import com.stayen.casa.gatewayservice.enums.ProfileError;
import com.stayen.casa.gatewayservice.exception.ProfileException;
import com.stayen.casa.gatewayservice.helper.RestTemplateHelper;
import com.stayen.casa.gatewayservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(Endpoints.Property.BASE_URL)
public class PropertyServiceController {

//    private final String appDomain;
    private final String propertyServiceDomain;
    private final String userServiceDomain;
    private final RestTemplateHelper restTemplateHelper;

    @Autowired
    public PropertyServiceController(EnvConstant envConstant, RestTemplateHelper restTemplateHelper) {
//        this.appDomain = envConstant.getAppDomain();
        this.propertyServiceDomain = envConstant.getPropertyServiceDomain();
        this.userServiceDomain = envConstant.getUserServiceDomain();
        this.restTemplateHelper = restTemplateHelper;
    }

    /**
     * Un-protected Endpoint -- Don't require JWT
     *
     * @return
     */
    // http://localhost:9093/api/v1/properties
    @GetMapping
    public ResponseEntity<?> fetchAllProperties() {
        String url = propertyServiceDomain
                + Endpoints.Property.BASE_URL;

        return restTemplateHelper.GET(url, null, String.class);
    }

    /**
     * Un-protected Endpoint -- Don't require JWT
     *
     * @param payloadReceived
     * @return
     */
    // http://localhost:9093/api/v1/properties/search
    @PostMapping(Endpoints.Property.SEARCH)
    public ResponseEntity<?> searchPropertyByField(@RequestBody Map<String, Object> payloadReceived) {
        String url = propertyServiceDomain
                + Endpoints.Property.BASE_URL
                + Endpoints.Property.SEARCH;

        return restTemplateHelper.POST(
                url,
                payloadReceived,
                String.class
        );
    }

    /**
     * Un-protected Endpoint -- Don't require JWT
     *
     */
    @GetMapping(Endpoints.Property.PROPERTY_BY_ID_param + Endpoints.Property.AVAILABILITY)
    public ResponseEntity<?> checkPropertyAvailability(@PathVariable String propertyId) {
        String url = propertyServiceDomain
                + Endpoints.Property.BASE_URL
                + "/" + propertyId
                + Endpoints.Property.AVAILABILITY;

        return restTemplateHelper.GET(
                url,
                null,
                Map.class
        );
    }

    /**
     *
     * ^^^^^^^^^^^
     * Above are un-protected endpoints
     *
     *
     *
     * Below are protected endpoints
     * vvvvvvvvvvv
     *
     */


    /**
     * Protected endpoint - require JWT in auth-header
     *
     * @param propertyId
     * @return
     */
    // http://localhost:9093/api/v1/properties/{propertyId}
    @GetMapping(Endpoints.Property.PROPERTY_BY_ID_param)
    public ResponseEntity<?> fetchPropertyById(@PathVariable String propertyId) {
        String url = propertyServiceDomain
                + Endpoints.Property.BASE_URL
                + "/"
                + propertyId;

        return restTemplateHelper.GET(
                url,
                null,
                String.class
        );
    }

    /**
     * Protected endpoint - require JWT in auth-header
     *
     */
    // http://localhost:9093/api/v1/properties/owner
    @GetMapping(Endpoints.Property.PROPERTY_BY_OWNER)
    public ResponseEntity<?> fetchPropertyById() {
        String ownerId = UserContext.getLoggedInUser().getUid();

        String url = propertyServiceDomain
                + Endpoints.Property.BASE_URL
                + Endpoints.Property.PROPERTY_BY_OWNER
                + "/"
                + ownerId;

        return restTemplateHelper.GET(
                url,
                null,
                String.class
        );
    }


    /**
     * Protected endpoint - require JWT in auth-header
     *
     * Flaws:
     * After adding property, if adding propertyId fails,
     * User will not get any property under "MyProperty" section
     *
     * @param payloadReceived
     * @return
     */
    // http://localhost:9093/api/v1/properties
    @PostMapping
    public ResponseEntity<?> postNewProperty(@RequestBody Map<String, Object> payloadReceived) {
        System.out.println("New Property called... ");
        User loggedInUser = UserContext.getLoggedInUser();

        String ownerId = loggedInUser.getUid();

        /**
         * Checking is user profile exist or not
         */
        String isUserExistsURL = userServiceDomain
                + Endpoints.User.BASE_URL
                + Endpoints.User.PROFILE
                + "/" + ownerId
                + "/exists";

        ResponseEntity<?> isUserExists = restTemplateHelper.GET(
                isUserExistsURL,
                null,
                String.class
        );
        if(!isUserExists.getStatusCode().is2xxSuccessful()) {
            throw new ProfileException(ProfileError.NO_PROFILE_FOUND);
        }

        /**
         * updating ownerId
         * in case of ownerId mismatch
         */
        payloadReceived.put("ownerId", ownerId);


        /**
         * Posting a new property, and getting response
         * newPropertyResponse ::
         * {
         *      ownerId: "",
         *      propertyId: "",
         * }
         */
       // http://localhost:9093/api/v1/properties/owner/{ownerId}
        String newPropertyUrl = propertyServiceDomain
                + Endpoints.Property.BASE_URL
                + "/owner/"
                + ownerId;

        ResponseEntity<OwnerAndPropertyDTO> newPropertyResponse = restTemplateHelper.POST(
                newPropertyUrl,
                payloadReceived,
                OwnerAndPropertyDTO.class
        );

        /**
         * If Property added successfully
         * then add propertyId to user's profile
         */
        if(newPropertyResponse.getStatusCode().is2xxSuccessful()) {
            String addPropertyIdToUserProfileUrl = userServiceDomain
                    + Endpoints.User.BASE_URL
                    + Endpoints.User.PROFILE
                    + Endpoints.User.ADD_NEW_PROPERTY_ID;

            restTemplateHelper.PUT(
                    addPropertyIdToUserProfileUrl,
                    null,
                    newPropertyResponse.getBody().getPayloadMap(),
                    OwnerAndPropertyDTO.class
            );
        }
        return newPropertyResponse;
    }

    /**
     * Protected endpoint - require JWT in auth-header
     *
     */
    @PutMapping(Endpoints.Property.PROPERTY_BY_ID_param + Endpoints.Property.IMAGES)
    public ResponseEntity<?> updatePropertyImages(@PathVariable String propertyId, @RequestBody List<String> imageUrls) {
        String url = propertyServiceDomain
                + Endpoints.Property.BASE_URL
                + "/" + propertyId
                + Endpoints.Property.IMAGES;

//        return ResponseEntity.ok(imageUrls);
        return restTemplateHelper.PUT(
                url,
                null,
                imageUrls,
                String.class
        );
    }

    /**
     * Protected endpoint - require JWT in auth-header
     *
     */
    @PutMapping(Endpoints.Property.PROPERTY_BY_ID_param)
    public ResponseEntity<?> updatePropertyById(@PathVariable String propertyId, @RequestBody Map<String, Object> payloadReceived) {
        User loggedInUser = UserContext.getLoggedInUser();

        String ownerId = loggedInUser.getUid();

        /**
         * Check ownerId and update property details
         */
        String url = propertyServiceDomain
                + Endpoints.Property.BASE_URL
                + "/" + propertyId
                + "/ownerId/" + ownerId;

        return restTemplateHelper.PUT(
                url,
                null,
                payloadReceived,
                String.class
        );
    }

    /**
     * Protected endpoint - require JWT in auth-header
     *
     */
    @DeleteMapping(Endpoints.Property.PROPERTY_BY_ID_param)
    public ResponseEntity<?> deletePropertyById(@PathVariable String propertyId) {
        User loggedInUser = UserContext.getLoggedInUser();

        String ownerId = loggedInUser.getUid();

        /**
         * Check if property exists in user's profile
         * and delete it
         */
        String propertyIdDeletionURL = userServiceDomain
                + Endpoints.User.BASE_URL
                + Endpoints.User.PROFILE
                + Endpoints.User.DELETE_PROPERTY_ID;

        ResponseEntity<OwnerAndPropertyDTO> isPropertyIdDeleted = restTemplateHelper.PUT(
                propertyIdDeletionURL,
                null,
                OwnerAndPropertyDTO.getOwnerIdAndPropertyPayload(ownerId, propertyId),
                OwnerAndPropertyDTO.class
        );

        /**
         * Delete Property Data
         */
        String propertyDeletionURL = propertyServiceDomain
                + Endpoints.Property.BASE_URL
                + "/"
                + propertyId;  // Endpoints.Property.PROPERTY_BY_ID_param;

        ResponseEntity<OwnerAndPropertyDTO> ownerAndPropertyResponse = restTemplateHelper.DELETE(
                propertyDeletionURL,
                null,
                OwnerAndPropertyDTO.class
        );

        return ownerAndPropertyResponse;
    }

    /**
     * Protected endpoint - require JWT in auth-header
     *
     */
    @PatchMapping(Endpoints.Property.PROPERTY_BY_ID_param + Endpoints.Property.MARK_PROPERTY_SOLD)
    public ResponseEntity<?> markAsSold(@PathVariable String propertyId) {
        User loggedInUser = UserContext.getLoggedInUser();

        String ownerId = loggedInUser.getUid();

        /**
         * Verify and Mark Property as sold
         */
        String markAsSoldURL = propertyServiceDomain
                + Endpoints.Property.BASE_URL
                + "/"
                + propertyId
                + Endpoints.Property.MARK_PROPERTY_SOLD;

        return restTemplateHelper.PUT(
                markAsSoldURL,
                null,
                Map.of("ownerId", ownerId),
                String.class
        );
    }

    /**
     * Protected endpoint - require JWT in auth-header
     *
     */
    @PatchMapping(Endpoints.Property.PROPERTY_BY_ID_param + Endpoints.Property.MARK_PROPERTY_AVAILABLE)
    public ResponseEntity<?> markAsAvailable(@PathVariable String propertyId) {
        User loggedInUser = UserContext.getLoggedInUser();

        String ownerId = loggedInUser.getUid();

        /**
         * Verify and Mark Property as sold
         */
        String markAsAvialableURL = propertyServiceDomain
                + Endpoints.Property.BASE_URL
                + "/"
                + propertyId
                + Endpoints.Property.MARK_PROPERTY_AVAILABLE;

        return restTemplateHelper.PUT(
                markAsAvialableURL,
                null,
                Map.of("ownerId", ownerId),
                String.class
        );
    }



}
