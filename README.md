# 🏥 Smart Healthcare Appointment System

A comprehensive healthcare management system built with **Spring Boot** that streamlines appointment scheduling, doctor management, patient records, and real-time notifications for modern healthcare facilities.

![Healthcare System](https://img.shields.io/badge/Spring%20Boot-Healthcare-blue?style=for-the-badge&logo=spring)
![Java](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=java)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apache-maven)

## 🌟 Features

### 📅 **Appointment Management**
- **Schedule Appointments**: Create and manage patient appointments with doctors
- **Real-time Availability**: Check doctor availability and schedule accordingly
- **Automated Notifications**: Email notifications for appointment confirmations and reminders
- **Today's Appointments**: Quick view of current day's scheduled appointments

### 👨‍⚕️ **Doctor Management**
- **Doctor Profiles**: Comprehensive doctor information with specializations
- **Availability Management**: Set and update doctor working hours and availability
- **Specialization Tracking**: Organize doctors by medical specialties
- **Documentation**: Complete API documentation for doctor operations

### 👥 **Patient Management**
- **Patient Records**: Secure storage and management of patient information
- **Medical History**: Track patient medical history and previous appointments
- **Contact Management**: Patient contact information and communication preferences

### 📧 **Communication System**
- **Email Service**: Automated email notifications for appointments
- **Real-time Updates**: Instant notifications for appointment changes
- **Reminder System**: Automated appointment reminders

## 🏗️ System Architecture

```mermaid
graph TB
    subgraph "Presentation Layer"
        A[REST Controllers]
        B[API Documentation]
    end
    
    subgraph "Business Logic Layer"
        C[Doctor Service]
        D[Appointment Service]
        E[Patient Service]
        F[Email Service]
    end
    
    subgraph "Data Access Layer"
        G[Doctor Repository]
        H[Appointment Repository]
        I[Patient Repository]
    end
    
    subgraph "Database Layer"
        J[(MySQL Database)]
    end
    
    subgraph "External Services"
        K[Email Provider]
        L[Notification Service]
    end

    A --> C
    A --> D
    A --> E
    B --> A
    
    C --> G
    D --> H
    E --> I
    F --> K
    
    G --> J
    H --> J
    I --> J
    
    D --> F
    F --> L

    style A fill:#e1f5fe
    style C fill:#f3e5f5
    style G fill:#fff3e0
    style J fill:#e8f5e8
```

## 🗄️ Database Design

```mermaid
erDiagram
    DOCTOR {
        Long id PK
        String name
        String email
        String phone
        String specialization
        String address
        LocalTime startTime
        LocalTime endTime
        Boolean isAvailable
        LocalDateTime createdAt
        LocalDateTime updatedAt
    }
    
    PATIENT {
        Long id PK
        String name
        String email
        String phone
        Date dateOfBirth
        String gender
        String address
        String medicalHistory
        LocalDateTime createdAt
        LocalDateTime updatedAt
    }
    
    APPOINTMENT {
        Long id PK
        Long doctorId FK
        Long patientId FK
        LocalDateTime appointmentDate
        String status
        String notes
        String reason
        LocalDateTime createdAt
        LocalDateTime updatedAt
    }
    
    NOTIFICATION {
        Long id PK
        Long appointmentId FK
        String type
        String message
        Boolean sent
        LocalDateTime sentAt
        LocalDateTime createdAt
    }

    DOCTOR ||--o{ APPOINTMENT : "has many"
    PATIENT ||--o{ APPOINTMENT : "has many"
    APPOINTMENT ||--o{ NOTIFICATION : "generates"
```

## 🔄 System Workflows

### 📋 Add Doctor Workflow

```mermaid
sequenceDiagram
    participant Client
    participant Controller
    participant Service
    participant Repository
    participant Database
    participant EmailService

    Client->>Controller: POST /api/doctors
    Controller->>Service: createDoctor(doctorDTO)
    Service->>Repository: save(doctor)
    Repository->>Database: INSERT doctor
    Database-->>Repository: doctor saved
    Repository-->>Service: saved doctor
    Service->>EmailService: sendWelcomeEmail()
    EmailService-->>Service: email sent
    Service-->>Controller: doctor created
    Controller-->>Client: 201 Created + doctor data
```

### 📅 Book Appointment Workflow

```mermaid
sequenceDiagram
    participant Patient
    participant AppController
    participant AppService
    participant DoctorService
    participant Repository
    participant EmailService

    Patient->>AppController: POST /api/appointments
    AppController->>AppService: createAppointment(appointmentDTO)
    AppService->>DoctorService: checkAvailability(doctorId, date)
    DoctorService-->>AppService: availability confirmed
    AppService->>Repository: save(appointment)
    Repository-->>AppService: appointment saved
    AppService->>EmailService: sendConfirmation(patient, doctor)
    EmailService-->>AppService: confirmation sent
    AppService-->>AppController: appointment created
    AppController-->>Patient: 201 Created + appointment details
```

### 📊 Daily Appointments View

```mermaid
sequenceDiagram
    participant Doctor
    participant Controller
    participant Service
    participant Repository

    Doctor->>Controller: GET /api/appointments/today
    Controller->>Service: getTodayAppointments()
    Service->>Repository: findByAppointmentDate(today)
    Repository-->>Service: List<Appointment>
    Service-->>Controller: formatted appointments
    Controller-->>Doctor: 200 OK + appointments list
```

## 🚀 Getting Started

### Prerequisites
- **Java 17+**
- **Maven 3.6+**
- **MySQL 8.0+**
- **Git**

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Tojan-Naiem/Smart-Healthcare-Appointment-System.git
   cd Smart-Healthcare-Appointment-System
   ```

2. **Switch to develop branch**
   ```bash
   git checkout develop
   ```

3. **Configure Database**
   ```properties
   # application.properties
   spring.datasource.url=jdbc:mysql://localhost:3306/healthcare_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. **Configure Email Service**
   ```properties
   # Email configuration
   spring.mail.host=smtp.gmail.com
   spring.mail.port=587
   spring.mail.username=your_email@gmail.com
   spring.mail.password=your_app_password
   ```

5. **Build and Run**
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

6. **Access the Application**
   - **API Base URL**: `http://localhost:8080/api`
   - **Documentation**: `http://localhost:8080/swagger-ui.html`

## 📡 API Endpoints

### 👨‍⚕️ Doctor Management
```http
GET    /api/doctors              # Get all doctors
POST   /api/doctors              # Create new doctor
GET    /api/doctors/{id}         # Get doctor by ID
PUT    /api/doctors/{id}         # Update doctor
DELETE /api/doctors/{id}         # Delete doctor
GET    /api/doctors/available    # Get available doctors
```

### 📅 Appointment Management
```http
GET    /api/appointments         # Get all appointments
POST   /api/appointments         # Create new appointment
GET    /api/appointments/{id}    # Get appointment by ID
PUT    /api/appointments/{id}    # Update appointment
DELETE /api/appointments/{id}    # Delete appointment
GET    /api/appointments/today   # Get today's appointments
```

### 👥 Patient Management
```http
GET    /api/patients             # Get all patients
POST   /api/patients             # Create new patient
GET    /api/patients/{id}        # Get patient by ID
PUT    /api/patients/{id}        # Update patient
DELETE /api/patients/{id}        # Delete patient
```

## 🛠️ Technology Stack

| Technology | Purpose | Version |
|------------|---------|---------|
| **Spring Boot** | Backend Framework | 3.x |
| **Spring Data JPA** | Data Access Layer | Latest |
| **MySQL** | Database | 8.0+ |
| **Maven** | Build Tool | 3.6+ |
| **Spring Mail** | Email Service | Latest |
| **Swagger/OpenAPI** | API Documentation | 3.x |
| **Lombok** | Code Generation | Latest |

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/healthcare/
│   │       ├── controller/          # REST Controllers
│   │       │   ├── DoctorController.java
│   │       │   ├── AppointmentController.java
│   │       │   └── PatientController.java
│   │       ├── service/             # Business Logic
│   │       │   ├── DoctorService.java
│   │       │   ├── AppointmentService.java
│   │       │   ├── PatientService.java
│   │       │   └── EmailService.java
│   │       ├── repository/          # Data Access
│   │       │   ├── DoctorRepository.java
│   │       │   ├── AppointmentRepository.java
│   │       │   └── PatientRepository.java
│   │       ├── model/               # Entity Classes
│   │       │   ├── Doctor.java
│   │       │   ├── Appointment.java
│   │       │   ├── Patient.java
│   │       │   └── Notification.java
│   │       ├── dto/                 # Data Transfer Objects
│   │       ├── config/              # Configuration Classes
│   │       └── HealthcareApplication.java
│   └── resources/
│       ├── application.properties
│       └── static/
└── test/                           # Unit & Integration Tests
```

## 🔧 Configuration

### Database Configuration
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/healthcare_db
    username: ${DB_USERNAME:root}
    password: ${DB_PASSWORD:password}
    driver-class-name: com.mysql.cj.jdbc.Driver
  
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
```

### Email Configuration
```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: ${EMAIL_USERNAME:your-email@gmail.com}
    password: ${EMAIL_PASSWORD:your-app-password}
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
```

## 🧪 Testing

Run the test suite:
```bash
./mvnw test
```

Run integration tests:
```bash
./mvnw integration-test
```


## 🔒 Security Features

- **Data Validation**: Input validation and sanitization
- **Error Handling**: Comprehensive error handling and logging
- **CORS Configuration**: Secure cross-origin resource sharing
- **SQL Injection Prevention**: Parameterized queries
- **Authentication Ready**: Prepared for JWT integration


---

<div align="center">

**Built with ❤️ for better healthcare management**

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white)](https://www.java.com)
[![MySQL](https://img.shields.io/badge/MySQL-00000F?style=flat&logo=mysql&logoColor=white)](https://www.mysql.com)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apache-maven&logoColor=white)](https://maven.apache.org)

</div>
