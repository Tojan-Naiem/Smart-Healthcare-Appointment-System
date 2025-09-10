Smart Healthcare Appointment System
A comprehensive, full-stack Smart Healthcare Appointment System built with Spring Boot 3. This application streamlines hospital operations by managing patients, doctors, appointments, prescriptions, and medical records with robust security and modern architecture.

🚀 Technology Stack
Backend Framework: Spring Boot 3.2.2

Security: Spring Security 6 with JWT Authentication

Persistence:

Relational Database: PostgreSQL with Spring Data JPA & Hibernate

NoSQL Database: MongoDB for prescriptions and medical records

Caching: Ehcache implemented as Hibernate Second-Level Cache

AOP: Spring AOP for comprehensive logging

Email: Spring Mail with Async processing for notifications

Testing: JUnit 5, Mockito, Testcontainers

Build Tool: Maven

✨ Features
🔐 Authentication & Authorization
JWT-based secure authentication

Role-based access control (RBAC) with three distinct roles:

ADMIN: Manage doctors and patients

DOCTOR: Manage appointments and prescriptions

PATIENT: Book/cancel appointments, view medical records

👨‍💼 Doctor Management
Admin can perform CRUD operations on doctor profiles

Patients can search doctors by specialty

Doctor data cached with Hibernate L2 cache for performance

👥 Patient Management
Admin registers new patients

Patients can update their personal information

Secure access to personal data

📅 Appointment Management
Patients can book and cancel appointments

Intelligent double-booking prevention system

Doctors can mark appointments as completed

Automated email confirmations and notifications

💊 Prescription & Medical Records
Doctors create digital prescriptions stored in MongoDB

Patients have access to their prescription history

Support for lab results and medical notes

Secure, role-based access to medical records
