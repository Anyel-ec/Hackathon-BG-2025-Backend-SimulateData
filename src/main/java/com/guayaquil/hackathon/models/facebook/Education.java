package com.guayaquil.hackathon.models.facebook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String degree;
    private String year;

    @ManyToOne
    @JoinColumn(name = "facebook_user_id")
    @JsonBackReference("user-education")
    private FacebookUser facebookUser;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "school_id")),
            @AttributeOverride(name = "name", column = @Column(name = "school_name"))
    })
    private School school;
}
