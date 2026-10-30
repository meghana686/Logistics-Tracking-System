# 🚚 Logistics Tracking System

### Spring Boot REST API Project with PostgreSQL

A backend application developed using **Java, Spring Boot, Spring Data JPA, Hibernate, and PostgreSQL** to manage customers, shipments, delivery agents, warehouses, packages, payments, and shipment tracking history.

---

## 🛠️ Technologies Used

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white" alt="Hibernate"/>
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL"/>
  <img src="https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven"/>
  <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"/>
</p>

## 📌 Project Overview

The Logistics Tracking System provides REST APIs for managing logistics operations. It uses Spring Boot for backend development and PostgreSQL for data persistence.

### 🎯 Main Objectives

- Manage customer information.
- Create and manage shipments.
- Associate shipments with delivery agents and warehouses.
- Manage packages and payments.
- Maintain shipment tracking history.
- Perform CRUD operations and retrieve shipment information efficiently.

---

## 📦 Project Modules

### 1. 👤 Customer Management
- Manage customer records.
- Validate email and contact information.
- Check email and contact uniqueness.
- Validate 10-digit contact numbers.

### 2. 🚚 Shipment Management
- Create and retrieve shipment records.
- Retrieve shipments by customer ID.
- Retrieve shipments by delivery agent ID.
- Search shipments by source and destination.
- Retrieve shipments by delivery date.
- Update shipment destination.
- Assign delivery agents to shipments.
- Assign warehouses to shipments.
- Delete shipment records.
- Implement pagination and sorting.

### 3. 🧑‍✈️ Delivery Agent Management
- Manage delivery agent information.
- Associate delivery agents with shipments.

### 4. 🏢 Warehouse Management
- Manage warehouse information.
- Associate shipments with warehouses.

### 5. 📦 Package Management
- Manage package information.
- Connect package records with the logistics system.

### 6. 💳 Payment Management
- Manage payment records.
- Update payment status.

### 7. 📍 Tracking History
- Manage tracking history associated with shipments.
- Maintain shipment tracking information.

---

## 🔗 JPA Entity Relationships

The project uses JPA entity mappings to associate related entities, including:

- One-to-One
- One-to-Many
- Many-to-One

These relationships connect customers, shipments, delivery agents, warehouses, packages, payments, and tracking history.

---

## ⚙️ Technologies and Tools

| Technology | Purpose |
|---|---|
| Java | Backend programming |
| Spring Boot | Application development |
| Spring Data JPA | Database operations |
| Hibernate | Object-relational mapping |
| PostgreSQL | Relational database |
| Maven | Dependency management |
| Postman | REST API testing |
| Eclipse IDE | Development environment |
| Git and GitHub | Version control and project hosting |

---

## 🛡️ Exception Handling

The project includes custom exception handling components:

- `IdNotFoundException`
- `NoRecordAvailableException`
- `GlobalExceptionHandler`

A `ResponseStructure` DTO is used to organize API response data, messages, and status codes.

---

## ▶️ How to Run the Project

1. Clone the repository.
2. Import the project into Eclipse as an existing Maven project.
3. Create a PostgreSQL database.
4. Configure your database URL, username, and password in `application.properties`.
5. Ensure PostgreSQL is running.
6. Run the Spring Boot application.
7. Use Postman to test the REST APIs.

**Security:** Keep database credentials and GitHub personal access tokens private. Never commit secrets to the repository.

---

## 📚 Key Learning Outcomes

- REST API development using Spring Boot.
- CRUD operations using Spring Data JPA.
- Entity relationships and Hibernate mappings.
- PostgreSQL database integration.
- Service-layer business logic and validation.
- Custom exceptions and global exception handling.
- Filtering, pagination, and sorting.
- Git and GitHub version control.

---

## 👩‍💻 Author

**Meghana**

---

⭐ *Developed as a hands-on project to practice Java backend development with Spring Boot and PostgreSQL.*
