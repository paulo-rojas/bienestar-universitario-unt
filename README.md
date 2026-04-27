<div align="center">

# Bienestar Universitario UNT

### University Wellness Healthcare Management System  
**National University of Trujillo**

[![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-green?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=flat-square&logo=postgresql)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-3.x-red?style=flat-square&logo=apachemaven)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)](LICENSE)

[Features](#features) · [Tech Stack](#tech-stack) · [Getting Started](#getting-started) · [API Reference](#api-reference) · [Architecture](#architecture)

</div>

---

## Overview

**Bienestar Universitario UNT** is a RESTful backend system designed to manage the health services of the University Wellness Center at the National University of Trujillo (UNT). It streamlines the management of patients, medical consultations, triage, medical histories, staff, and geographic address data — providing a reliable foundation for clinical operations within a university environment.

---

## Features

| Module | Description |
|---|---|
| **Patient Management** | Register and manage students, faculty, and administrative staff as patients |
| **User & Auth** | Role-based user accounts (Admin, Doctor, Nurse, Receptionist) |
| **Medical Consultations** | Full consultation records: anamnesis, physical exams, diagnoses, and prescriptions |
| **Triage** | Pre-consultation vital signs: weight, height, BMI, blood pressure, SpO₂, temperature |
| **Medical History** | Persistent per-patient medical history tracking |
| **Medical Rest (Sick Leave)** | Manage medical rest certificates with status tracking |
| **Staff Management** | Medical staff directory with specialities and professional credentials |
| **Address Management** | Hierarchical Peruvian address system: Department → Province → District |

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.5 |
| ORM | Spring Data JPA / Hibernate |
| Security | Spring Security |
| Database | PostgreSQL |
| Build Tool | Apache Maven |
| Utilities | Project Lombok |
| Testing | JUnit 5 / Spring Boot Test |

---

## Getting Started

### Prerequisites

- **Java 21** or higher — [Download](https://adoptium.net/)
- **PostgreSQL 14+** — [Download](https://www.postgresql.org/download/)
- **Maven 3.8+** (or use the included Maven Wrapper)

### Database Setup

Create the database before starting the application:

```sql
CREATE DATABASE bd_bienestar;
```

> **Note:** Hibernate will auto-generate the schema on first startup (`create-drop` in development). The application also auto-loads seed data for Departments, Provinces, and Districts.

### Installation

1. **Clone the repository**

```bash
git clone https://github.com/your-username/bienestar-universitario-unt.git
cd bienestar-universitario-unt
```

2. **Configure database credentials**

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bd_bienestar
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. **Build the project**

```bash
# Using Maven Wrapper (recommended)
./mvnw clean package -DskipTests

# Or using system Maven
mvn clean package -DskipTests
```

4. **Run the application**

```bash
# Development (Maven Wrapper)
./mvnw spring-boot:run

# Production (JAR)
java -jar target/bienestar-0.0.1-SNAPSHOT.jar
```

The API will be available at: **`http://localhost:8080`**

---

## API Reference

> Base URL: `http://localhost:8080/api`

### Patients

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/patients` | Create a new patient |
| `GET` | `/patients/{id}` | Get patient by ID |
| `PUT` | `/patients/{id}` | Update patient data |
| `DELETE` | `/patients/{id}` | Delete a patient |
| `GET` | `/patients/{id}/address` | Get patient address |
| `POST` | `/patients/{id}/address` | Create patient address |
| `PUT` | `/patients/{id}/address` | Update patient address |

### Users

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/users` | Create a new user |
| `GET` | `/users/{id}` | Get user by ID |
| `PUT` | `/users/{id}` | Update user data |
| `DELETE` | `/users/{id}` | Delete a user |

### Geographic Address

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/address/departments` | List all departments |
| `GET` | `/address/provinces?departmentId={id}` | Get provinces by department |
| `GET` | `/address/districts?provinceId={id}` | Get districts by province |

---

## Project Structure

```
src/main/java/pe/edu/unitru/bienestar/
├── BienestarApplication.java        # Application entry point
│
├── address/                         # Geographic address module
│   ├── controller/
│   ├── domain/                      # Department, Province, District, Address entities
│   ├── dto/
│   ├── mapper/
│   ├── repository/
│   └── service/
│
├── patient/                         # Patient management module
│   ├── controller/
│   ├── domain/                      # PatientEntity, PatientType
│   ├── dto/
│   ├── mapper/
│   ├── repository/
│   └── service/
│
├── user/                            # User & authentication module
│   ├── controller/
│   ├── domain/                      # UserEntity, Role
│   ├── dto/
│   ├── mapper/
│   ├── repository/
│   └── service/
│
├── staff/                           # Medical staff module
│   └── domain/                      # StaffEntity, StaffRole, MedicalSpeciality
│
├── consultation/                    # Medical consultation module
│   ├── domain/                      # ConsultationEntity, MedicalRestEntity
│   └── triage/domain/               # TriageEntity
│
├── medicalhistory/                  # Medical history module
│   └── domain/
│
└── shared/                          # Shared base entities and services
    ├── domain/                      # PersonEntity, Gender
    ├── dto/
    ├── repository/
    └── service/
```

---

## Architecture

The project follows a **layered architecture** with clear separation of concerns:

```
┌──────────────────────────────────┐
│         REST Controllers         │  ← HTTP request handling
├──────────────────────────────────┤
│          Service Layer           │  ← Business logic
├──────────────────────────────────┤
│         Repository Layer         │  ← Data access (Spring Data JPA)
├──────────────────────────────────┤
│      Entity / Domain Layer       │  ← JPA entities
├──────────────────────────────────┤
│           PostgreSQL             │  ← Persistence
└──────────────────────────────────┘
```

### Key Design Decisions

- **PersonEntity** acts as a base entity shared by `Patient`, `User`, and `Staff`, avoiding data duplication.
- **DTO + Mapper pattern** decouples the API contract from the persistence model.
- **Modular package structure** by business domain (not by layer) enables independent scaling of each feature area.

### Entity Relationships

```
PersonEntity ─── UserEntity
             ├── PatientEntity ─── MedicalHistoryEntity
             │       └── AddressEntity ─── District ─── Province ─── Department
             └── StaffEntity

ConsultationEntity ─── TriageEntity
                   ├── StaffEntity
                   └── MedicalRestEntity
```

---

## Configuration Reference

Key properties in `application.properties`:

| Property | Default | Description |
|---|---|---|
| `spring.datasource.url` | `jdbc:postgresql://localhost:5432/bd_bienestar` | Database connection URL |
| `spring.jpa.hibernate.ddl-auto` | `create-drop` | Schema generation strategy |
| `spring.jpa.show-sql` | `true` | Log generated SQL |
| `server.port` | `8080` | Application port |

> **Warning:** `create-drop` drops and recreates the schema on every restart. Change to `update` or `validate` for production environments.

---

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature-name`
3. Commit your changes: `git commit -m "feat: add your feature"`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Open a Pull Request against `develop`

Please follow [Conventional Commits](https://www.conventionalcommits.org/) for commit messages.

---

## License

This project is licensed under the [MIT License](LICENSE).

---

<div align="center">

Developed for the **University Wellness Center — National University of Trujillo**  
Universidad Nacional de Trujillo · Trujillo, Perú

</div>
