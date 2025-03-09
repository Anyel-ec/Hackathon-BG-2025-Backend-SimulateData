package com.guayaquil.hackathon.schedule;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
import com.guayaquil.hackathon.models.facebook.FacebookUser;
import com.guayaquil.hackathon.models.google.UserData;
import com.guayaquil.hackathon.repositories.FacebookUserRepository;
import com.guayaquil.hackathon.repositories.UserDataRepository;
import com.guayaquil.hackathon.services.interfaces.FacebookDataService;
import com.guayaquil.hackathon.services.interfaces.UserDataService;
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
    private final UserDataService userDataService;
    private final UserDataRepository userDataRepository;

    @Scheduled(fixedRate = 5000)
    public void persistFakeData() {
        FacebookUser user = facebookDataService.generateFakeData();
        FacebookUser savedUser = facebookUserRepository.save(user);
        log.info("Saved user: " + savedUser.getName() + " with generated ID: " + savedUser.getId());
    }

    @Scheduled(fixedRate = 5000)
    public void persistFakeUserData() {
        UserData userData = userDataService.generateFakeUserData();
        UserData saved = userDataRepository.save(userData);
        log.info("Persisted user: " + saved.getUser().getBasicInfo().getName() +
                " - Email: " + saved.getUser().getBasicInfo().getEmail());
    }

}