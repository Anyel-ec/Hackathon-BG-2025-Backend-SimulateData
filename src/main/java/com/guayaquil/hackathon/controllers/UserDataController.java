package com.guayaquil.hackathon.controllers;

import com.guayaquil.hackathon.models.google.UserData;
import com.guayaquil.hackathon.repositories.GoogleUserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user-data")
public class UserDataController {

    private final GoogleUserDataRepository userDataRepository;

    @Autowired
    public UserDataController(GoogleUserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    // Endpoint para listar datos paginados
    @GetMapping
    public ResponseEntity<Page<UserData>> getAllUserData(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserData> pageResult = userDataRepository.findAll(pageable);
        return ResponseEntity.ok(pageResult);
    }

    // Endpoint para buscar por email (basicInfo.email)
    @GetMapping("/search")
    public ResponseEntity<UserData> getUserDataByEmail(@RequestParam("email") String email) {
        Optional<UserData> userDataOptional = userDataRepository.findByUserBasicInfoEmail(email);
        return userDataOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
