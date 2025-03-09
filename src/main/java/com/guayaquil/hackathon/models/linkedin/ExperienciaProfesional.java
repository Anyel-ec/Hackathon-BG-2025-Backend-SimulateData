package com.guayaquil.hackathon.models.linkedin;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "experiencia_profesional")
public class ExperienciaProfesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empresa;
    private String cargo;
    private String fechaInicio;
    private String fechaFin;
    private String nivelSeniority;

    @ElementCollection
    @CollectionTable(name = "tecnologias_experiencia", joinColumns = @JoinColumn(name = "experiencia_id"))
    @Column(name = "tecnologia")
    private List<String> tecnologias;
}