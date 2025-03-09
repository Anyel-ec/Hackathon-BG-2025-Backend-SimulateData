package com.guayaquil.hackathon.controllers;

import com.guayaquil.hackathon.models.facebook.FacebookUser;
import com.guayaquil.hackathon.services.interfaces.FacebookDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
@RestController
public class FacebookDataController {

    @Autowired
    private FacebookDataService facebookDataService;


    @GetMapping("/api/fake-facebook-data")
    public FacebookUser getFakeFacebookData() {
        return facebookDataService.generateFakeData();
    }
}
