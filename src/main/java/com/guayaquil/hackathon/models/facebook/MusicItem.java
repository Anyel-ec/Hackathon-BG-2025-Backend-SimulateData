package com.guayaquil.hackathon.models.facebook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "music_item")
public class MusicItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String facebookMusicId;
    private String name;
    private String createdTime;

    @ManyToOne
    @JoinColumn(name = "facebook_user_id")
    @JsonBackReference("user-musicItems")
    private FacebookUser facebookUser;
}
