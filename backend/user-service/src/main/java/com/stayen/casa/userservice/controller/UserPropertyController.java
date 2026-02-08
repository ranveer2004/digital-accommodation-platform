//package com.stayen.casa.userservice.controller;
//
//import com.stayen.casa.userservice.constant.Endpoints;
//import com.stayen.casa.userservice.dto.NewPropertyDTO;
//import com.stayen.casa.userservice.service.UserProfileService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(Endpoints.USER_BASE_URL)
//public class UserPropertyController {
//
//    private final UserProfileService userProfileService;
//
//    @Autowired
//    public UserPropertyController(UserProfileService userProfileService) {
//        this.userProfileService = userProfileService;
//    }
//
//    @PatchMapping(Endpoints.ADD_NEW_PROPERTY)
//    public ResponseEntity<?> addNewPropertyId(NewPropertyDTO newPropertyDTO) {
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(userProfileService.addNewPropertyId(newPropertyDTO));
//    }
//
//}
