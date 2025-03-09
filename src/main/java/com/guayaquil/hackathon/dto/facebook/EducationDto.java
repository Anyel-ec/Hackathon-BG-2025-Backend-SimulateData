package com.guayaquil.hackathon.dto.facebook;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
import lombok.Data;

@Data
public class EducationDto {
    private SchoolDto school;
    private String degree;
    private String year;
}