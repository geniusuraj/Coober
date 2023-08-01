# Coober - Online Cab Booking Application

## Description

This project is an online cab booking application built with Spring Boot. It provides a platform for customers to book cabs, track their ride, and provide feedback which can be reviewed by our dedicated support team. It uses JPA for data persistence, MySQL for the database, and includes validation and web functionalities. The project also employs Lombok to minimize boilerplate code.

## Team Members

- Member 1: [Sarthak (Team Lead)](https://github.com/sarthakdevhub)
- Member 2: [Suraj Kumar Sharma](https://github.com/geniusuraj)
- Member 3: [Sudhist Kumar](https://github.com/ersudhist)
- Member 4: [Harsh Rai](https://github.com/RHarsh0)
- Member 5: [Akhil Suryan](https://github.com/akhil368)

## Tech Stack

- Spring Boot
- Spring Data JPA
- Spring Boot Validation
- Spring Boot Web
- Spring Boot DevTools
- MySQL Database
- Lombok

## Prerequisites

- Java 8 or higher
- Maven
- MySQL Server

## Installation

```bash
# To run this project locally:

# Clone the repository and navigate to the directory
git clone https://github.com/sarthakdevhub/vestal-trouble-683.git
cd online-cab-booking

# Configure your MySQL credentials in application.properties
# located in src/main/resources directory
# Replace with your actual MySQL credentials
spring.datasource.url=jdbc:mysql://localhost:3306/your-database-name
spring.datasource.username=your-username
spring.datasource.password=your-password

# Use Maven to build the project
mvn clean install

# After successful build, navigate to target directory and run the jar file
java -jar target/online-cab-booking-0.0.1-SNAPSHOT.jar

# Your application should be up and running at http://localhost:8080.
## Usage
# After the application is running, you can interact with it using a tool like Postman to make HTTP requests.

# To get all cab bookings
GET http://localhost:8080/bookings

# To book a new cab
POST http://localhost:8080/booking
Content-Type: application/json
Request body:
{
  "name": "Your Name",
  "email": "Your Email",
  "pickupLocation": "Your Pickup Location",
  "dropLocation": "Your Drop Location"
}

# To update a cab booking
PUT http://localhost:8080/booking/{id}
Content-Type: application/json
Request body:
{
  "name": "New Name",
  "email": "New Email",
  "pickupLocation": "Updated Pickup Location",
  "dropLocation": "Updated Drop Location"
}

# Replace {id} with the ID of the cab booking you want to update
```
## Entity-Relationship Diagram
### Here is the ER Diagram for our database:
![Entity-Relationship Diagram](https://github.com/sarthakdevhub/vestal-trouble-683/blob/main/Back-End/E-R.png)

