package com.guayaquil.hackathon.models.facebook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String facebookBrandId;
    private String name;

    @ManyToOne
    @JoinColumn(name = "facebook_user_id")
    @JsonBackReference("user-brands")
    private FacebookUser facebookUser;
}
