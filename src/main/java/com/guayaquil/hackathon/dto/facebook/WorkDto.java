package com.guayaquil.hackathon.dto.facebook;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
import lombok.Data;

@Data
public class WorkDto {
    private EmployerDto employer;
    private String position;
    private String startDate;
    private String endDate;
}