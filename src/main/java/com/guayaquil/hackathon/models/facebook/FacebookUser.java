package com.guayaquil.hackathon.models.facebook;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "facebook_user")
public class FacebookUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String facebookId;

    private String name;

    @Embedded
    private AgeRange ageRange;

    private String birthday;
    private String email;
    private String gender;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "hometown_id")),
            @AttributeOverride(name = "name", column = @Column(name = "hometown_name"))
    })
    private Hometown hometown;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "location_id")),
            @AttributeOverride(name = "name", column = @Column(name = "location_name")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "zip_code")),
            @AttributeOverride(name = "neighborhood", column = @Column(name = "neighborhood")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "region", column = @Column(name = "region")),
            @AttributeOverride(name = "country", column = @Column(name = "country"))
    })
    private Location location;

    private String link;
    private String relationshipStatus;
    private String politicalViews;
    private String religion;

    @ElementCollection
    @CollectionTable(name = "user_interests", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "interest")
    private Set<String> interests;

    @OneToMany(mappedBy = "facebookUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-posts")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "facebookUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-photos")
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "facebookUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-musicItems")
    private List<MusicItem> musicItems = new ArrayList<>();

    @OneToMany(mappedBy = "facebookUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-work")
    private List<Work> work = new ArrayList<>();

    @OneToMany(mappedBy = "facebookUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-education")
    private List<Education> education = new ArrayList<>();

    @OneToMany(mappedBy = "facebookUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-likes")
    private List<LikePage> likes = new ArrayList<>();

    @OneToMany(mappedBy = "facebookUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-brands")
    private List<Brand> favoriteBrands = new ArrayList<>();
}
