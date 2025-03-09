package com.guayaquil.hackathon.models.linkedin;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Map;

@Data
@Entity
@Table(name = "perfil_crediticio")
public class PerfilCrediticio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "factores_crediticios", joinColumns = @JoinColumn(name = "perfil_crediticio_id"))
    @MapKeyColumn(name = "factor")
    @Column(name = "valor")
    private Map<String, String> factores;

    private String categoriaProbable;
}
