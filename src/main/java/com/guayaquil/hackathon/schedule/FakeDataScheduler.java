package com.guayaquil.hackathon.schedule;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */

import com.guayaquil.hackathon.models.facebook.FacebookUser;
import com.guayaquil.hackathon.models.google.UserData;
import com.guayaquil.hackathon.models.linkedin.ProfessionalProfile;
import com.guayaquil.hackathon.repositories.FacebookUserRepository;
import com.guayaquil.hackathon.repositories.GoogleUserDataRepository;
import com.guayaquil.hackathon.repositories.ProfessionalProfileRepository;
import com.guayaquil.hackathon.services.impl.ProfessionalProfileService;
import com.guayaquil.hackathon.services.interfaces.FacebookDataService;
import com.guayaquil.hackathon.services.interfaces.GoogleDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class FakeDataScheduler {

    private final FacebookDataService facebookDataService;
    private final FacebookUserRepository facebookUserRepository;
    private final GoogleDataService userDataService;
    private final GoogleUserDataRepository userDataRepository;
    private final ProfessionalProfileService linkedInDataService;
    private final ProfessionalProfileRepository linkedInProfileRepository;

    public void persistFakeData() {
        // Generate and save a fake Facebook user
        FacebookUser user = facebookDataService.generateFakeData();
        FacebookUser savedUser = facebookUserRepository.save(user);
        log.info("Saved user: " + savedUser.getName() + " with generated ID: " + savedUser.getId());
    }

    @Scheduled(fixedRate = 5000)
    public void persistFakeUserData() {
        // Generate and save a fake Google user data
        UserData userData = userDataService.generateFakeUserData();
        UserData saved = userDataRepository.save(userData);
        log.info("Persisted user: " + saved.getUser().getBasicInfo().getName() +
                " - Email: " + saved.getUser().getBasicInfo().getEmail());
    }

    //@Scheduled(fixedRate = 1000)
    public void persistFakeLinkedInData() {
        // Generate and save a fake LinkedIn profile
        ProfessionalProfile profile = linkedInDataService.generateFakeProfile();
        ProfessionalProfile savedProfile = linkedInProfileRepository.save(profile);
        log.info("Persisted LinkedIn profile: " + savedProfile.getNombreCompleto() +
                " - Position: " + savedProfile.getCargoActual());
    }

}
