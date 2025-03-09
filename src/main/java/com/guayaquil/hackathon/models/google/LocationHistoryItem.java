package com.guayaquil.hackathon.models.google;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
import lombok.Data;

@Data
public class LocationHistoryItem {
    private double latitude;
    private double longitude;
    private String timestamp;
    private String place;
}