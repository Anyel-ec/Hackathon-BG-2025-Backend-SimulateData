package com.guayaquil.hackathon.models.facebook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String facebookPostId;
    private String createdTime;

    @Column(length = 2048)
    private String message;

    @ManyToOne
    @JoinColumn(name = "facebook_user_id")
    @JsonBackReference("user-posts")
    private FacebookUser facebookUser;
}
