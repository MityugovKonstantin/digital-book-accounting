# digital-book-accounting
## About
This application is educational and implements the reduced functionality of the digital library. This service allows the administrator to add, edit, delete, change information about people (readers) and books, as well as search by book title. Also, this service displays the status of the book (it can be free or someone could already take it) and the number of books that a particular user took. Expired books are now flagged (in the human view, the book is underlined in red).

## Technologies
This back-end application was written in Java 17 using the Spring Boot 3.1.2 and Spring Boot Starter Web 3.1.2. For ease of development, a flyway plugin for database migration was added, as well as a Postgres 13 image that was launched using docker-compose technology. Using Hibernate Validator 8.0.1, the fields in the model were validated. Spring Data JPA 3.1.2 and Hibernate Core 6.2.7 are used to communicate with the database.

To develop the view, the following technologies were used: 
1. Thymeleaf for spring6 - a template engine;
2. Standard html.

## Usage
To view a menu, go to the address: /index;

Other transitions between views are carried out using buttons.
