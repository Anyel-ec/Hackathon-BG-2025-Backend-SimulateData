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
        // **📌 Crear perfil profesional**
        ProfessionalProfile profile = new ProfessionalProfile();
        profile.setNombreCompleto("Neythan León Vásquez");
        profile.setUbicacion("Guayaquil, Guayas, Ecuador");

        // **📌 Experiencia Profesional**
        List<ExperienciaProfesional> experiencias = new ArrayList<>();

        // **Desarrollador de Software en AlfaPeople**
        ExperienciaProfesional exp1 = new ExperienciaProfesional();
        exp1.setEmpresa("AlfaPeople");
        exp1.setCargo("Desarrollador de software");
        exp1.setFechaInicio("2020-11");
        exp1.setFechaFin(null); // Actualidad
        exp1.setNivelSeniority("Senior");
        exp1.setTecnologias(Arrays.asList("Microsoft Dynamics 365 FO", "C#", "Azure DevOps", "SQL Server", "Web Services API", "OData", "SSRS"));
        experiencias.add(exp1);

        // **CTO & Founder en Levanst**
        ExperienciaProfesional exp2 = new ExperienciaProfesional();
        exp2.setEmpresa("Levanst");
        exp2.setCargo("CTO & Founder");
        exp2.setFechaInicio("2019-02");
        exp2.setFechaFin(null); // Actualidad
        exp2.setNivelSeniority("Senior");
        exp2.setTecnologias(Arrays.asList("Angular", "Django", "Python", "TypeScript", "Full-Stack Development"));
        experiencias.add(exp2);

        // **Finalista en Hult Prize Foundation**
        ExperienciaProfesional exp3 = new ExperienciaProfesional();
        exp3.setEmpresa("Hult Prize Foundation");
        exp3.setCargo("Finalista 2019 Reto HultPrize OnCampus");
        exp3.setFechaInicio("2019-10");
        exp3.setFechaFin("2023-04");
        exp3.setNivelSeniority("Intermedio");
        exp3.setTecnologias(Arrays.asList("Web Analytics", "Project Coordination", "Agile Project Management", "Full-Stack Development"));
        experiencias.add(exp3);

        // **Presidente del Club de Mecatrónica ESPOL**
        ExperienciaProfesional exp4 = new ExperienciaProfesional();
        exp4.setEmpresa("Club de Mecatrónica ESPOL");
        exp4.setCargo("Presidente");
        exp4.setFechaInicio("2020-01");
        exp4.setFechaFin("2021-02");
        exp4.setNivelSeniority("Liderazgo");
        exp4.setTecnologias(Arrays.asList("Educación en tecnología", "IoT", "Team Coordination", "Digital Marketing"));
        experiencias.add(exp4);

        profile.setExperienciaProfesional(experiencias);

        // **📌 Datos de trabajos previos y estado actual**
        profile.setTrabajosPrevios(experiencias.size());
        profile.setEstadoActual("Empleado");
        profile.setCargoActual("Desarrollador de software");
        profile.setNivelEducacion("Ingeniería en Ciencias Computacionales");

        // **📌 Habilidades**
        profile.setHabilidades(Arrays.asList(
                "Microsoft Dynamics 365 FO", "C#", "Azure DevOps", "SQL Server",
                "Angular", "Django", "Python", "TypeScript", "Full-Stack Development",
                "Project Coordination", "Agile Project Management", "IoT"
        ));

        // **📌 Idiomas**
        List<Idioma> idiomas = new ArrayList<>();
        idiomas.add(new Idioma("Español", "Nativo"));
        idiomas.add(new Idioma("Inglés", "Intermedio"));
        profile.setIdiomas(idiomas);

        // **📌 Salario Estimado**
        SalarioEstimado salario = new SalarioEstimado();
        Map<String, String> salaryRanges = new HashMap<>();
        salaryRanges.put("EE.UU", "100,000 - 130,000 USD");
        salaryRanges.put("México", "10,000 - 20,000 USD");
        salaryRanges.put("Ecuador", "12,000 - 18,000 USD");
        salario.setRango(salaryRanges);

        Map<String, String> salarySources = new HashMap<>();
        salarySources.put("EE.UU", "Talent.com");
        salarySources.put("México", "Glassdoor");
        salarySources.put("Ecuador", "Estimado basado en mercado");
        salario.setFuente(salarySources);
        profile.setSalarioEstimado(salario);

        // **📌 Perfil Crediticio**
        PerfilCrediticio creditProfile = new PerfilCrediticio();
        Map<String, String> creditFactors = new HashMap<>();
        creditFactors.put("estabilidad_laboral", "Alta");
        creditFactors.put("nivel_ingresos", "Depende de ubicación");
        creditFactors.put("historial_crediticio", "Desconocido");
        creditFactors.put("endeudamiento", "Desconocido");
        creditProfile.setFactores(creditFactors);
        creditProfile.setCategoriaProbable("Bueno - Muy Bueno");
        profile.setPerfilCrediticio(creditProfile);

        // **📌 Guardar el perfil en la base de datos**
        return repository.save(profile);
    }

    public Page<ProfessionalProfile> getAllProfiles(Pageable pageable) {
        // Obtener todos los perfiles con paginación
        return repository.findAll(pageable);
    }

    public Optional<ProfessionalProfile> getProfileById(Long id) {
        // Obtener un perfil por ID
        return repository.findById(id);
    }
}
