package com.guayaquil.hackathon.repositories;

import com.guayaquil.hackathon.models.linkedin.ProfessionalProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalProfileRepository extends JpaRepository<ProfessionalProfile, Long> {
}