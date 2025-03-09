package com.guayaquil.hackathon.models.google;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
import lombok.Data;
import java.util.List;

@Data
public class GoogleActivity {
    private List<SearchHistoryItem> searchHistory;
    private List<YoutubeActivityItem> youtubeActivity;
    private List<MapsHistoryItem> mapsHistory;
}