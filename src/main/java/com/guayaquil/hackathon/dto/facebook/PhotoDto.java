package com.guayaquil.hackathon.dto.facebook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
@Data
public class PhotoDto {
    @JsonProperty("created_time")
    private String createdTime;
    private String id;
    private String name;
}