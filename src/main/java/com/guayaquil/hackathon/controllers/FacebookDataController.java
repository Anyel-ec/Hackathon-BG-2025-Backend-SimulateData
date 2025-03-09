package com.guayaquil.hackathon.controllers;

import com.guayaquil.hackathon.models.facebook.FacebookUser;
import com.guayaquil.hackathon.services.interfaces.FacebookDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
@RestController
public class FacebookDataController {

    private static final Logger logger = LoggerFactory.getLogger(FacebookDataController.class);

    @Autowired
    private FacebookDataService facebookDataService;

    @GetMapping("/api/fake-facebook-data")
    public FacebookUser getFakeFacebookData() {
        try {
            // Generate fake Facebook user data
            return facebookDataService.generateFakeData();
        } catch (Exception e) {
            // Log error and return null
            logger.error("Error generating fake Facebook data: {}", e.getMessage(), e);
            return null;
        }
    }
}
