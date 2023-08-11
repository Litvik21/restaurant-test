<div id="header" align="center">
  <img src="logo-rest.png" width="500" alt="Logo"/>
</div>

## 📖 Description
This project demonstrates the simple operation of a program for a restaurant,
the main purpose of which is to demonstrate the creation of orders and their
display for staff in real time.


## 📋 Project structure
**The project has an 3-Tier Architecture**
- Controller - This level allows the user to work with this application.
- Service - This level of architecture is responsible for processing the data received from the DAO level.
- Repository - This level of architecture is responsible for communicating with the database.

## 🎯 Features
- Registering/Logging
- Save to DB all of models
- See posts of current user
- See posts of searching user
- Staff can change status of order

## 🖥️ Technologies
- Java 17
- Maven
- MySQL
- Tomcat
- Swagger
- Spring Boot/Web/MVC/Data/Security(JWT token)
- DOCKER
- Angular

## ⚡️Quickstart

### For launch project

1. Install Docker Desktop and register on DockerHub

2. Run next command in terminal from `restaurant` directory:

   > mvn clean package

3. Run next command in terminal from the main directory:

   > docker-compose up --build

4. Open your browser on http://localhost:4200.

### For local development

1. Fork this repository
2. Copy link of project
3. Create new project from Version Control
4. Edit api/resources/application.properties - set the necessary parameters

``` java
    spring.datasource.driver-class-name=YOUR_DRIVER
    spring.datasource.url=YOUR_URL
    spring.datasource.username=YOUR_USERNAME
    spring.datasource.password=YOUR_PASSWORD
```
5. Do not forget set this param on "create" for first project run. Like this:
``` java
    spring.jpa.hibernate.ddl-auto=create
```
6. Create the necessary name of DB
7. Run project

## 👀 Example of parameters for db.properties
``` java
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/NameOfDataBase?useUnicode=true&serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=123456
```

<div id="header" align="center">
  <img src="Logo.png" width="568" alt="Logo"/>
</div>