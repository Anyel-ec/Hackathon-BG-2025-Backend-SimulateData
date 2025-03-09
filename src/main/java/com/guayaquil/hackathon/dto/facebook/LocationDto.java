package com.guayaquil.hackathon.dto.facebook;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class LocationDto {
    private String id;
    private String name;
    @JsonProperty("zip_code")
    private String zipCode;
    private String neighborhood;
    private String city;
    private String region;
    private String country;
}

