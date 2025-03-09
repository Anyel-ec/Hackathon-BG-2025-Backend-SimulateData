package com.guayaquil.hackathon.models.facebook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String facebookPhotoId;
    private String createdTime;

    @Column(length = 2048)
    private String name;

    @ManyToOne
    @JoinColumn(name = "facebook_user_id")
    @JsonBackReference("user-photos")
    private FacebookUser facebookUser;
}
