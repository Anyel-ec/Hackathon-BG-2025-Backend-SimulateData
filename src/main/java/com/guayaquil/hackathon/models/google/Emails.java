package com.guayaquil.hackathon.models.google;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
import lombok.Data;
import java.util.List;

@Data
public class Emails {
    private List<EmailMetadata> metadata;
}