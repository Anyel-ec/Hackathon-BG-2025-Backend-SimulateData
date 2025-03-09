package com.guayaquil.hackathon.services.impl;

import com.github.javafaker.Faker;
import com.guayaquil.hackathon.models.facebook.*;
import com.guayaquil.hackathon.repositories.FacebookUserRepository;
import com.guayaquil.hackathon.services.interfaces.FacebookDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class FacebookDataServiceImpl implements FacebookDataService {
    private final Faker faker = new Faker(new Locale("es"));
    private final Random random = new Random();
    private final FacebookUserRepository facebookUserRepository;

    @Override
    public FacebookUser generateFakeData() {
        FacebookUser user = new FacebookUser();
        user.setFacebookId(UUID.randomUUID().toString());
        user.setName("Neythan León Vásquez");
        user.setBirthday("1997"); // Año de nacimiento
        user.setEmail(faker.internet().emailAddress());
        user.setGender("male");
        user.setLink("https://www.facebook.com/neythanleon");
        user.setRelationshipStatus(null); // No disponible
        user.setPoliticalViews(null);
        user.setReligion(null);

        // Edad aproximada
        AgeRange ageRange = new AgeRange();
        ageRange.setMin(27); // Basado en su año de nacimiento (2024 - 1997)
        user.setAgeRange(ageRange);

        // Ubicación
        Hometown hometown = new Hometown();
        hometown.setId(UUID.randomUUID().toString());
        hometown.setName("Babahoyo, Los Ríos, Ecuador");
        user.setHometown(hometown);

        Location location = new Location();
        location.setId(UUID.randomUUID().toString());
        location.setName("Babahoyo, Los Ríos, Ecuador");
        location.setCity("Babahoyo");
        location.setCountry("Ecuador");
        user.setLocation(location);

        // No tiene empleo registrado
        user.setWork(Collections.emptyList());

        // **📌 Educación**
        List<Education> educationList = new ArrayList<>();

        Education university = new Education();
        university.setDegree("Ingeniería en Ciencias Computacionales y Tecnología");
        university.setYear("2021");
        university.setFacebookUser(user);
        School universitySchool = new School();
        universitySchool.setId(UUID.randomUUID().toString());
        universitySchool.setName("Escuela Superior Politécnica del Litoral (ESPOL)");
        university.setSchool(universitySchool);
        educationList.add(university);

        Education highSchool = new Education();
        highSchool.setDegree("Bachillerato");
        highSchool.setYear(null); // No disponible
        highSchool.setFacebookUser(user);
        School highSchoolSchool = new School();
        highSchoolSchool.setId(UUID.randomUUID().toString());
        highSchoolSchool.setName("Unidad Educativa Emigdio Esparza Moreno");
        highSchool.setSchool(highSchoolSchool);
        educationList.add(highSchool);

        user.setEducation(educationList);

        // **📌 Posts**
        List<Post> posts = new ArrayList<>();

        Post post1 = new Post();
        post1.setFacebookPostId(UUID.randomUUID().toString());
        post1.setCreatedTime("2022-07-07T00:00:00Z");
        post1.setMessage("Feliz cumpleaños 🎂🎂🎂 - Publicado por Mayerline Alonso");
        post1.setFacebookUser(user);
        posts.add(post1);

        Post post2 = new Post();
        post2.setFacebookPostId(UUID.randomUUID().toString());
        post2.setCreatedTime("2022-07-07T00:00:00Z");
        post2.setMessage("Feliz cumpleaños primo 🤗 - Publicado por Raisa Vasquez Ganchozo");
        post2.setFacebookUser(user);
        posts.add(post2);

        Post post3 = new Post();
        post3.setFacebookPostId(UUID.randomUUID().toString());
        post3.setCreatedTime("2022-07-07T00:00:00Z");
        post3.setMessage("Que viva el cumpleañero - Publicado por Carmen Yanet Quiroz Vera");
        post3.setFacebookUser(user);
        posts.add(post3);

        Post post4 = new Post();
        post4.setFacebookPostId(UUID.randomUUID().toString());
        post4.setCreatedTime("2022-05-20T00:00:00Z");
        post4.setMessage("📽️ 0:12 / 0:12 Video subido");
        post4.setFacebookUser(user);
        posts.add(post4);

        Post post5 = new Post();
        post5.setFacebookPostId(UUID.randomUUID().toString());
        post5.setCreatedTime("2022-04-29T00:00:00Z");
        post5.setMessage("Que manera de marcar territorio 😂");
        post5.setFacebookUser(user);
        posts.add(post5);

        user.setPosts(posts);

        // **📌 Fotos**
        List<Photo> photos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Photo photo = new Photo();
            photo.setFacebookPhotoId(UUID.randomUUID().toString());
            photo.setCreatedTime(OffsetDateTime.now()
                    .minusDays(faker.number().numberBetween(1, 30))
                    .toString());
            photo.setName("Foto de perfil");
            photo.setFacebookUser(user);
            photos.add(photo);
        }
        user.setPhotos(photos);

        // **📌 Intereses**
        Set<String> interests = new HashSet<>();
        interests.add("Música");
        interests.add("Deportes");
        interests.add("Cine");
        interests.add("Tecnología");
        user.setInterests(interests);


        facebookUserRepository.save(user);

        return user;
    }

}
