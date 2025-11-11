# Smart Healthcare Appointment System

A comprehensive backend healthcare appointment management system built with Spring Boot 3 that streamlines hospital operations through secure patient-doctor interactions, appointment scheduling, prescription management, and medical records handling.

## Table of Contents

* [Overview](#overview)
* [Features](#features)
* [Tech Stack](#tech-stack)
* [Architecture & Design Patterns](#architecture--design-patterns)
* [Project Structure](#project-structure)
* [Getting Started](#getting-started)
* [Testing](#testing)
* [API Documentation](#api-documentation)
* [Contributing](#contributing)
* [Contact & Support](#contact--support)
* [Acknowledgments](#acknowledgments)

## Overview

The Smart Healthcare Appointment System is a robust backend solution designed to modernize healthcare facility operations. It provides role-based access control for admins, doctors, and patients, enabling efficient appointment scheduling, prescription management, and secure medical record storage across both relational and NoSQL databases.

### Key Highlights

- JWT-based authentication with role-based authorization (RBAC)
- Dual database architecture: MySQL for relational data, MongoDB for medical documents
- Conflict-free appointment scheduling with double-booking prevention
- Automated email notifications for appointment confirmations
- Hibernate L2 caching for optimized doctor data retrieval
- Comprehensive test coverage with unit and integration tests

## Features

### Authentication & Authorization

- JWT-based secure authentication with token expiration handling
- Three-tier role system:
  - ADMIN: Manages doctor and patient accounts, oversees system operations
  - DOCTOR: Handles appointments, creates prescriptions, updates appointment status
  - PATIENT: Books/cancels appointments, views medical history and prescriptions
- Secure password encryption using BCrypt

### Doctor Management

- Complete CRUD operations (Admin-only)
- Search doctors by specialty with pagination
- Hibernate L2 cache implementation for performance optimization
- Profile management with specialty tracking

### Patient Management

- Admin-controlled patient registration
- Self-service profile updates for patients
- Secure access to personal medical data
- Medical history tracking

### Appointment Management

- Intelligent booking system with conflict detection
- Double-booking prevention algorithm validates time slots
- Real-time availability checking
- Appointment status tracking (SCHEDULED, COMPLETED, CANCELLED)
- Doctor-specific daily appointment views
- Patient-initiated cancellations
- Doctor-controlled status updates
- Automated email notifications to both parties upon booking

### Prescription & Medical Records

- Digital prescription storage in MongoDB
- Comprehensive prescription history for patients
- Lab results integration
- Medical notes and instructions tracking
- Secure, role-based access controls
- Medicine dosage and instructions management

### Logging & Monitoring

- Spring AOP aspect-oriented logging for all business operations
- Method-level execution tracking
- Appointment and prescription activity logging
- System event auditing

### Testing

- Unit tests with JUnit 5 and Mockito
- Integration tests with H2 in-memory database
- CRUD operation validation for all entities
- Specialized conflict detection test cases
- Repository layer testing with @DataJpaTest
- Service layer mock testing

## Tech Stack

### Core Framework
- Spring Boot 3.5.5 - Modern enterprise Java framework
- Java 17 - Long-term support version

### Security
- Spring Security 6 - Authentication and authorization
- JWT (JJWT 0.11.5) - Token-based authentication

### Data Persistence
- Spring Data JPA - Relational data access with Hibernate
- Spring Data MongoDB - NoSQL document storage
- MySQL 8.0 - Primary relational database
- MongoDB - Medical records and prescriptions storage

### Caching
- Ehcache - Second-level caching for doctor entities
- Spring Cache Abstraction - Unified caching API

### Additional Technologies
- Spring AOP - Cross-cutting concerns and logging
- Spring Mail - Asynchronous email notifications
- MapStruct 1.5.5 - DTO-Entity mapping
- Lombok - Boilerplate code reduction
- Maven - Build automation and dependency management

### Testing
- JUnit 5 - Unit testing framework
- Mockito - Mocking framework
- H2 Database - In-memory testing database
- Spring Boot Test - Integration testing support

## Architecture & Design Patterns

### Layered Architecture

The application follows a standard three-tier architecture:
```
Client Layer (REST API)
        |
Controller Layer (Request Handling)
        |
Service Layer (Business Logic)
        |
Repository Layer (Data Access)
        |
Database Layer (MySQL + MongoDB)
```

### Design Patterns

1. Repository Pattern: Abstracts data access logic through Spring Data repositories
2. DTO Pattern: Separates internal domain models from API representations using MapStruct
3. Service Layer Pattern: Encapsulates business logic in dedicated service classes
4. Dependency Injection: Spring IoC container manages component lifecycle
5. Aspect-Oriented Programming: Cross-cutting logging concerns handled via Spring AOP
6. Builder Pattern: Lombok @Builder for entity construction
7. Strategy Pattern: Multiple authentication strategies via Spring Security
8. Factory Pattern: Entity creation through DTOs and mappers

### Data Architecture

- Single Table Inheritance: User entity hierarchy (Patient, Doctor, Admin)
- Relational Storage (MySQL):
  - User management (patients, doctors, admins)
  - Appointments with foreign key relationships
  - Role-based access control tables
  
- Document Storage (MongoDB):
  - Prescriptions with embedded medicine arrays
  - Lab results
  - Medical records and notes

### Security Architecture

- JWT Filter Chain: Pre-authentication filter validates tokens
- Stateless Sessions: No server-side session storage
- Role-based Authorization: Method-level security with @PreAuthorize
- Password Encryption: BCrypt hashing for secure storage

### Caching Strategy

- L2 Cache: Hibernate second-level cache for Doctor entities
- Cache-aside Pattern: Manual cache management with @Cacheable annotations
- Eviction Policy: Cache invalidation on updates

## Project Structure
```
Smart-Healthcare-Appointment-System/
├── src/
│   ├── main/
│   │   ├── java/org/example/smarthealthcareappointmentsystem/
│   │   │   ├── aop/                          # Aspect-oriented logging
│   │   │   │   └── LoggingAspect.java
│   │   │   ├── config/                       # Configuration classes
│   │   │   │   ├── AppConfig.java            # Cache configuration
│   │   │   │   ├── JwtFilter.java            # JWT authentication filter
│   │   │   │   └── SecurityConfiguration.java # Security setup
│   │   │   ├── controller/                   # REST API endpoints
│   │   │   │   ├── AppointmentController.java
│   │   │   │   ├── AuthController.java       # Login/Register
│   │   │   │   ├── DoctorController.java
│   │   │   │   ├── PatientController.java
│   │   │   │   ├── PrescriptionController.java
│   │   │   │   └── RoleController.java
│   │   │   ├── dto/                          # Data Transfer Objects
│   │   │   │   ├── AppointmentDTO.java
│   │   │   │   ├── DoctorDTO.java
│   │   │   │   ├── PatientDTO.java
│   │   │   │   ├── PrescriptionDTO.java
│   │   │   │   ├── MedicineDTO.java
│   │   │   │   ├── LabResultDTO.java
│   │   │   │   └── LoginRequest.java
│   │   │   ├── entity/                       # Domain models
│   │   │   │   ├── User.java                 # Base entity
│   │   │   │   ├── Doctor.java               # Extends User
│   │   │   │   ├── Patient.java              # Extends User
│   │   │   │   ├── Appointment.java          # MySQL entity
│   │   │   │   ├── Prescription.java         # MongoDB document
│   │   │   │   ├── Medicine.java
│   │   │   │   ├── LabResult.java
│   │   │   │   ├── Role.java
│   │   │   │   └── Status.java               # Enum
│   │   │   ├── exception/                    # Custom exceptions
│   │   │   │   ├── AdviceException.java      # Global handler
│   │   │   │   ├── AlreadyExistsException.java
│   │   │   │   ├── ResourcesNotFound.java
│   │   │   │   ├── ForbiddenActionException.java
│   │   │   │   └── JWTException.java
│   │   │   ├── mapper/                       # MapStruct mappers
│   │   │   │   └── UserMapperDTO.java
│   │   │   ├── repository/                   # Data access layer
│   │   │   │   ├── AppointmentRepository.java
│   │   │   │   ├── DoctorRepository.java
│   │   │   │   ├── PatientRepository.java
│   │   │   │   ├── PrescriptionRepository.java # MongoDB
│   │   │   │   ├── RoleRepository.java
│   │   │   │   └── UserRepository.java
│   │   │   ├── service/                      # Business logic interfaces
│   │   │   │   ├── AppointmentService.java
│   │   │   │   ├── DoctorService.java
│   │   │   │   ├── PatientService.java
│   │   │   │   ├── PrescriptionService.java
│   │   │   │   ├── RoleService.java
│   │   │   │   └── UserService.java
│   │   │   └── service/Impl/                 # Service implementations
│   │   │       ├── AppointmentServiceImp.java
│   │   │       ├── DoctorServiceImp.java
│   │   │       ├── PatientServiceImp.java
│   │   │       ├── PrescriptionServiceImp.java
│   │   │       ├── RoleServiceImp.java
│   │   │       ├── UserServiceImp.java
│   │   │       └── NotificationService.java  # Email service
│   │   └── resources/
│   │       ├── application.properties        # Main configuration
│   │       └── application-test.properties   # Test configuration
│   └── test/                                 # Test suites
│       └── java/org/example/smarthealthcareappointmentsystem/
│           ├── dto/
│           │   └── UserMapperDTOTest.java
│           ├── repository/
│           │   └── DoctorRepositoryTest.java
│           └── service/
│               ├── DoctorServiceTest.java
│               ├── PatientServiceImpTest.java
│               ├── AppointmentServiceImpTest.java
│               └── RoleServiceTest.java
├── pom.xml                                   # Maven dependencies
└── README.md
```

## Getting Started

### Prerequisites

Ensure you have the following installed:

- Java 17 or higher
- Maven 3.9+
- MySQL 8.0+
- MongoDB 4.4+
- Git

### Installation Steps

1. Clone the repository
```bash
   git clone https://github.com/Tojan-Naiem/Smart-Healthcare-Appointment-System.git
   cd Smart-Healthcare-Appointment-System
```

2. Set up MySQL database
```sql
   CREATE DATABASE healthcareDB;
```

3. Set up MongoDB database
   
   MongoDB will automatically create the healthcareDB database on first connection.

4. Configure database connections
   
   Edit `src/main/resources/application.properties`:
```properties
   # MySQL Configuration
   spring.datasource.url=jdbc:mysql://localhost:3306/healthcareDB
   spring.datasource.username=YOUR_MYSQL_USERNAME
   spring.datasource.password=YOUR_MYSQL_PASSWORD
   
   # MongoDB Configuration
   spring.data.mongodb.host=localhost
   spring.data.mongodb.port=27017
   spring.data.mongodb.database=healthcareDB
   
   # Email Configuration (optional)
   spring.mail.username=YOUR_EMAIL@gmail.com
   spring.mail.password=YOUR_APP_PASSWORD
```

5. Build the project
```bash
   ./mvnw clean install
```
   
   On Windows:
```bash
   mvnw.cmd clean install
```

6. Run the application
```bash
   ./mvnw spring-boot:run
```

7. Access the API
   
   The application will start at: `http://localhost:8080`

### Initial Setup

1. Create roles (POST to `/api/v1/role/` - requires ADMIN):
```json
   { "roleName": "ADMIN" }
   { "roleName": "DOCTOR" }
   { "roleName": "PATIENT" }
```

2. Register admin user (POST to `/api/v1/auth/register`):
```json
   {
     "fullName": "Admin User",
     "username": "admin",
     "email": "admin@hospital.com",
     "password": "securePassword123",
     "birthday": "1990-01-01",
     "role": { "id": 1, "name": "ADMIN" }
   }
```

3. Login (POST to `/api/v1/auth/login`):
```json
   {
     "username": "admin",
     "password": "securePassword123"
   }
```
   
   Save the returned JWT token for authenticated requests.

## Testing

### Test Structure

The project includes comprehensive test coverage:

- Unit Tests: Service layer business logic with Mockito mocks
- Integration Tests: Repository layer with H2 in-memory database
- DTO Mapping Tests: MapStruct mapper validation

### Running Tests
```bash
# Run all tests
./mvnw test

# Run specific test class
./mvnw test -Dtest=DoctorServiceTest

# Run tests with detailed output
./mvnw test -X
```

### Test Coverage

Key test suites:

- DoctorServiceTest - 80+ test cases covering CRUD operations
- PatientServiceImpTest - Patient management validation
- AppointmentServiceImpTest - Conflict detection algorithms
- DoctorRepositoryTest - Data access layer with @DataJpaTest
- UserMapperDTOTest - DTO-Entity mapping validation

### Sample Test Output
```
[INFO] Tests run: 45, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] --- APPOINTMENT CONFLICT DETECTION ---
[INFO] checkConflict_success - Prevents double booking
[INFO] checkConflict_shouldFailWhenConflictExists - Validates time overlap
```

## API Documentation

### Authentication Endpoints

#### Register Admin
```http
POST /api/v1/auth/register
Content-Type: application/json

{
  "fullName": "John Admin",
  "username": "johnadmin",
  "email": "john@hospital.com",
  "password": "SecurePass123!",
  "birthday": "1985-05-15",
  "role": { "id": 1, "name": "ADMIN" }
}
```

#### Login
```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "username": "johnadmin",
  "password": "SecurePass123!"
}

Response: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

### Doctor Management (Admin Only)

#### Create Doctor
```http
POST /api/v1/doctor/
Authorization: Bearer {JWT_TOKEN}
Content-Type: application/json

{
  "fullName": "Dr. Sarah Wilson",
  "username": "drwilson",
  "email": "sarah.wilson@hospital.com",
  "password": "Doctor123!",
  "birthday": "1980-03-20",
  "specialty": "Cardiology"
}
```

#### Get All Doctors (with optional filters)
```http
GET /api/v1/doctor/?page=0&size=10&filterKey=specialty&filterValue=Cardiology
Authorization: Bearer {JWT_TOKEN}
```

#### Get Doctor by ID
```http
GET /api/v1/doctor/{id}
Authorization: Bearer {JWT_TOKEN}
```

#### Update Doctor
```http
PUT /api/v1/doctor/{id}
Authorization: Bearer {JWT_TOKEN}
Content-Type: application/json

{
  "fullName": "Dr. Sarah Wilson",
  "specialty": "Pediatric Cardiology",
  "birthday": "1980-03-20"
}
```

#### Delete Doctor
```http
DELETE /api/v1/doctor/{id}
Authorization: Bearer {JWT_TOKEN}
```

### Patient Management

#### Create Patient (Admin Only)
```http
POST /api/v1/patient/
Authorization: Bearer {JWT_TOKEN}
Content-Type: application/json

{
  "fullName": "Michael Brown",
  "username": "mbrown",
  "email": "michael.brown@email.com",
  "password": "Patient123!",
  "birthday": "1995-07-10"
}
```

#### Get All Patients
```http
GET /api/v1/patient/?page=0&size=10
Authorization: Bearer {JWT_TOKEN}
```

#### Get Patient by ID
```http
GET /api/v1/patient/{id}
Authorization: Bearer {JWT_TOKEN}
```

#### Update Patient (Patient Only)
```http
PUT /api/v1/patient/{id}
Authorization: Bearer {JWT_TOKEN}
Content-Type: application/json

{
  "fullName": "Michael J. Brown",
  "birthday": "1995-07-10"
}
```

### Appointment Management

#### Book Appointment (Patient Only)
```http
POST /api/v1/appointment/
Authorization: Bearer {JWT_TOKEN}
Content-Type: application/json

{
  "doctorId": 5,
  "appointmentDateTime": "2025-11-15T10:30:00",
  "duration": 30,
  "notes": "Annual checkup"
}
```

#### Get Doctor's Today Appointments (Doctor Only)
```http
GET /api/v1/appointment/
Authorization: Bearer {JWT_TOKEN}
```

#### Update Appointment Status (Doctor Only)
```http
PUT /api/v1/appointment/{id}
Authorization: Bearer {JWT_TOKEN}
Content-Type: application/json

{
  "status": "COMPLETED"
}
```

#### Cancel Appointment (Patient Only)
```http
DELETE /api/v1/appointment/{id}
Authorization: Bearer {JWT_TOKEN}
```

### Prescription Management

#### Create Prescription (Doctor Only)
```http
POST /api/v1/prescription/
Authorization: Bearer {JWT_TOKEN}
Content-Type: application/json

{
  "patientId": 10,
  "medicines": [
    {
      "medicineName": "Amoxicillin",
      "dosage": "500mg, 3 times daily"
    }
  ],
  "instructions": "Take with food. Complete full course.",
  "issueDate": "2025-11-11",
  "labResultDTO": [
    {
      "testName": "Blood Pressure",
      "result": "120/80 mmHg"
    }
  ]
}
```

#### Get Patient Prescriptions (Patient Only)
```http
GET /api/v1/prescription/
Authorization: Bearer {JWT_TOKEN}
```

### Role Management (Admin Only)

#### Create Role
```http
POST /api/v1/role/
Authorization: Bearer {JWT_TOKEN}
Content-Type: application/json

{
  "roleName": "DOCTOR"
}
```

## Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch
```bash
   git checkout -b feature/AmazingFeature
```
3. Commit your changes
```bash
   git commit -m 'Add some AmazingFeature'
```
4. Push to the branch
```bash
   git push origin feature/AmazingFeature
```
5. Open a Pull Request

### Code Style Guidelines

- Follow Java naming conventions
- Write meaningful commit messages
- Add JavaDoc comments for public methods
- Include unit tests for new features
- Ensure all tests pass before submitting PR

## Contact & Support

Developer: Tojan Naiem  
GitHub: @Tojan-Naiem  
Project Link: https://github.com/Tojan-Naiem/Smart-Healthcare-Appointment-System

For issues and feature requests, please use the GitHub Issues page.

## Acknowledgments

- Spring Boot Team for the excellent framework
- Hibernate for ORM capabilities
- MongoDB for flexible document storage
- MapStruct for simplifying DTO mappings
- All contributors and testers

Built with Spring Boot 3
