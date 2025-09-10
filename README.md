# Smart Healthcare Appointment System

A backend Smart Healthcare Appointment System built with **Spring Boot 3** to streamline hospital operations by managing patients, doctors, appointments, prescriptions, and medical records with a secure, modern architecture.

---

## 📸 Screenshots / Demo



*You can add multiple screenshots or a GIF here to showcase your application's user interface and functionality.*

---

## 🚀 Features

### 🔐 Authentication & Authorization
* **JWT-based** secure authentication.
* **Role-based access control (RBAC)** with three distinct roles:
    * **ADMIN:** Manages doctor and patient accounts.
    * **DOCTOR:** Manages appointments and prescriptions.
    * **PATIENT:** Books/cancels appointments and views medical records.

### 👨‍💼 Doctor Management
* **CRUD** operations performed by the Admin.
* Search for doctors by specialty.
* **Hibernate L2 cache** for fast data retrieval.

### 👥 Patient Management
* Admin registers new patients.
* Patients can update their personal information.
* Secure access to personal data.

### 📅 Appointment Management
* Patients can book and cancel appointments.
* Double-booking prevention.
* Doctors can mark appointments as completed.
* Automated email notifications.

### 💊 Prescription & Medical Records
* Digital prescriptions stored in **MongoDB**.
* Patients can view their prescription history.
* Supports lab results and medical notes.
* Role-based secure access to all medical data.

### 📊 Logging & Monitoring
* **Spring AOP** is used for method-level logging.
* Logs all key events, including appointments, prescriptions, and system actions.

### ✅ Testing
* **Unit tests** with **JUnit 5** and **Mockito**.
* **Integration tests** for key functionalities.
* **CRUD** operation tests for all entities.
* Specific test cases for double-booking prevention.

---

## 🏗️ Architecture

The application follows a standard layered architecture:

**Client → Controllers → Service Layer → Repository Layer**

* **Spring AOP** is integrated for logging.
* **Spring Security** handles JWT authentication.

### Data Storage
* **MySQL:** Stores relational data for Doctors, Patients, and Appointments.
* **MongoDB:** Used for non-relational data like Prescriptions, Medical Records, and Lab Results.
* **Ehcache:** Provides in-memory caching for frequently accessed doctor data.

---

## 🛠️ Technology Stack
* **Backend:** Spring Boot 3.2.2
* **Security:** Spring Security 6 with JWT
* **Relational DB:** MySQL with Spring Data JPA & Hibernate
* **NoSQL DB:** MongoDB
* **Caching:** Ehcache
* **AOP:** Spring AOP
* **Email:** Spring Mail with Async processing
* **Testing:** JUnit 5, Mockito
* **Build Tool:** Maven

---

## ⚙️ Installation

### Prerequisites
* Java 11 or higher
* Maven
* MySQL & MongoDB databases

### Steps
1.  **Clone the repository**
    ```bash
    git clone [https://github.com/Tojan-Naiem/Smart-Healthcare-Appointment-System.git](https://github.com/Tojan-Naiem/Smart-Healthcare-Appointment-System.git)
    ```
2.  **Navigate to the project directory**
    ```bash
    cd Smart-Healthcare-Appointment-System
    ```
3.  **Configure database settings**
    * Update your MySQL and MongoDB connection details in `src/main/resources/application.properties`.
4.  **Build the project**
    ```bash
    mvn clean install
    ```
5.  **Run the application**
    ```bash
    mvn spring-boot:run
    ```

---

## 📱 Usage
Once the application is running, you can access it via your web browser at:
`http://localhost:8080`

* **Register** as a Patient or Doctor.
* **Manage** appointments, profiles, and prescriptions from the user-specific dashboards.


Screenshot :

use case : 

use case 1: 

![image.png](attachment:d1202bed-18d4-4b2d-99db-66cf73edca17:image.png)

![image.png](attachment:9b9dd6b8-111f-4cf3-a4f9-1b365f127375:image.png)

![image.png](attachment:dbf501bd-3475-4343-b0f8-3a136ee8b790:image.png)

use case 2: 

![image.png](attachment:2617506f-49a2-47e4-bd0b-edeee47db4b9:image.png)

![image.png](attachment:b889bbf7-55a5-44e7-98ab-83baf91a4203:image.png)

![image.png](attachment:8063a7de-8450-4893-8e7b-a6546716e85a:image.png)

![image.png](attachment:02c21bef-91a6-4af6-a60a-824329004535:image.png)

![image.png](attachment:6ab39ee7-c337-48a6-9135-5ef9e55a3164:image.png)

use case3: 

![image.png](attachment:3828357c-0f2e-4f4e-a59b-a756c5e617d4:image.png)

![image.png](attachment:b8e318f9-7fee-4cd7-8dd1-c8788c9a1602:image.png)

![image.png](attachment:885df026-3f0f-4844-bac4-b3f99fdf2d19:image.png)
