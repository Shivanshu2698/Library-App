# Library Management System

This project is a simple RESTful API for managing books and authors in a library, built using Spring Boot. The purpose of this project is to evaluate algorithm and problem-solving skills, the ability to write clean and maintainable code, and proficiency in Java.

## Features

- Add a new book
- Add a new author
- Retrieve a list of all books
- Retrieve a list of all authors
- Retrieve a list of all books by a specific author
- Update book details
- Delete a book
- Search books by title
- Search author by name

## Technologies Used

- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Swagger for API documentation

## Getting Started

### Prerequisites

- Java 17 or later
- Maven

### Setup Instructions

1. **Clone the repository:**
    git clone https://github.com/your-username/library-management-system.git
    cd library-management-system


2. **Build the project:**
    mvn clean install

3. **Run the application:**
    mvn spring-boot:run


4. **Access the application:**
    The application will be running at `http://localhost:8070.

### Database Configuration
The project uses an in-memory H2 database for simplicity. The database schema is initialized on startup.
To check the database, after deploting youe app you can go through: http://localhost:8070/h2-console 

### API Endpoints

#### Book Endpoints

- **Add a new book**
    //POST url - http://localhost:8070/books
	  // Json body-
	  { "title": "SpringBoot Java",
    "isbn": "978-0134685991",
    "author": { "id": 3 }
	  }

- **Retrieve a list of all books**
    GET url - http://localhost:8070/books

- **Retrieve a list of all books by a specific author**
    GET  url - http://localhost:8070/books/author/{id}

- **Update book details**
    PUT url - http://localhost:8070/books
	  Json body-
	  { "title": "SpringBoot Java",
    "isbn": "978-0134685991",
    "author": { "id": 3 }
	  }

- **Delete a book**
    DELETE url - http://localhost:8070/books/{id}

- **Search a book by title**
URL - http://localhost:8070/books/search?title={title}


#### Author Endpoints

- **Add a new author**
    POST url - http://localhost:8070/authors
    Json body-
    { "name": "Author Name",
    "biography": "Author biography" }

- **Retrieve a list of all authors**
    GET  url - http://localhost:8070/authors

  - **Search author by name**
    GET URL: http://localhost:8070/authors/search?name={name}

### Assumptions

- The `authorId` provided when adding a new book must reference an existing author.
- The `bookId` and `authorId` used in the endpoints must be valid and existing.

### Running Tests

The project includes unit tests for the service layer and integration tests for the REST endpoints. To run the tests, use the following command:  mvn test

### API Documentation
API documentation is provided using Swagger. Once the application is running, you can access the Swagger UI at:
  GET url- http://localhost:8080/swagger-ui.html


###  Project Structure

src/main/java: Contains the source code for the application.
com.lms.app: Main package for the application.
controller: Contains the REST controllers.
entity: Contains the JPA entities.
repository: Contains the JPA repositories.
service: Contains the service layer.
config: Contains Api Documentation configuration file.

src/main/resources: Contains the application configuration files.
src/test/java: Contains the test cases.

