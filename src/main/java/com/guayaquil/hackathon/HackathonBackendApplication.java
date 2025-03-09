package com.guayaquil.hackathon;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HackathonBackendApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();

        // Set environment variables as system properties so Spring can access them
        System.setProperty("POSTGRES_URL", dotenv.get("POSTGRES_URL", ""));
        System.setProperty("POSTGRES_USERNAME", dotenv.get("POSTGRES_USERNAME", ""));
        System.setProperty("POSTGRES_PASSWORD", dotenv.get("POSTGRES_PASSWORD", ""));

        SpringApplication.run(HackathonBackendApplication.class, args);
    }

}
