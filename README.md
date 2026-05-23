# 📋 Task Manager API

[![Java](https://img.shields.io/badge/Java-17+-blue?logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=spring)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?logo=apache-maven)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A production-ready RESTful API for managing tasks and categories built using **Spring Boot 3**, **Java 17**, and **Spring Data JPA**.

The project follows clean architecture principles, DTO pattern, layered architecture, global exception handling, validation, pagination, and modern Java development practices.

---

# ✨ Features

- ✅ CRUD operations for Tasks & Categories
- 🔍 Search tasks by title
- 📄 Pagination & sorting support
- 🎯 Filter tasks by completion status
- 🛡️ Request validation using Jakarta Validation
- 🌐 Global exception handling
- 🔄 DTO pattern with mapper layer
- 💾 H2 in-memory database
- 📊 Spring Boot Actuator monitoring
- 🧹 Lombok support for reducing boilerplate
- 🏗️ Layered architecture (Controller → Service → Repository)

---

# 🛠️ Tech Stack

| Technology | Usage |
|------------|------|
| Java 17 | Programming Language |
| Spring Boot 3 | Backend Framework |
| Spring Data JPA | ORM & Database Access |
| H2 Database | In-memory Database |
| Maven | Build Tool |
| Lombok | Boilerplate Reduction |
| Jakarta Validation | Request Validation |
| Spring Boot Actuator | Monitoring & Health Checks |

---

# 📁 Project Structure

```bash
src/main/java/com/example/taskmanager
│
├── controller        # REST Controllers
├── service           # Business Logic
├── repository        # JPA Repositories
├── entity            # Database Entities
├── dto               # Request/Response DTOs
├── mapper            # DTO Mappers
├── exception         # Global Exception Handling
└── config            # Application Configuration
```

---

# 🚀 Getting Started

## Prerequisites

Make sure you have installed:

- Java 17+
- Maven 3.8+
- Git

---

# ⚙️ Installation

## 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/taskmanager-api.git
cd taskmanager-api
```

---

## 2️⃣ Run Application

Using Maven Wrapper:

### Linux / macOS

```bash
./mvnw spring-boot:run
```

### Windows

```bash
mvnw.cmd spring-boot:run
```

Or using installed Maven:

```bash
mvn clean spring-boot:run
```

---

# 🌐 API Base URL

```bash
http://localhost:8080/api/v1
```

---

# 💾 H2 Database Console

Enable console in `application.properties`:

```properties
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

Open:

```bash
http://localhost:8080/h2-console
```

### Default Credentials

| Property | Value |
|----------|-------|
| JDBC URL | jdbc:h2:mem:taskdb |
| Username | sa |
| Password | password |



---

# 📌 Sample API Endpoints

## Task Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/tasks` | Get all tasks |
| GET | `/tasks/{id}` | Get task by ID |
| POST | `/tasks` | Create new task |
| PUT | `/tasks/{id}` | Update task |
| DELETE | `/tasks/{id}` | Delete task |
| GET | `/tasks/search` | Search tasks |

---

# 📄 Example Request

## Create Task

### POST `/api/v1/tasks`

```json
{
  "title": "Learn Spring Boot",
  "description": "Complete REST API project",
  "completed": false
}
```

---

# ✅ Validation Example

```java
@NotBlank(message = "Title is required")
@Size(max = 100)
private String title;
```

---

# 🌐 Global Exception Response

Example:

```json
{
  "timestamp": "2026-05-22T10:00:00",
  "status": 404,
  "message": "Task not found"
}
```

---

# 🧪 Running Tests

```bash
mvn test
```

---

# 🏗️ Future Improvements

- JWT Authentication & Authorization
- PostgreSQL/MySQL integration
- Docker support
- Swagger/OpenAPI documentation
- Unit & Integration Testing
- CI/CD Pipeline
- Redis caching

---

# 🤝 Contributing

Contributions are welcome.

1. Fork the repository
2. Create feature branch
3. Commit changes
4. Push to branch
5. Open Pull Request

---

# 📜 License

This project is licensed under the MIT License.

---
