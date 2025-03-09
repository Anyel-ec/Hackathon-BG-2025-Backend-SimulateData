package com.guayaquil.hackathon.models.google;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
import lombok.Data;

@Data
public class BasicInfo {
    private String name;
    private String email;
    private String profilePictureUrl;
    private int approximateAge;
}