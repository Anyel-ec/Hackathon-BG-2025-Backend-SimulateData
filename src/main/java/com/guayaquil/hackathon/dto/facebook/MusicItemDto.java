package com.guayaquil.hackathon.dto.facebook;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class MusicItemDto {
    private String name;
    private String id;
    @JsonProperty("created_time")
    private String createdTime;
}
