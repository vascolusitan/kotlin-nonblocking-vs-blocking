# Non-Blocking Programming vs Blocking Programming

This is a modular project designed to compare non-blocking (reactive) programming with blocking (imperative) programming, 
using [Kotlin](https://kotlinlang.org/) and popular frameworks like [Quarkus](https://quarkus.io/), [Spring 
WebFlux](https://docs.spring.io/spring-framework/reference/web/webflux.html), and [Spring 
Boot](https://spring.io/projects/spring-boot). The project utilizes [PostgreSQL](https://www.postgresql.org/) as the 
database system, providing a robust and reliable storage solution for all modules. Each module demonstrates different 
approaches to API development, allowing for performance and behavior comparisons between reactive and imperative paradigms.

## Project Structure

The project consists of the following modules:

- **Quarkus Module**: A Quarkus-based application demonstrating non-blocking (reactive) programming patterns.
- **Spring WebFlux Module**: A Spring WebFlux-based application for reactive programming, showcasing asynchronous data 
processing and event-driven design.
- **Spring Boot Module**: A traditional Spring Boot application using blocking (imperative) programming to handle 
requests synchronously.
- **Cross-Cutting Module**: Contains tools for:
    - **Load Testing**: Includes [K6](https://k6.io/) scripts to simulate load testing, comparing the performance and 
    scalability of reactive vs. imperative modules.
    - **Database Migration**: Manages database migrations using [Flyway](https://flywaydb.org/), ensuring consistent 
    schema evolution across all modules.
    - **Application Startup**: Centralizes setup scripts and configurations shared across modules.

## Objective

The main objective is to analyze the performance, resource utilization, and development patterns of reactive versus 
imperative programming paradigms. The modules serve as practical, side-by-side examples of each approach, allowing for 
direct comparisons through load tests and performance metrics.

## Requirements

- **Java 21**: The project requires Java 11 or higher to support Quarkus and Spring modules.
- **Maven 3.6**: For dependency management and building the modular structure.
- **Docker**: For running the database in a containerized environment (useful for consistency across environments).
- **K6**: For load testing. Install K6 by following the [official installation 
guide](https://grafana.com/docs/k6/latest/set-up/install-k6/)

## Getting Started

Follow these steps to set up and run the project locally, including starting up a PostgreSQL database, applying migrations, building the project, running the applications, and testing performance.

### 1. Start PostgreSQL Database with Docker

To start a PostgreSQL database using Docker, navigate to the project root directory where the `docker-compose.yml` file 
is located, and run:
```
  docker-compose up -d
```
This command will start the PostgreSQL database. Once the database is running, you can verify its status with:
```
  docker-compose ps
```

### 2. Run Database Migrations Manually with Flyway

After the database is up and running, apply the database migrations. From the project root directory, run:
```
  mvn -pl cross-cutting flyway:migrate
```
This command uses Flyway to migrate the database schema based on scripts located in the cross-cutting module.

### 3. Build the Project

To compile and package all modules, execute:
```
  mvn clean install
```
This command will clean any previous builds and then build all modules, downloading necessary dependencies and packaging 
each module.

### 4. Run Each Module Individually

Each module can be run independently on a different port, allowing you to compare the reactive and imperative 
implementations.
#### Run Quarkus (Reactive)
```
  mvn -pl kotlin-quarkus quarkus:dev
```
#### Run Quarkus (Reactive)
```
  mvn -pl kotlin-spring-webflux spring-boot:run
```
#### Run Quarkus (Reactive)
```
  mvn -pl kotlin-spring-web spring-boot:run
```

### 5. Test the API with cURL

Once one of the applications is running, you can test it by sending a GET request to fetch all persons. Replace 8080 
with the correct port if different.
```
  curl -X GET http://localhost:8080/api/v1/persons
```

### 6. Run Load Tests with K6

To run load tests, use K6 with a script provided in the cross-cutting module. For example, to test the GET /api/v1/persons 
endpoint, run:
```
  k6 run ./cross-cutting/load-tests/src/test/k6/get-all-persons.js
```
This command will execute a load test, and results will display in the terminal, showing metrics like latency, throughput, 
and error rates.

### 7. Additional Useful Commands

#### Stop Docker Containers and Remove Volumes
To stop the PostgreSQL container and any other services defined in docker-compose.yml, and also remove the database volumes 
(erasing all data):
```
  docker-compose down -v
```

#### Roll Back Database Migrations
If you need to roll back the last applied migration:
```
  mvn -pl cross-cutting flyway:undo
```

## Comparative Results

### Hello World

| Metric                      | **Quarkus**      | **Spring Webflux** | **Spring Boot** |
|-----------------------------|------------------|---------------------|------------------|
| **Total Requests Processed**| 100% (78.31M)    | 100% (56.18M)       | 99.99% (41.81M) |
| **Data Received**           | 100% (7.8 GB)    | 73% (5.7 GB)        | 72% (5.6 GB)    |
| **Data Sent**               | 100% (7.8 GB)    | 72% (5.6 GB)        | 54% (4.2 GB)    |
| **Request Duration (avg)**  | 100% (516 µs)    | 143% (735 µs)       | 128% (661 µs)   |
| **Throughput (req/s)**      | 100% (93.2K/s)   | 72% (66.9K/s)       | 52% (48.6K/s)   |

### Summary of Results

- **Throughput**: Quarkus achieved the highest throughput at 100% (93.2K requests/s), with Spring Webflux at 72% and 
Spring Boot at 52% of Quarkus's performance.
- **Data Transfer**: Both data received and sent in Quarkus were approximately 100% of its load, with Spring Webflux 
transferring about 72-73%, and Spring Boot 54% for sent data.
- **Average Request Duration**: Quarkus was the fastest, with an average request duration of 516 µs. Spring Boot averaged 
661 µs (28% slower), and Spring Webflux was 43% slower with an average of 735 µs.
- **Reliability**: All frameworks maintained a 0% failure rate, though Spring Boot had a very slight deviation (20 failed 
requests out of 41.81M).
