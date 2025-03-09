package com.guayaquil.hackathon.repositories;

import com.guayaquil.hackathon.models.google.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
@Repository
public interface GoogleUserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUserBasicInfoEmail(String email);

}