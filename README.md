# Spring Boot 3 JWT Authentication API

A secure RESTful API built with **Spring Boot 3**, **Spring Security**, and **JWT (JSON Web Tokens)** for authentication and authorization.
It includes user registration, login, and role-based access control — all backed by **MariaDB**.

---

## 🚀 Features

* ✅ User Registration & Login
* 🔐 JWT Authentication (Access Token)
* 👥 Role-Based Authorization (Admin, User)
* 💾 MariaDB Database Integration
* ⚙️ Spring Boot 3 + JPA + Hibernate
* 🧩 Password Encryption (BCrypt)
* 🧪 Ready for Postman Testing

---

## 🛠️ Technologies

| Technology            | Purpose                        |
| --------------------- | ------------------------------ |
| Spring Boot 3         | Application framework          |
| Spring Security       | Authentication & authorization |
| JWT (io.jsonwebtoken) | Token-based security           |
| MariaDB               | Database                       |
| Hibernate / JPA       | ORM mapping                    |
| Maven                 | Build tool                     |

---

## ⚙️ Configuration

Edit the `application.properties` file:

```properties
server.port=8080
spring.datasource.url=jdbc:mariadb://localhost:3306/demo_db
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=mysupersecretkeyforjwtgeneration123456
jwt.expiration-ms=3600000
```

---

## 🧰 API Endpoints

### 🔹 Register User

**POST** `/auth/register`

**Body (JSON):**

```json
{
  "username": "saleh",
  "password": "123456"
}
```

**Response:**

```json
{
  "message": "User registered successfully!",
  "token": "<jwt-token>"
}
```

---

### 🔹 Login User

**POST** `/auth/login`

**Body (JSON):**

```json
{
  "username": "salehe",
  "password": "123456"
}
```

**Response:**

```json
{
  "token": "<jwt-token>",
  "username": "salehe",
  "roles": ["ROLE_USER"]
}
```

---

## 🧾 How to Run

```bash
# 1. Clone the project
git clone https://github.com/salehemussa/springboot-jwt-auth.git

# 2. Navigate into the folder
cd springboot-jwt-auth

# 3. Run the project
mvn spring-boot:run
```

---

## 🧑‍💻 Author

**Saleh Mussa**
💼 Software Developer | Microservives
gdh  | 💡 Java & Spring Boot Enthusiast

---

## 📜 License

This project is open-source and available under the [MIT License](LICENSE).
