package com.guayaquil.hackathon.controllers;

import com.guayaquil.hackathon.models.linkedin.ProfessionalProfile;
import com.guayaquil.hackathon.services.impl.ProfessionalProfileService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/professional-profiles")
public class ProfessionalProfileController {

    private static final Logger logger = LoggerFactory.getLogger(ProfessionalProfileController.class);
    private final ProfessionalProfileService profileService;

    @GetMapping
    public ResponseEntity<Page<ProfessionalProfile>> getAllProfiles(
            @RequestParam(defaultValue = "0") int page) {
        try {
            // Retrieve paginated professional profiles
            Pageable pageable = PageRequest.of(page, 5);
            Page<ProfessionalProfile> profiles = profileService.getAllProfiles(pageable);
            return ResponseEntity.ok(profiles);
        } catch (Exception e) {
            // Log error and return 500 response
            logger.error("Error fetching professional profiles: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalProfile> getProfileById(@PathVariable Long id) {
        try {
            // Retrieve profile by ID
            Optional<ProfessionalProfile> profile = profileService.getProfileById(id);
            return profile.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            // Log error and return 500 response
            logger.error("Error fetching professional profile by ID: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/generate")
    public ResponseEntity<ProfessionalProfile> generateFakeProfile() {
        try {
            // Generate a fake professional profile
            ProfessionalProfile newProfile = profileService.generateFakeProfile();
            return ResponseEntity.ok(newProfile);
        } catch (Exception e) {
            // Log error and return 500 response
            logger.error("Error generating fake professional profile: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
