# Spring Boot Authors & Books Web App

## Purpose
This is a simple web application built with Spring Boot to manage authors and their books. It allows you to add, view, edit, and delete authors and books, and view relationships between them.

## How to Run
1. Make sure you have Java 17+ and Maven installed.
2. Clone this repository.
3. Run `mvn spring-boot:run` in the project root.
4. Visit [http://localhost:8080/authors](http://localhost:8080/authors) or [http://localhost:8080/books](http://localhost:8080/books) in your browser.

## Features
- Add, edit, delete authors and books
- List all authors and all books
- View author details (with their books)
- View book details (with author info)
- Each book has one author; each author can have zero or more books
- Form validation for input

## Application Structure
- `model/` - JPA entities: `Author`, `Book`
- `repository/` - Spring Data repositories: `AuthorRepository`, `BookRepository`
- `controller/` - Web controllers: `AuthorController`, `BookController`
- `templates/` - Thymeleaf HTML templates for all pages
- `application.properties` - H2 in-memory database configuration

## Database
- Uses H2 in-memory database

## Tutorials Used
- Spring Boot RESTful Web Service
- Accessing Relational Data using Spring Data JDBC
- Validating Form Input



