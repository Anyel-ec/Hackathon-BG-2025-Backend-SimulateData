package com.guayaquil.hackathon.services.interfaces;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
import com.guayaquil.hackathon.models.facebook.FacebookUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface FacebookUserService {
    Page<FacebookUser> getAllFacebookUsers(Pageable pageable);
    Optional<FacebookUser> findFacebookUserByEmail(String email);
}