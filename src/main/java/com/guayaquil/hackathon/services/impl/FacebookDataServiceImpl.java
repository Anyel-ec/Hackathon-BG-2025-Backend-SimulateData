package com.guayaquil.hackathon.services.impl;

import com.github.javafaker.Faker;
import com.guayaquil.hackathon.models.facebook.*;
import com.guayaquil.hackathon.services.interfaces.FacebookDataService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;

@Service
public class FacebookDataServiceImpl implements FacebookDataService {
    private final Faker faker = new Faker(new Locale("es"));
    private final Random random = new Random();

    private static final List<String> LATIN_AMERICAN_CITIES = Arrays.asList("Santo Domingo", "Bogotá", "Buenos Aires", "Lima", "Ciudad de México");
    private static final List<String> LATIN_AMERICAN_COUNTRIES = Arrays.asList("Ecuador", "Colombia", "Argentina", "Perú", "México");

    private static final List<String> EUROPEAN_CITIES = Arrays.asList("Madrid", "Paris", "Berlin", "Rome", "Amsterdam");
    private static final List<String> EUROPEAN_COUNTRIES = Arrays.asList("España", "Francia", "Alemania", "Italia", "Países Bajos");

    @Override
    public FacebookUser generateFakeData() {
        FacebookUser user = new FacebookUser();
        user.setFacebookId(UUID.randomUUID().toString());
        user.setName(faker.name().fullName());
        user.setBirthday("05/11/2002");
        user.setEmail(faker.internet().emailAddress());
        user.setGender("male");
        user.setLink("https://www.facebook.com/" + faker.name().username());
        user.setRelationshipStatus("Single");
        user.setPoliticalViews("Centrist");
        user.setReligion("None");

        // AgeRange
        AgeRange ageRange = new AgeRange();
        ageRange.setMin(21);
        user.setAgeRange(ageRange);

        // Selección aleatoria de región: América Latina o Europa
        boolean isLatinAmerica = random.nextBoolean();
        String city, country;
        if (isLatinAmerica) {
            city = LATIN_AMERICAN_CITIES.get(random.nextInt(LATIN_AMERICAN_CITIES.size()));
            country = LATIN_AMERICAN_COUNTRIES.get(random.nextInt(LATIN_AMERICAN_COUNTRIES.size()));
        } else {
            city = EUROPEAN_CITIES.get(random.nextInt(EUROPEAN_CITIES.size()));
            country = EUROPEAN_COUNTRIES.get(random.nextInt(EUROPEAN_COUNTRIES.size()));
        }

        // Hometown
        Hometown hometown = new Hometown();
        hometown.setId(UUID.randomUUID().toString());
        hometown.setName(city + " (" + country + ")");
        user.setHometown(hometown);

        // Location
        Location location = new Location();
        location.setId(UUID.randomUUID().toString());
        location.setName(city + " (" + country + ")");
        location.setZipCode(isLatinAmerica ? "10001" : "20002");
        location.setNeighborhood("Barrio Lujoso");
        location.setCity(city);
        location.setRegion(isLatinAmerica ? "Zona de alto nivel" : "Zona Premium");
        location.setCountry(country);
        user.setLocation(location);

        // Posts
        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Post post = new Post();
            post.setFacebookPostId(UUID.randomUUID().toString());
            post.setCreatedTime(OffsetDateTime.now()
                    .minusDays(faker.number().numberBetween(1, 30))
                    .toString());
            post.setMessage(faker.lorem().sentence());
            post.setFacebookUser(user);
            posts.add(post);
        }
        user.setPosts(posts);

        // Photos
        List<Photo> photos = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Photo photo = new Photo();
            photo.setFacebookPhotoId(UUID.randomUUID().toString());
            photo.setCreatedTime(OffsetDateTime.now()
                    .minusDays(faker.number().numberBetween(1, 30))
                    .toString());
            photo.setName(faker.lorem().word());
            photo.setFacebookUser(user);
            photos.add(photo);
        }
        user.setPhotos(photos);

        // MusicItems
        List<MusicItem> musicItems = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            MusicItem music = new MusicItem();
            music.setFacebookMusicId(UUID.randomUUID().toString());
            music.setName(faker.artist().name());
            music.setCreatedTime(OffsetDateTime.now()
                    .minusDays(faker.number().numberBetween(30, 365))
                    .toString());
            music.setFacebookUser(user);
            musicItems.add(music);
        }
        user.setMusicItems(musicItems);

        // Work
        List<Work> workList = new ArrayList<>();
        Work work = new Work();
        work.setPosition("Software Engineer");
        work.setStartDate("2020-06");
        work.setEndDate(null);
        work.setFacebookUser(user);
        Employer employer = new Employer();
        employer.setId(UUID.randomUUID().toString());
        employer.setName("TechCorp");
        work.setEmployer(employer);
        workList.add(work);
        user.setWork(workList);

        // Education
        List<Education> educationList = new ArrayList<>();
        Education education = new Education();
        education.setDegree("Ingeniería Informática");
        education.setYear("2024");
        education.setFacebookUser(user);
        School school = new School();
        school.setId(UUID.randomUUID().toString());
        school.setName("Universidad de Quito");
        education.setSchool(school);
        educationList.add(education);
        user.setEducation(educationList);

        // Likes
        List<LikePage> likes = new ArrayList<>();
        LikePage likePage = new LikePage();
        likePage.setFacebookLikePageId(UUID.randomUUID().toString());
        likePage.setName("Rolex");
        likePage.setCategory("Luxury Goods");
        likePage.setFacebookUser(user);
        likes.add(likePage);
        user.setLikes(likes);

        // Favorite Brands
        List<Brand> brands = new ArrayList<>();
        Brand brand = new Brand();
        brand.setFacebookBrandId(UUID.randomUUID().toString());
        brand.setName("Louis Vuitton");
        brand.setFacebookUser(user);
        brands.add(brand);
        user.setFavoriteBrands(brands);

        // Interests
        Set<String> interests = new HashSet<>();
        interests.add("Tecnología");
        interests.add("Viajes");
        interests.add("Moda");
        interests.add("Gastronomía");
        user.setInterests(interests);

        return user;
    }
}
