# Vehicle Rental Management System

**Name:** MBISHIBISHI Flavien
**Student ID:** 27857

## How to Run the API

1. **Database Setup:**
   - Ensure PostgreSQL is installed and running.
   - Create a database named `vehicle_rental_db`.

2. **Configuration:**
   - Open `src/main/resources/application.properties`.
   - Ensure the `spring.datasource.username` and `spring.datasource.password` match your local PostgreSQL credentials.

3. **Run the Application:**
   - Open a terminal in the project root directory.
   - Run the following command:
     ```
     ./mvnw spring-boot:run
     ```
   - The API will start on port `8084` by default.
