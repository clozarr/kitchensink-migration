# Kitchensink Application

The Kitchensink application, built with **Spring Boot 3.3.4** and **Java 21**. It allows you to register, view, and store member information in an in-memory H2 database.

## Features

- **Web interface** to register members with the following fields:
    - Name
    - Email
    - Phone Number
- A dynamic table that displays registered members and automatically updates with new entries.
- Persistence using **H2** in-memory database.
- Field validations:
    - Name must not contain numbers, and should be between 1 and 24 characters in length.
    - Email must have a valid format.
    - Phone number must contain only integers and be between 10 and 12 digits.
- Members are represented by the `MemberEntity` class with the following fields:
    - `id` (automatically generated)
    - `name`
    - `email` (must be unique)
    - `phoneNumber`

## Requirements

To run the application, you need:

- **Java 21**
- **Gradle 7.4** or higher (if using `./gradlew`, local Gradle installation is not required)

## Installation

1. Clone the repository or download the project files:

```bash
git clone https://github.com/clozarr/kitchensink-migration.git
cd kitchensink-migration
 ```
2.	Run the following command to download dependencies and build the project:

```bash
./gradlew build
 ```
   
## Running the application

```bash
./gradlew bootRun
 ```
3. The application will start on http://localhost:8080.
4. Upon startup, the import.sql script will be executed, inserting an example member into the database:


## H2 Database

The application uses an in-memory H2 database, which you can access via a browser. To access the H2 console:

Navigate to: http://localhost:8080/h2-console
Configure the connection with the following parameters:
* **Driver Class**: org.h2.Driver
* **JDBC URL**: jdbc:h2:mem:test
* **Username**: sa
* **Password**: (leave it blank)


## Dependencies

The application includes the following major dependencies:

* Spring Boot 3.3.4 
* Database
* Thymeleaf (for the web interface)
* ModelMapper (for object mapping)


## Author

* **Carlos Lozano**
* Contact: [carlos.lozano@globallogic.com](carlos.lozano@globallogic.com)