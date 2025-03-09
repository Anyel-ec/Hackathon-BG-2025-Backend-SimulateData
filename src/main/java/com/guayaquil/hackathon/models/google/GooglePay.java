package com.guayaquil.hackathon.models.google;

import lombok.Data;

import java.util.List;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
@Data
public class GooglePay {
    private List<Transaction> transactions;
}
