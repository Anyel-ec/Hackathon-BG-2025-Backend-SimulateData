package com.guayaquil.hackathon.services.impl;

import com.guayaquil.hackathon.models.facebook.FacebookUser;
import com.guayaquil.hackathon.repositories.FacebookUserRepository;
import com.guayaquil.hackathon.services.interfaces.FacebookUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@AllArgsConstructor
@Service
public class FacebookUserServiceImpl implements FacebookUserService {

    private final FacebookUserRepository facebookUserRepository;

    @Override
    public Page<FacebookUser> getAllFacebookUsers(Pageable pageable) {
        // Retrieve all Facebook users with pagination
        return facebookUserRepository.findAll(pageable);
    }

    @Override
    public Optional<FacebookUser> findFacebookUserByEmail(String email) {
        // Search for a Facebook user by email
        return facebookUserRepository.findByEmail(email);
    }
}
