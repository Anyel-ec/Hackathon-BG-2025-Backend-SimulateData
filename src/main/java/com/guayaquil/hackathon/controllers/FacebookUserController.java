package com.guayaquil.hackathon.controllers;

import com.guayaquil.hackathon.models.facebook.FacebookUser;
import com.guayaquil.hackathon.services.interfaces.FacebookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/facebook-users")
public class FacebookUserController {

    private static final Logger logger = LoggerFactory.getLogger(FacebookUserController.class);
    private final FacebookUserService facebookUserService;

    @Autowired
    public FacebookUserController(FacebookUserService facebookUserService) {
        this.facebookUserService = facebookUserService;
    }

    @GetMapping
    public ResponseEntity<Page<FacebookUser>> getAllFacebookUsers(Pageable pageable) {
        try {
            // Retrieve all Facebook users with pagination
            Page<FacebookUser> page = facebookUserService.getAllFacebookUsers(pageable);
            return ResponseEntity.ok(page);
        } catch (Exception e) {
            // Log error and return 500 response
            logger.error("Error fetching Facebook users: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<FacebookUser> getFacebookUserByEmail(@RequestParam("email") String email) {
        try {
            // Search for a Facebook user by email
            return facebookUserService.findFacebookUserByEmail(email)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            // Log error and return 500 response
            logger.error("Error searching Facebook user by email: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
