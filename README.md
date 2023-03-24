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
Application provides a set of CRUD htttp methods. 
In order to use them firts you need to get a valid token for Authentication passing valid user credentials.
For now its in memory user that u can use with username: jacob, and password: password
When token is aquired, pass it with any of the user http requests to get autheticated and get response back

