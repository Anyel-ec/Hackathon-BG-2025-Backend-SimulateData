package com.guayaquil.hackathon.models.google;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
import jakarta.persistence.*;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
public class LocationWrapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "location_wrapper_id")
    private List<LocationHistoryItem> locationHistory = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "current_location_id")
    private LocationHistoryItem currentLocation;
}