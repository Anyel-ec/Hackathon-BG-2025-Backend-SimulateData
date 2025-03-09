package com.guayaquil.hackathon.repositories;

import com.guayaquil.hackathon.models.google.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
@Repository
public interface GoogleUserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.basicInfo.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
}
