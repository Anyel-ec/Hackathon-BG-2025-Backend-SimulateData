package com.guayaquil.hackathon.services.impl;

import com.guayaquil.hackathon.models.facebook.FacebookUser;
import com.guayaquil.hackathon.repositories.FacebookUserRepository;
import com.guayaquil.hackathon.services.interfaces.FacebookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class FacebookUserServiceImpl implements FacebookUserService {

    private final FacebookUserRepository facebookUserRepository;

    @Autowired
    public FacebookUserServiceImpl(FacebookUserRepository facebookUserRepository) {
        this.facebookUserRepository = facebookUserRepository;
    }

    @Override
    public Page<FacebookUser> getAllFacebookUsers(Pageable pageable) {
        return facebookUserRepository.findAll(pageable);
    }

    @Override
    public Optional<FacebookUser> findFacebookUserByEmail(String email) {
        return facebookUserRepository.findByEmail(email);
    }
}
