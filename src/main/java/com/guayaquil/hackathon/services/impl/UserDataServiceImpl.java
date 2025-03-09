package com.guayaquil.hackathon.services.impl;
import com.github.javafaker.Faker;
import com.guayaquil.hackathon.models.google.*;
import com.guayaquil.hackathon.services.interfaces.UserDataService;
import com.guayaquil.hackathon.models.google.Contact;

import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;
/*
 * Author: Anyel EC
 * Github: https://github.com/Anyel-ec
 * Creation date: 09/03/2025
 */
@Service
public class UserDataServiceImpl implements UserDataService {

    private final Faker faker = new Faker(new Locale("en")); // Puedes usar "es" si prefieres español
    private final Random random = new Random();

    // Listas de valores predefinidos para algunos campos
    private static final List<String> PLACES = Arrays.asList(
            "Coffee Shop, Los Angeles",
            "Museum, New York",
            "Restaurant, Mexico City",
            "Park, Madrid",
            "Gallery, Paris"
    );
    private static final List<String> CONTACT_NAMES = Arrays.asList("John Smith", "Emily Jones", "Michael Brown", "Laura Wilson");
    private static final List<String> CONTACT_EMAILS = Arrays.asList("john.smith@example.com", "emily.jones@example.com", "michael.brown@example.com", "laura.wilson@example.com");
    private static final List<String> PHONE_NUMBERS = Arrays.asList("+15551234567", "+15559876543", "+15552345678", "+15553456789");
    private static final List<String> SEARCH_QUERIES = Arrays.asList("restaurants near me", "flights to Europe", "best coffee shops", "weather today");
    private static final List<String> YOUTUBE_VIDEOS = Arrays.asList("Italian Cooking Recipe", "Programming Tutorial", "Travel Vlog", "Music Video");
    private static final List<String> YOUTUBE_CHANNELS = Arrays.asList("Cooking Channel", "Tech Channel", "Travel Channel", "Music Channel");
    private static final List<String> MAPS_PLACES = Arrays.asList("Italian Restaurant", "Electronics Store", "Bookstore", "Gym");
    private static final List<String> MERCHANTS = Arrays.asList("Supermarket", "Restaurant", "Cafe", "Online Store");

    @Override
    public UserData generateFakeUserData() {
        UserData userData = new UserData();
        User user = new User();

        // BasicInfo
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setName(faker.name().fullName());
        basicInfo.setEmail(faker.internet().emailAddress());
        basicInfo.setProfilePictureUrl("https://example.com/" + faker.internet().slug() + ".jpg");
        basicInfo.setApproximateAge(faker.number().numberBetween(18, 60));
        user.setBasicInfo(basicInfo);

        // Location
        LocationWrapper locationWrapper = new LocationWrapper();
        // LocationHistory
        List<LocationHistoryItem> history = new ArrayList<>();
        int historyCount = faker.number().numberBetween(2, 5);
        for (int i = 0; i < historyCount; i++) {
            LocationHistoryItem item = new LocationHistoryItem();
            item.setLatitude(Double.parseDouble(faker.address().latitude()));
            item.setLongitude(Double.parseDouble(faker.address().longitude()));
            item.setTimestamp(OffsetDateTime.now().minusDays(faker.number().numberBetween(1, 10)).toString());
            item.setPlace(PLACES.get(random.nextInt(PLACES.size())));
            history.add(item);
        }
        locationWrapper.setLocationHistory(history);
        // CurrentLocation
        LocationHistoryItem currentLocation = new LocationHistoryItem();
        currentLocation.setLatitude(Double.parseDouble(faker.address().latitude()));
        currentLocation.setLongitude(Double.parseDouble(faker.address().longitude()));
        currentLocation.setTimestamp(OffsetDateTime.now().toString());
        currentLocation.setPlace("Home, " + faker.address().city());
        locationWrapper.setCurrentLocation(currentLocation);
        user.setLocation(locationWrapper);

        // Contacts
        List<Contact> contacts = new ArrayList<>();
        int contactCount = faker.number().numberBetween(1, 4);
        for (int i = 0; i < contactCount; i++) {
            Contact contact = new Contact();
            contact.setName(CONTACT_NAMES.get(random.nextInt(CONTACT_NAMES.size())));
            contact.setEmail(CONTACT_EMAILS.get(random.nextInt(CONTACT_EMAILS.size())));
            contact.setPhoneNumber(PHONE_NUMBERS.get(random.nextInt(PHONE_NUMBERS.size())));
            contacts.add(contact);
        }
        user.setContacts(contacts);

        // Emails metadata
        Emails emails = new Emails();
        List<EmailMetadata> emailMetadataList = new ArrayList<>();
        int emailCount = faker.number().numberBetween(1, 3);
        for (int i = 0; i < emailCount; i++) {
            EmailMetadata metadata = new EmailMetadata();
            metadata.setSender(faker.internet().emailAddress());
            metadata.setSubject(faker.lorem().sentence());
            metadata.setTimestamp(OffsetDateTime.now().minusDays(faker.number().numberBetween(1, 5)).toString());
            emailMetadataList.add(metadata);
        }
        emails.setMetadata(emailMetadataList);
        user.setEmails(emails);

        // Google Activity
        GoogleActivity googleActivity = new GoogleActivity();
        // Search history
        List<SearchHistoryItem> searchHistory = new ArrayList<>();
        int searchCount = faker.number().numberBetween(1, 3);
        for (int i = 0; i < searchCount; i++) {
            SearchHistoryItem searchItem = new SearchHistoryItem();
            searchItem.setQuery(SEARCH_QUERIES.get(random.nextInt(SEARCH_QUERIES.size())));
            searchItem.setTimestamp(OffsetDateTime.now().minusDays(faker.number().numberBetween(1, 5)).toString());
            searchHistory.add(searchItem);
        }
        googleActivity.setSearchHistory(searchHistory);
        // YouTube activity
        List<YoutubeActivityItem> youtubeActivity = new ArrayList<>();
        int youtubeCount = faker.number().numberBetween(1, 3);
        for (int i = 0; i < youtubeCount; i++) {
            YoutubeActivityItem ytItem = new YoutubeActivityItem();
            ytItem.setVideoTitle(YOUTUBE_VIDEOS.get(random.nextInt(YOUTUBE_VIDEOS.size())));
            ytItem.setChannel(YOUTUBE_CHANNELS.get(random.nextInt(YOUTUBE_CHANNELS.size())));
            ytItem.setTimestamp(OffsetDateTime.now().minusDays(faker.number().numberBetween(1, 5)).toString());
            youtubeActivity.add(ytItem);
        }
        googleActivity.setYoutubeActivity(youtubeActivity);
        // Maps history
        List<MapsHistoryItem> mapsHistory = new ArrayList<>();
        int mapsCount = faker.number().numberBetween(1, 3);
        for (int i = 0; i < mapsCount; i++) {
            MapsHistoryItem mapsItem = new MapsHistoryItem();
            mapsItem.setPlace(MAPS_PLACES.get(random.nextInt(MAPS_PLACES.size())));
            mapsItem.setTimestamp(OffsetDateTime.now().minusDays(faker.number().numberBetween(1, 5)).toString());
            mapsHistory.add(mapsItem);
        }
        googleActivity.setMapsHistory(mapsHistory);
        user.setGoogleActivity(googleActivity);

        // Google Pay transactions
        GooglePay googlePay = new GooglePay();
        List<Transaction> transactions = new ArrayList<>();
        int transCount = faker.number().numberBetween(1, 3);
        for (int i = 0; i < transCount; i++) {
            Transaction transaction = new Transaction();
            transaction.setMerchant(MERCHANTS.get(random.nextInt(MERCHANTS.size())));
            transaction.setAmount(faker.number().randomDouble(2, 10, 100));
            transaction.setTimestamp(OffsetDateTime.now().minusDays(faker.number().numberBetween(1, 5)).toString());
            transactions.add(transaction);
        }
        googlePay.setTransactions(transactions);
        user.setGooglePay(googlePay);

        // Asigna el objeto user al contenedor principal
        userData.setUser(user);
        return userData;
    }
}