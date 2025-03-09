package com.guayaquil.hackathon.models.google;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BasicInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String profilePictureUrl;
    private int approximateAge;

    @JsonIgnore
    @OneToOne(mappedBy = "basicInfo")
    private User user;
}
