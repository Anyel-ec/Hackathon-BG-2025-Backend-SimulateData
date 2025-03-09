package com.guayaquil.hackathon.models.facebook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "work")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;
    private String startDate;
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "facebook_user_id")
    @JsonBackReference("user-work")
    private FacebookUser facebookUser;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "employer_id")),
            @AttributeOverride(name = "name", column = @Column(name = "employer_name"))
    })
    private Employer employer;
}
