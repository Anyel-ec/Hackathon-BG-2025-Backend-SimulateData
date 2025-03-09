package com.guayaquil.hackathon.services.impl;

import com.github.javafaker.Faker;
import com.guayaquil.hackathon.models.linkedin.*;
import com.guayaquil.hackathon.repositories.ProfessionalProfileRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        // Create a new professional profile
        ProfessionalProfile profile = new ProfessionalProfile();

        // Set name and location
        profile.setNombreCompleto(faker.name().fullName());
        profile.setUbicacion(faker.address().city());

        // Generate professional experience
        List<ExperienciaProfesional> experiencias = new ArrayList<>();
        for (int i = 0; i < faker.number().numberBetween(1, 3); i++) {
            ExperienciaProfesional exp = new ExperienciaProfesional();
            exp.setEmpresa(faker.company().name());
            exp.setCargo(faker.job().title());
            exp.setFechaInicio(faker.number().numberBetween(2005, 2023) + "-0" + faker.number().numberBetween(1, 9));
            exp.setFechaFin(faker.bool().bool() ? "Present" : (faker.number().numberBetween(2010, 2024) + "-0" + faker.number().numberBetween(1, 9)));
            exp.setNivelSeniority(faker.options().option("Junior", "Intermediate", "Senior"));
            exp.setTecnologias(Arrays.asList("Java", "Spring Boot", "Docker", "Kubernetes"));
            experiencias.add(exp);
        }
        profile.setExperienciaProfesional(experiencias);

        // Set previous jobs and current status
        profile.setTrabajosPrevios(experiencias.size());
        profile.setEstadoActual(faker.options().option("Employed", "Freelancer", "Unemployed"));
        profile.setCargoActual(experiencias.get(0).getCargo());
        profile.setNivelEducacion(faker.options().option("Computer Science Engineering", "Master in Artificial Intelligence"));

        // Set skills
        profile.setHabilidades(Arrays.asList("Software Development", "Machine Learning", "Data Science", "DevOps"));

        // Set languages
        List<Idioma> idiomas = new ArrayList<>();
        idiomas.add(new Idioma("Spanish", "Native"));
        idiomas.add(new Idioma("English", faker.options().option("Basic", "Intermediate", "Advanced")));
        profile.setIdiomas(idiomas);

        // Set estimated salary
        SalarioEstimado salario = new SalarioEstimado();
        Map<String, String> salaryRanges = new HashMap<>();
        salaryRanges.put("USA", "100,000 - 130,000 USD");
        salaryRanges.put("Mexico", "10,000 - 20,000 USD");
        salaryRanges.put("Ecuador", "12,000 - 18,000 USD");
        salario.setRango(salaryRanges);

        Map<String, String> salarySources = new HashMap<>();
        salarySources.put("USA", "Talent.com");
        salarySources.put("Mexico", "Glassdoor");
        salarySources.put("Ecuador", "Market estimation");
        salario.setFuente(salarySources);
        profile.setSalarioEstimado(salario);

        // Set credit profile
        PerfilCrediticio creditProfile = new PerfilCrediticio();
        Map<String, String> creditFactors = new HashMap<>();
        creditFactors.put("job_stability", "Medium-High");
        creditFactors.put("income_level", "Depends on location");
        creditFactors.put("credit_history", "Unknown");
        creditFactors.put("debt", "Unknown");
        creditProfile.setFactores(creditFactors);
        creditProfile.setCategoriaProbable("Good - Very Good");
        profile.setPerfilCrediticio(creditProfile);

        // Save profile to the database
        return repository.save(profile);
    }

    public Page<ProfessionalProfile> getAllProfiles(Pageable pageable) {
        // Retrieve all professional profiles with pagination
        return repository.findAll(pageable);
    }

    public Optional<ProfessionalProfile> getProfileById(Long id) {
        // Retrieve a professional profile by ID
        return repository.findById(id);
    }
}
