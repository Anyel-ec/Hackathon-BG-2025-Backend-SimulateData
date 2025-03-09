package com.guayaquil.hackathon.models.google;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
import lombok.Data;
import java.util.List;

@Data
public class LocationWrapper {
    private List<LocationHistoryItem> locationHistory;
    private LocationHistoryItem currentLocation;
}