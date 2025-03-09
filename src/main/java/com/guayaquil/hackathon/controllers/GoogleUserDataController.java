package com.guayaquil.hackathon.controllers;

import com.guayaquil.hackathon.models.google.UserData;
import com.guayaquil.hackathon.repositories.GoogleUserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@RestController
@RequestMapping("/api/user-data")
public class GoogleUserDataController {

    private static final Logger logger = LoggerFactory.getLogger(GoogleUserDataController.class);
    private final GoogleUserDataRepository userDataRepository;

    @Autowired
    public GoogleUserDataController(GoogleUserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @GetMapping
    public ResponseEntity<Page<UserData>> getAllUserData(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        try {
            // Retrieve paginated user data
            Pageable pageable = PageRequest.of(page, size);
            Page<UserData> pageResult = userDataRepository.findAll(pageable);
            return ResponseEntity.ok(pageResult);
        } catch (Exception e) {
            // Log error and return 500 response
            logger.error("Error fetching user data: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<UserData> getUserDataByEmail(@RequestParam("email") String email) {
        try {
            // Search user data by email
            Optional<UserData> userDataOptional = userDataRepository.findByUserBasicInfoEmail(email);
            return userDataOptional.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            // Log error and return 500 response
            logger.error("Error searching user data by email: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
