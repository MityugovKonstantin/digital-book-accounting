# digital-book-accounting
## About
This application is educational and implements the reduced functionality of the digital library. This service allows the administrator to add, edit, delete and change information about people (readers) and books. Also, this service displays the status of the book (it can be free or someone could already take it) and the number of books that a particular user took.
## Technologies
This back-end application was written in Java 17 using the Spring 6.0.8 framework, Spring Boot 3.0.6. For ease of development, a flyway plugin for database migration was added, as well as a Postgres 13 image that was launched using docker-compose technology. Using hibernate, the fields in the model were validated.

To develop the view, the following technologies were used: 
1. Thymeleaf for spring6 - a template engine;
2. Standard html.
## Usage
To view a menu, go to the address: /index;

Other transitions between views are carried out using buttons.
