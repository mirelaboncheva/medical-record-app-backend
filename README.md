# 🩺 Medical Record System – Project Documentation

## 🔍 Project Purpose

The **Medical Record System** is a web-based application developed using Java Spring Boot that manages patient health records with strict role-based access. It supports three main user roles:

- **Admin** – Full system access and report generation.
- **Doctor** – Manages patient appointments, diagnoses, and treatments.
- **Patient** – Views personal medical history.

This system replicates real-world healthcare workflows by allowing doctors to treat patients, track appointments, and issue sick leaves. Patients can access their data securely, while admins manage the overall system.

---

## 🎯 Core Features

### ✅ Role-Based Access Control (RBAC)
| Role     | Capabilities                                                                 |
|----------|------------------------------------------------------------------------------|
| Admin    | Full CRUD on all entities and system-wide statistics                         |
| Doctor   | Manages their patients: appointments, diagnoses, treatments, sick leaves     |
| Patient  | Views personal health records, appointments, diagnoses, and sick leaves      |

### 💡 Real-World Simulation
- **Doctors** have unique IDs and specializations.
- **Patients** have a national ID (EGN) and insurance status.
- **Doctor-patient assignments** track personal doctor relationships.
- **Appointments** record timestamps and medical context.
- Diagnoses, treatments, and sick leaves are linked to appointments.

---

## 🏗️ System Architecture

The system uses a layered architecture:

- **Controller** – Handles RESTful API requests.
- **Service** – Contains business logic.
- **Repository** – Interfaces for database interaction (Spring Data JPA).
- **DTOs** – Abstracts internal data models.
- **Security Layer** – JWT authentication and RBAC via Spring Security.

---

## 🗃️ Data Model Overview

### `User`
Implements `UserDetails`, used for authentication.
- Fields: `firstName`, `lastName`, `email`, `password`, `phoneNumber`, `role`

### `Patient`
- Fields: `nationalId`, `isHealthInsurancePaid`
- One-to-one with `User`
- One-to-one with `DoctorPatientAssignment`

### `Doctor`
- Fields: `doctorUid`, `specialization`
- One-to-one with `User`
- One-to-many with `DoctorPatientAssignment`

### `DoctorPatientAssignment`
- Fields: `registrationDate`, `deregistrationDate`
- One-to-one with `Patient`
- Many-to-one with `Doctor`

### `Appointment`
- Fields: `appointmentDate`, `appointmentHour`, `duration`, `status`
- Many-to-one with `Patient` and `Doctor`
- One-to-one with `SickLeave`
- One-to-many with `Diagnosis` and `Treatment`

### `Diagnosis`
- Fields: `name`
- Many-to-one with `Appointment`

### `Treatment`
- Fields: `medicine`, `dosage`, `duration`, `doctorNotes`
- Many-to-one with `Appointment`

### `SickLeave`
- Fields: `startDate`, `endDate`
- One-to-one with `Appointment`

---

## 🔐 Security & Authentication

- **JWT Authentication**: Stateless token-based auth.
- **Spring Security**: URL- and method-level access control based on roles.
- **Token Flow**:
  - Login → JWT token issued
  - Token must be included in Authorization headers for protected endpoints

---

## 🧪 Example Use Cases

### Doctor
- Logs in with credentials
- Views their assigned patients
- Schedules appointments and records diagnoses/treatments
- Issues sick leaves

### Admin
- Creates or updates users
- Accesses all medical records
- Generates summary reports

### Patient
- Logs in
- Views personal history: diagnoses, treatments, sick leaves, and appointments

---

## ⚙️ Technology Stack

| Technology             | Purpose                                 |
|------------------------|-----------------------------------------|
| Java 21                | Core programming language               |
| Spring Boot 3          | REST API framework                      |
| Spring Security        | Role-based authentication & authorization |
| JSON Web Token (jjwt)  | Token-based user sessions               |
| Spring Data JPA        | ORM and repository abstraction          |
| MapStruct              | DTO ↔ Entity mapping                    |
| MySQL                  | Relational database                     |
| Lombok                 | Reduces boilerplate                     |
| OpenAPI / Swagger      | API documentation and testing           |

---

## 🔄 Future Enhancements

- Frontend integration with React
- Email verification and password reset
- Audit logging
- Advanced query filtering
- Export to PDF (e.g., for patient history)

---

## 👩‍💻 Author

**Mirela Boncheva**  
Graduate Student, Software Engineering  
New Bulgarian University

---

## 📌 Conclusion

The Medical Record System provides a secure, well-structured, and scalable solution to manage digital patient data. It implements modern backend design principles such as layered architecture, RBAC, and DTO-based APIs. This project aligns with real-world healthcare data management practices and academic requirements.

---
