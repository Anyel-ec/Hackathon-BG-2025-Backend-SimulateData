package com.guayaquil.hackathon.models.linkedin;

/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "professional_profile")
public class ProfessionalProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCompleto;
    private String ubicacion;
    private int trabajosPrevios;
    private String estadoActual;
    private String cargoActual;
    private String nivelEducacion;

    @ElementCollection
    @CollectionTable(name = "habilidades", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "habilidad")
    private List<String> habilidades;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id")
    private List<Idioma> idiomas;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id")
    private List<ExperienciaProfesional> experienciaProfesional;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "salario_estimado_id")
    private SalarioEstimado salarioEstimado;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "perfil_crediticio_id")
    private PerfilCrediticio perfilCrediticio;
}