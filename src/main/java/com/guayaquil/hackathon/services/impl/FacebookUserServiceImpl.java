package com.guayaquil.hackathon.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guayaquil.hackathon.models.facebook.FacebookUser;
import com.guayaquil.hackathon.repositories.FacebookUserRepository;
import com.guayaquil.hackathon.services.interfaces.FacebookUserService;
import com.guayaquil.hackathon.utils.JsonFlattener;
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
    public Optional<String> findFacebookUserById(Long id) {
        return facebookUserRepository.findById(id)
                .map(user -> {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        String json = objectMapper.writeValueAsString(user);
                        return JsonFlattener.flattenFacebookUser(json);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("Error procesando JSON", e);
                    }
                });
    }


    @Override
    public Optional<String> findFacebookUserByEmail(String email) {
        return facebookUserRepository.findByEmail(email)
                .map(user -> {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        String json = objectMapper.writeValueAsString(user);
                        return JsonFlattener.flattenFacebookUser(json);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("Error procesando JSON", e);
                    }
                });
    }

}
