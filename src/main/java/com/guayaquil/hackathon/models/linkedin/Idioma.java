package com.guayaquil.hackathon.models.linkedin;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "idioma")

public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idioma;
    private String nivel;

    public Idioma() {
    }

    public Idioma(String idioma, String nivel) {
        this.idioma = idioma;
        this.nivel = nivel;
    }
}