<div align="center">

# Bienestar Universitario UNT

### Sistema de Gestión de Salud — Bienestar Universitario  
**Universidad Nacional de Trujillo**

[![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-green?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=flat-square&logo=postgresql)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-3.x-red?style=flat-square&logo=apachemaven)](https://maven.apache.org/)
[![Licencia](https://img.shields.io/badge/Licencia-MIT-yellow?style=flat-square)](LICENSE)

[Funcionalidades](#funcionalidades) · [Tecnologías](#tecnologías) · [Inicio rápido](#inicio-rápido) · [API](#referencia-de-la-api) · [Arquitectura](#arquitectura)

</div>

---

## Descripción general

**Bienestar Universitario UNT** es un sistema backend RESTful diseñado para gestionar los servicios de salud del Centro de Bienestar Universitario de la Universidad Nacional de Trujillo (UNT). Centraliza la gestión de pacientes, consultas médicas, triaje, historias clínicas, personal médico y datos de dirección geográfica, proporcionando una base sólida para las operaciones clínicas en el entorno universitario.

---

## Funcionalidades

| Módulo | Descripción |
|---|---|
| **Gestión de Pacientes** | Registro y administración de estudiantes, docentes y personal administrativo como pacientes |
| **Usuarios y Autenticación** | Cuentas de usuario con control de acceso basado en roles (Admin, Médico, Enfermero, Recepcionista) |
| **Consultas Médicas** | Registros completos de consulta: anamnesis, examen físico, diagnóstico y tratamiento |
| **Triaje** | Signos vitales previos a la consulta: peso, talla, IMC, presión arterial, SpO₂, temperatura |
| **Historia Clínica** | Seguimiento persistente de la historia médica de cada paciente |
| **Descanso Médico** | Gestión de certificados de descanso médico con seguimiento de estado |
| **Gestión de Personal** | Directorio del personal médico con especialidades y credenciales profesionales |
| **Gestión de Direcciones** | Sistema de direcciones jerárquico: Departamento → Provincia → Distrito |

---

## Tecnologías

| Capa | Tecnología |
|---|---|
| Lenguaje | Java 21 |
| Framework | Spring Boot 3.5 |
| ORM | Spring Data JPA / Hibernate |
| Seguridad | Spring Security |
| Base de datos | PostgreSQL |
| Gestión de dependencias | Apache Maven |
| Utilidades | Project Lombok |
| Pruebas | JUnit 5 / Spring Boot Test |

---

## Inicio rápido

### Requisitos previos

- **Java 21** o superior — [Descargar](https://adoptium.net/)
- **PostgreSQL 14+** — [Descargar](https://www.postgresql.org/download/)
- **Maven 3.8+** (o usar el Maven Wrapper incluido)

### Configuración de la base de datos

Crear la base de datos antes de iniciar la aplicación:

```sql
CREATE DATABASE bd_bienestar;
```

> **Nota:** Hibernate generará el esquema automáticamente en el primer inicio (`create-drop` en desarrollo). La aplicación también carga automáticamente los datos iniciales de Departamentos, Provincias y Distritos del Perú.

### Instalación

1. **Clonar el repositorio**

```bash
git clone https://github.com/tu-usuario/bienestar-universitario-unt.git
cd bienestar-universitario-unt
```

2. **Configurar las credenciales de la base de datos**

Editar `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bd_bienestar
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

3. **Compilar el proyecto**

```bash
# Usando Maven Wrapper (recomendado)
./mvnw clean package -DskipTests

# O usando Maven instalado en el sistema
mvn clean package -DskipTests
```

4. **Ejecutar la aplicación**

```bash
# Modo desarrollo (Maven Wrapper)
./mvnw spring-boot:run

# Modo producción (JAR)
java -jar target/bienestar-0.0.1-SNAPSHOT.jar
```

La API estará disponible en: **`http://localhost:8080`**

---

## Referencia de la API

> URL base: `http://localhost:8080/api`

### Pacientes

| Método | Endpoint | Descripción |
|---|---|---|
| `POST` | `/patients` | Crear un nuevo paciente |
| `GET` | `/patients/{id}` | Obtener paciente por ID |
| `PUT` | `/patients/{id}` | Actualizar datos del paciente |
| `DELETE` | `/patients/{id}` | Eliminar un paciente |
| `GET` | `/patients/{id}/address` | Obtener dirección del paciente |
| `POST` | `/patients/{id}/address` | Crear dirección del paciente |
| `PUT` | `/patients/{id}/address` | Actualizar dirección del paciente |

### Usuarios

| Método | Endpoint | Descripción |
|---|---|---|
| `POST` | `/users` | Crear un nuevo usuario |
| `GET` | `/users/{id}` | Obtener usuario por ID |
| `PUT` | `/users/{id}` | Actualizar datos del usuario |
| `DELETE` | `/users/{id}` | Eliminar un usuario |

### Direcciones Geográficas

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/address/departments` | Listar todos los departamentos |
| `GET` | `/address/provinces?departmentId={id}` | Obtener provincias por departamento |
| `GET` | `/address/districts?provinceId={id}` | Obtener distritos por provincia |

---

## Estructura del proyecto

```
src/main/java/pe/edu/unitru/bienestar/
├── BienestarApplication.java        # Punto de entrada de la aplicación
│
├── address/                         # Módulo de gestión de direcciones
│   ├── controller/
│   ├── domain/                      # Entidades: Departamento, Provincia, Distrito, Dirección
│   ├── dto/
│   ├── mapper/
│   ├── repository/
│   └── service/
│
├── patient/                         # Módulo de gestión de pacientes
│   ├── controller/
│   ├── domain/                      # PatientEntity, PatientType
│   ├── dto/
│   ├── mapper/
│   ├── repository/
│   └── service/
│
├── user/                            # Módulo de usuarios y autenticación
│   ├── controller/
│   ├── domain/                      # UserEntity, Role
│   ├── dto/
│   ├── mapper/
│   ├── repository/
│   └── service/
│
├── staff/                           # Módulo de personal médico
│   └── domain/                      # StaffEntity, StaffRole, MedicalSpeciality
│
├── consultation/                    # Módulo de consultas médicas
│   ├── domain/                      # ConsultationEntity, MedicalRestEntity
│   └── triage/domain/               # TriageEntity
│
├── medicalhistory/                  # Módulo de historia clínica
│   └── domain/
│
└── shared/                          # Entidades base y servicios compartidos
    ├── domain/                      # PersonEntity, Gender
    ├── dto/
    ├── repository/
    └── service/
```

---

## Arquitectura

El proyecto sigue una **arquitectura en capas** con clara separación de responsabilidades:

```
┌──────────────────────────────────┐
│         Controladores REST       │  ← Manejo de peticiones HTTP
├──────────────────────────────────┤
│          Capa de Servicio        │  ← Lógica de negocio
├──────────────────────────────────┤
│         Capa de Repositorio      │  ← Acceso a datos (Spring Data JPA)
├──────────────────────────────────┤
│      Capa de Entidades/Dominio   │  ← Entidades JPA
├──────────────────────────────────┤
│           PostgreSQL             │  ← Persistencia
└──────────────────────────────────┘
```

### Decisiones de diseño clave

- **PersonEntity** es la entidad base compartida por `Paciente`, `Usuario` y `Personal`, evitando la duplicación de datos personales.
- El **patrón DTO + Mapper** desacopla el contrato de la API del modelo de persistencia.
- La **estructura de paquetes por dominio de negocio** (no por capa técnica) facilita el mantenimiento independiente de cada módulo.

### Relaciones entre entidades

```
PersonEntity ─── UserEntity
             ├── PatientEntity ─── MedicalHistoryEntity
             │       └── AddressEntity ─── Distrito ─── Provincia ─── Departamento
             └── StaffEntity

ConsultationEntity ─── TriageEntity
                   ├── StaffEntity
                   └── MedicalRestEntity
```

---

## Referencia de configuración

Propiedades clave en `application.properties`:

| Propiedad | Valor por defecto | Descripción |
|---|---|---|
| `spring.datasource.url` | `jdbc:postgresql://localhost:5432/bd_bienestar` | URL de conexión a la base de datos |
| `spring.jpa.hibernate.ddl-auto` | `create-drop` | Estrategia de generación del esquema |
| `spring.jpa.show-sql` | `true` | Registro del SQL generado en consola |
| `server.port` | `8080` | Puerto de la aplicación |

> **Advertencia:** La estrategia `create-drop` elimina y recrea el esquema en cada reinicio. Para entornos de producción, cambiar a `update` o `validate`.

---

## Contribuciones

1. Realiza un fork del repositorio
2. Crea una rama para tu funcionalidad: `git checkout -b feature/nombre-de-tu-funcionalidad`
3. Registra tus cambios: `git commit -m "feat: agregar nueva funcionalidad"`
4. Sube la rama: `git push origin feature/nombre-de-tu-funcionalidad`
5. Abre un Pull Request apuntando a la rama `develop`

Por favor, sigue la convención de [Conventional Commits](https://www.conventionalcommits.org/es/v1.0.0/) para los mensajes de commit.

---

## Licencia

Este proyecto está bajo la [Licencia MIT](LICENSE).

---

<div align="center">

Desarrollado para el **Centro de Bienestar Universitario — Universidad Nacional de Trujillo**  
Universidad Nacional de Trujillo · Trujillo, Perú

</div>
