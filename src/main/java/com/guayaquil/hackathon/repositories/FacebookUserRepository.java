package com.guayaquil.hackathon.repositories;

import com.guayaquil.hackathon.models.facebook.FacebookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
@Repository
public interface FacebookUserRepository extends JpaRepository<FacebookUser, Long> {
    Optional<FacebookUser> findByEmail(String email);

}
