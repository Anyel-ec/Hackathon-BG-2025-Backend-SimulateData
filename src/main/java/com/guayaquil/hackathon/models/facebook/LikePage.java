package com.guayaquil.hackathon.models.facebook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "like_page")
public class LikePage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String facebookLikePageId;
    private String name;
    private String category;

    @ManyToOne
    @JoinColumn(name = "facebook_user_id")
    @JsonBackReference("user-likes")
    private FacebookUser facebookUser;
}
