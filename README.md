# 📊 Hackathon Data Generator API

## 📝 Overview
Hackathon Data Generator API is a Spring Boot-based backend service that generates and manages **fake user data** for Facebook, Google, and LinkedIn. This data can be used for testing, analytics, and development purposes. The API provides **scheduled fake data generation** and **RESTful endpoints** for retrieving and managing the generated data.

## 🚀 Features
- **Generate fake user profiles** for Facebook, Google, and LinkedIn.
- **Paginated retrieval** of user profiles.
- **Scheduled data persistence** in the database.
- **Search functionality** by email and ID.
- **Data storage using repositories** with JPA and Spring Data.

## 🛠️ Technologies Used
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Scheduler**
- **Faker (com.github.javafaker)**
- **PostgreSQL / MySQL / MongoDB (configurable)**
- **Lombok**
- **Docker (optional for containerized deployment)**

---

## 🏗️ **Project Structure**
```
hackathon-data-generator/
│── src/
│   ├── main/
│   │   ├── java/com/guayaquil/hackathon/
│   │   │   ├── controllers/       # REST Controllers
│   │   │   ├── models/            # Entity Models (Facebook, Google, LinkedIn)
│   │   │   ├── repositories/      # JPA Repositories
│   │   │   ├── schedule/          # Scheduled Tasks for Fake Data Generation
│   │   │   ├── services/          # Service Implementations
│   │   │   ├── HackathonApplication.java   # Main Application Class
│   ├── resources/
│   │   ├── application.properties  # Database and server configuration
│── pom.xml  # Dependencies (Maven)
│── README.md  # Documentation
```

---

## 🖥️ **API Endpoints**

### 📌 Facebook User API
| HTTP Method | Endpoint                     | Description |
|------------|-----------------------------|-------------|
| `GET`      | `/api/facebook-users`        | Get all Facebook users (paginated) |
| `GET`      | `/api/facebook-users/search?email={email}` | Get Facebook user by email |

### 📌 Google User API
| HTTP Method | Endpoint                     | Description |
|------------|-----------------------------|-------------|
| `GET`      | `/api/user-data`             | Get all Google users (paginated) |
| `GET`      | `/api/user-data/search?email={email}` | Get Google user by email |

### 📌 LinkedIn Profile API
| HTTP Method | Endpoint                          | Description |
|------------|----------------------------------|-------------|
| `GET`      | `/api/professional-profiles`     | Get all LinkedIn profiles (paginated) |
| `GET`      | `/api/professional-profiles/{id}` | Get LinkedIn profile by ID |
| `POST`     | `/api/professional-profiles/generate` | Generate and save a fake LinkedIn profile |

---

## 🕒 **Scheduled Fake Data Generation**
The application automatically generates and persists fake data at fixed intervals:

| Service     | Interval |
|------------|----------|
| Facebook Fake User | Every **5 seconds** |
| Google Fake User   | Every **5 seconds** |
| LinkedIn Profile   | Every **1 second** |

The logic for scheduled data generation is handled in the `FakeDataScheduler.java` class.

---

## 🔧 **Setup & Installation**

### 📌 1. Clone the Repository
```sh
git clone https://github.com/Anyel-ec/hackathon-data-generator.git
cd hackathon-data-generator
```

### 📌 2. Configure the Database
Modify `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hackathon_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 📌 3. Build & Run the Project
Using Maven:
```sh
mvn clean install
mvn spring-boot:run
```

Or with Docker (if you have a `Dockerfile`):
```sh
docker build -t hackathon-api .
docker run -p 8080:8080 hackathon-api
```

---

## 🧪 **Testing the API**
You can test the API using:
- **Postman** or **cURL**:
```sh
curl -X GET http://localhost:8080/api/facebook-users
```
- **Swagger UI** (if enabled) at:
```
http://localhost:8080/swagger-ui.html
```

---

## 🤝 Contributing
Feel free to **fork** this repository, create a branch, and submit a **pull request**.  
If you find a bug or want to request a feature, please **open an issue**.

---

## 📜 License
This project is licensed under the **MIT License**.

---
