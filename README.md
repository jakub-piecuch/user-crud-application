# user-crud-application
User management application - REST API tak provides CRUD operation on User entity which is secured with Spring Security and OAuth2 libraries. 

## Table of Content
- Technologies
- Installation
- Usage

## Technologies
- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- OAuth2 libraries
- JUnit5 & Mockito

## Installation
- Clone the repository: git clone https://github.com/jakub-piecuch/user-crud-application.git
- Open the project in your IDE
- Build the Project
- Run the Project

## Usage
- Application provides a set of CRUD htttp methods. 
- In order to use them first, get a valid token for authentication, by passing valid user credentials with `GET localhost:8080/v1/users/token` request.
- For now its in-memory user that can be used with username: jacob, and password: password
- When token is aquired, pass it with any of the user http request to get autheticated and get response back

