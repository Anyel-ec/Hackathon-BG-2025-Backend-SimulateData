package com.guayaquil.hackathon.controllers;

import com.guayaquil.hackathon.models.facebook.FacebookUser;
import com.guayaquil.hackathon.services.interfaces.FacebookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facebook-users")
public class FacebookUserController {

    private final FacebookUserService facebookUserService;

    @Autowired
    public FacebookUserController(FacebookUserService facebookUserService) {
        this.facebookUserService = facebookUserService;
    }

    @GetMapping
    public ResponseEntity<Page<FacebookUser>> getAllFacebookUsers(Pageable pageable) {
        Page<FacebookUser> page = facebookUserService.getAllFacebookUsers(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/search")
    public ResponseEntity<FacebookUser> getFacebookUserByEmail(@RequestParam("email") String email) {
        return facebookUserService.findFacebookUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
