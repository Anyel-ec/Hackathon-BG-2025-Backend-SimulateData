package com.guayaquil.hackathon.services.impl;

import com.github.javafaker.Faker;
import com.guayaquil.hackathon.models.linkedin.*;
import com.guayaquil.hackathon.repositories.ProfessionalProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProfessionalProfileService {

    private final ProfessionalProfileRepository repository;
    private final Faker faker = new Faker(new Locale("es"));

    public ProfessionalProfileService(ProfessionalProfileRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ProfessionalProfile generateFakeProfile() {
        ProfessionalProfile profile = new ProfessionalProfile();

        // Nombre y Ubicación
        profile.setNombreCompleto(faker.name().fullName());
        profile.setUbicacion(faker.address().city());

        // Experiencia Profesional
        List<ExperienciaProfesional> experiencias = new ArrayList<>();
        for (int i = 0; i < faker.number().numberBetween(1, 3); i++) {
            ExperienciaProfesional exp = new ExperienciaProfesional();
            exp.setEmpresa(faker.company().name());
            exp.setCargo(faker.job().title());
            exp.setFechaInicio(faker.number().numberBetween(2005, 2023) + "-0" + faker.number().numberBetween(1, 9));
            exp.setFechaFin(faker.bool().bool() ? "Presente" : (faker.number().numberBetween(2010, 2024) + "-0" + faker.number().numberBetween(1, 9)));
            exp.setNivelSeniority(faker.options().option("Junior", "Intermedio", "Senior"));
            exp.setTecnologias(Arrays.asList("Java", "Spring Boot", "Docker", "Kubernetes"));
            experiencias.add(exp);
        }
        profile.setExperienciaProfesional(experiencias);

        // Trabajos previos y Estado actual
        profile.setTrabajosPrevios(experiencias.size());
        profile.setEstadoActual(faker.options().option("Empleado", "Freelancer", "Desempleado"));
        profile.setCargoActual(experiencias.get(0).getCargo());
        profile.setNivelEducacion(faker.options().option("Ingeniería en Ciencias de la Computación", "Máster en Inteligencia Artificial"));

        // Habilidades
        profile.setHabilidades(Arrays.asList("Desarrollo de Software", "Machine Learning", "Ciencia de Datos", "DevOps"));

        // Idiomas
        List<Idioma> idiomas = new ArrayList<>();
        idiomas.add(new Idioma("Español", "Nativo"));
        idiomas.add(new Idioma("Inglés", faker.options().option("Básico", "Intermedio", "Avanzado")));
        profile.setIdiomas(idiomas);

        // Salario Estimado
        SalarioEstimado salario = new SalarioEstimado();
        Map<String, String> rangos = new HashMap<>();
        rangos.put("EE.UU", "100,000 - 130,000 USD");
        rangos.put("México", "10,000 - 20,000 USD");
        rangos.put("Ecuador", "12,000 - 18,000 USD");
        salario.setRango(rangos);

        Map<String, String> fuentes = new HashMap<>();
        fuentes.put("EE.UU", "Talent.com");
        fuentes.put("México", "Glassdoor");
        fuentes.put("Ecuador", "Estimado basado en mercado");
        salario.setFuente(fuentes);
        profile.setSalarioEstimado(salario);

        // Perfil Crediticio
        PerfilCrediticio crediticio = new PerfilCrediticio();
        Map<String, String> factores = new HashMap<>();
        factores.put("estabilidad_laboral", "Media-Alta");
        factores.put("nivel_ingresos", "Depende de ubicación");
        factores.put("historial_crediticio", "Desconocido");
        factores.put("endeudamiento", "Desconocido");
        crediticio.setFactores(factores);
        crediticio.setCategoriaProbable("Bueno - Muy Bueno");
        profile.setPerfilCrediticio(crediticio);

        // Guardar en la base de datos
        return repository.save(profile);
    }
}