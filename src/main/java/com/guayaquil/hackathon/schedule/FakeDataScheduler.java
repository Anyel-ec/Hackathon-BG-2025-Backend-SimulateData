package com.guayaquil.hackathon.schedule;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
import com.guayaquil.hackathon.models.facebook.FacebookUser;
import com.guayaquil.hackathon.repositories.FacebookUserRepository;
import com.guayaquil.hackathon.services.interfaces.FacebookDataService;
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


    @Scheduled(fixedRate = 1000)
    public void persistFakeData() {
        FacebookUser user = facebookDataService.generateFakeData();
        FacebookUser savedUser = facebookUserRepository.save(user);
        log.info("Saved user: " + savedUser.getName() + " with generated ID: " + savedUser.getId());
    }
}