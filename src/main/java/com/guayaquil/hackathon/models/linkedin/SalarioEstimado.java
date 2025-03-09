package com.guayaquil.hackathon.models.linkedin;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Map;

@Data
@Entity
@Table(name = "salario_estimado")
public class SalarioEstimado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "rango_salarial", joinColumns = @JoinColumn(name = "salario_estimado_id"))
    @MapKeyColumn(name = "pais")
    @Column(name = "rango")
    private Map<String, String> rango;

    @ElementCollection
    @CollectionTable(name = "fuente_salarial", joinColumns = @JoinColumn(name = "salario_estimado_id"))
    @MapKeyColumn(name = "pais")
    @Column(name = "fuente")
    private Map<String, String> fuente;
}