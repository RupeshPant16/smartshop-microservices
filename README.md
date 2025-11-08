🛒 SmartShop – Cloud-Native Microservices E-Commerce Platform

SmartShop is a production-grade, cloud-native microservices application built using Spring Boot, Spring Cloud, Kafka, gRPC, and Docker/Kubernetes.
It demonstrates end-to-end architecture patterns including API Gateway, Service Discovery, Centralized Config, Asynchronous Communication, and Distributed Security with JWT.

🚀 Features

✅ Microservices-based architecture
✅ Centralized configuration management (Spring Cloud Config)
✅ Service discovery and dynamic routing (Eureka + Gateway)
✅ Synchronous communication via gRPC
✅ Asynchronous event communication via Kafka
✅ Secure authentication and authorization using Keycloak & JWT
✅ Polyglot persistence (PostgreSQL, MySQL, MongoDB)
✅ Observability with Zipkin, Prometheus, Grafana
✅ Docker + Kubernetes deployment
✅ CI/CD ready (GitHub Actions or Jenkins)

🧱 Architecture Overview
        ┌─────────────────────────────────────────────┐
        │                 API Gateway                 │
        │ (JWT Validation, Routing, Rate Limiting)    │
        └──────────────────────┬──────────────────────┘
                               │
               ┌───────────────┴────────────────┐
               ▼                                ▼
   ┌──────────────────┐           ┌──────────────────┐
   │ Discovery Server │           │  Config Server   │
   │   (Eureka)       │           │ (Git-based)      │
   └────────┬─────────┘           └────────┬─────────┘
            │                              │
     ┌──────┴───────────┐          ┌───────┴───────────┐
     │   User Service   │          │ Product Service   │
     │ (PostgreSQL)     │          │ (MongoDB)         │
     └────────┬─────────┘          └────────┬──────────┘
              │ gRPC                          │
              ▼                               ▼
     ┌──────────────────┐         ┌──────────────────┐
     │  Order Service   │         │ Inventory Service│
     │   (MySQL)        │         │   (PostgreSQL)   │
     └────────┬─────────┘         └────────┬──────────┘
              │  gRPC                       │  Kafka
              ▼                              ▼
       ┌──────────────────┐         ┌──────────────────┐
       │ Payment Service  │         │ Notification Svc │
       │ (MySQL)          │         │ (MongoDB + Kafka)│
       └────────┬─────────┘         └────────┬──────────┘
                │ Kafka                      │
                ▼                            ▼
              ┌──────────────────────────────────────┐
              │        Analytics / Monitoring         │
              │ (Prometheus, Grafana, Zipkin, ELK)    │
              └──────────────────────────────────────┘

🧩 Microservices Overview
Service	Description	Tech	Database
Config Server	Centralized config from Git	Spring Cloud Config	—
Discovery Server	Service registry	Netflix Eureka	—
API Gateway	Routes traffic, validates JWT	Spring Cloud Gateway	—
User Service	Handles user registration, login, JWT issuance	Spring Boot, Spring Security	PostgreSQL
Product Service	Manages product catalog	Spring Boot, Spring Data MongoDB	MongoDB
Order Service	Handles order lifecycle and orchestration	Spring Boot, gRPC	MySQL
Inventory Service	Tracks product stock	Spring Boot, gRPC	PostgreSQL
Payment Service	Handles payments, refunds	Spring Boot, gRPC	MySQL
Notification Service	Sends emails/SMS on events	Spring Boot WebFlux, Kafka	MongoDB
Analytics Service (Optional)	Generates reports, metrics	Spring Boot, Kafka Consumer	PostgreSQL
⚙️ Tech Stack
Category	Tools
Language	Java 17 / 21
Frameworks	Spring Boot 3.x, Spring Cloud 2023.x
Communication	REST (external), gRPC (internal), Kafka (async)
Databases	PostgreSQL, MySQL, MongoDB
Security	JWT, OAuth2 (Keycloak)
Configuration	Spring Cloud Config (Git backend)
Service Discovery	Netflix Eureka
Build Tool	Maven
Containerization	Docker, Docker Compose
Orchestration	Kubernetes, Helm
Monitoring	Zipkin, Prometheus, Grafana
Testing	JUnit 5, Mockito, Testcontainers
🏗️ Project Structure
smartshop-microservices/
├── pom.xml                # Parent POM (dependency management)
├── config-server/
├── discovery-server/
├── gateway/
├── user-service/
├── product-service/
├── order-service/
├── inventory-service/
├── payment-service/
├── notification-service/
└── docker-compose.yml

⚡ How to Run Locally (Step-by-Step)
🧰 Prerequisites

Java 17+

Maven 3.9+

Docker Desktop

Git

Postman (optional for testing)

🪜 Step 1: Clone the Repos
git clone https://github.com/<your-username>/smartshop-microservices.git
git clone https://github.com/<your-username>/smartshop-config.git

🪜 Step 2: Start Databases & Kafka via Docker
docker-compose up -d


This runs:

PostgreSQL (for user/inventory)

MySQL (for order/payment)

MongoDB (for product/notifications)

Kafka + Zookeeper

🪜 Step 3: Start the Core Infrastructure
cd config-server && mvn spring-boot:run
cd discovery-server && mvn spring-boot:run
cd gateway && mvn spring-boot:run

🪜 Step 4: Start Business Services
cd user-service && mvn spring-boot:run
cd product-service && mvn spring-boot:run
cd order-service && mvn spring-boot:run
cd inventory-service && mvn spring-boot:run
cd payment-service && mvn spring-boot:run
cd notification-service && mvn spring-boot:run


✅ Open http://localhost:8761 → All services should be visible in Eureka.
✅ Test API: POST http://localhost:8080/api/users (via Gateway)

🪜 Step 5: Observability (Optional)

Open Zipkin → http://localhost:9411

Open Prometheus → http://localhost:9090

Open Grafana → http://localhost:3000

🔄 Communication Patterns
Type	Used For	Protocol	Example
REST	Client → Gateway	HTTP/JSON	/api/products
gRPC	Service → Service (sync)	HTTP/2	Order → Inventory
Kafka	Event-driven async	Pub/Sub	order.created, payment.completed
🔒 Security Overview

JWT authentication and authorization via Keycloak

Gateway verifies tokens before routing

Services also validate tokens for RBAC

Internal gRPC calls use JWT metadata or mTLS

🧠 Learning Goals / Concepts Covered

Spring Cloud microservice ecosystem

gRPC implementation with Spring Boot

Kafka event-driven design

Polyglot persistence design

Centralized configuration and service discovery

JWT/OAuth2 security

CI/CD and Dockerized deployment

🧪 Example API Calls
POST /auth/signup
POST /auth/login
GET /api/products
POST /api/orders
GET /api/orders/{id}

📊 Future Enhancements

Integrate Elastic Stack (ELK) for logs

Add circuit breaker (Resilience4j)

Implement API rate limiting

Introduce Blue-Green deployment in Kubernetes

📚 References

Spring Cloud Docs

Spring Boot Reference Guide

Apache Kafka

gRPC for Java

🧑‍💻 Author

Rupesh Pant
📧 [your-email@example.com
]
💼 [LinkedIn Profile]
💻 [GitHub Profile]
