package com.guayaquil.hackathon.models.facebook;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class School {
    private String id;
    private String name;
}
