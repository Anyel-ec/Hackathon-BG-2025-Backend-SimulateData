package com.guayaquil.hackathon.services.impl;

import com.github.javafaker.Faker;
import com.guayaquil.hackathon.models.google.*;
import com.guayaquil.hackathon.repositories.UserRepository;
import com.guayaquil.hackathon.services.interfaces.UserDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.*;

@Service
public class UserDataServiceImpl implements UserDataService {

    private final Faker faker = new Faker(new Locale("es"));
    private final Random random = new Random();
    private final UserRepository userRepository;

    public UserDataServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserData generateFakeUserData() {
        User user = new User();

        // Basic Info
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setName(faker.name().fullName());
        basicInfo.setEmail(faker.internet().emailAddress());
        basicInfo.setProfilePictureUrl("https://example.com/" + faker.internet().slug() + ".jpg");
        basicInfo.setApproximateAge(faker.number().numberBetween(18, 60));

        user.setBasicInfo(basicInfo);

        // Location
        LocationWrapper locationWrapper = new LocationWrapper();
        List<LocationHistoryItem> history = new ArrayList<>();
        for (int i = 0; i < faker.number().numberBetween(2, 5); i++) {
            LocationHistoryItem item = new LocationHistoryItem();
            item.setLatitude(faker.number().randomDouble(6, -90, 90));
            item.setLongitude(faker.number().randomDouble(6, -180, 180));
            item.setTimestamp(OffsetDateTime.now().minusDays(faker.number().numberBetween(1, 10)).toString());
            item.setPlace("Place " + (i + 1));
            history.add(item);
        }
        locationWrapper.setLocationHistory(history);

        LocationHistoryItem currentLocation = new LocationHistoryItem();
        currentLocation.setLatitude(faker.number().randomDouble(6, -90, 90));
        currentLocation.setLongitude(faker.number().randomDouble(6, -180, 180));
        currentLocation.setTimestamp(OffsetDateTime.now().toString());
        currentLocation.setPlace("Home, " + faker.address().city());
        locationWrapper.setCurrentLocation(currentLocation);

        user.setLocation(locationWrapper);

        // Contacts
        List<Contact> contacts = new ArrayList<>();
        for (int i = 0; i < faker.number().numberBetween(1, 4); i++) {
            Contact contact = new Contact();
            contact.setName("Contact " + (i + 1));
            contact.setEmail(faker.internet().emailAddress());
            contact.setPhoneNumber("+1" + faker.number().digits(10));
            contact.setUser(user);
            contacts.add(contact);
        }
        user.setContacts(contacts);

        // Emails
        Emails emails = new Emails();
        List<EmailMetadata> emailMetadataList = new ArrayList<>();
        for (int i = 0; i < faker.number().numberBetween(1, 3); i++) {
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
        List<SearchHistoryItem> searchHistory = new ArrayList<>();
        for (int i = 0; i < faker.number().numberBetween(1, 3); i++) {
            SearchHistoryItem searchItem = new SearchHistoryItem();
            searchItem.setQuery("Query " + (i + 1));
            searchItem.setTimestamp(OffsetDateTime.now().minusDays(faker.number().numberBetween(1, 5)).toString());
            searchHistory.add(searchItem);
        }
        googleActivity.setSearchHistory(searchHistory);

        List<YoutubeActivityItem> youtubeActivity = new ArrayList<>();
        for (int i = 0; i < faker.number().numberBetween(1, 3); i++) {
            YoutubeActivityItem ytItem = new YoutubeActivityItem();
            ytItem.setVideoTitle("Video " + (i + 1));
            ytItem.setChannel("Channel " + (i + 1));
            ytItem.setTimestamp(OffsetDateTime.now().minusDays(faker.number().numberBetween(1, 5)).toString());
            youtubeActivity.add(ytItem);
        }
        googleActivity.setYoutubeActivity(youtubeActivity);
        user.setGoogleActivity(googleActivity);

        // Google Pay transactions
        GooglePay googlePay = new GooglePay();
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < faker.number().numberBetween(1, 3); i++) {
            Transaction transaction = new Transaction();
            transaction.setMerchant("Merchant " + (i + 1));
            transaction.setAmount(faker.number().randomDouble(2, 10, 100));
            transaction.setTimestamp(OffsetDateTime.now().minusDays(faker.number().numberBetween(1, 5)).toString());
            transactions.add(transaction);
        }
        googlePay.setTransactions(transactions);
        user.setGooglePay(googlePay);

        user = userRepository.save(user);

        // Crear UserData
        UserData userData = new UserData();
        userData.setUser(user);

        return userData;
    }
}
