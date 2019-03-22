# The Dream Stream Documentation Development


## Milestone App Review
Watch the latest review of the application on Youtube [here](https://www.youtube.com/embed/3njvE3QVbVk). 

AWS website [dreamstream website](http://thedreamstream.us-east-2.elasticbeanstalk.com)  
View all the Java documentation [here](https://mmitar.github.io/capstone/)

## Table of Contents
1. [Application Requirements](#application-requirements)
	* [Functional Requirements](#functional-requirements)
	* [Non-Functional Requirements](#non-functional-requirements)
2. [Application Architecture](#application-architecture)
3. [Database Design](#database-design)
4. [Deployment Architecture](#deployment-architecture)

## Application Requirements

### Functional Requirements
| | |
| --- | --- |
AWS Elastic Beanstalk | Spring MVC Framekwork 3.0 |
Dynamic Web Application Framework 3.1 | Eclipse Oxygen IDE 3.0 |
JDK Compile Environment 1.8 | Tomcat Server 8.5 |
Java 8 | MySQL DB Connector Library for Java 5.1 |
Spring JDBC 4.3 | JAX-RS JSON provider 2.4 |
Servlet REST API 2.3 | JUnit Testing 4.12 |
SLF4J 1.7 / LOG4J 1.2 | MAMP |
MySQL Workbench | AWS Cloud Platform |
HTML / CSS / JavaScript 5.0 | JQuery 3.3 |
SVG | JSTL 1.2 |
Tiles 3.0 | Arduino Uno Elegoo |
ESP8266 Wifi Module | HX711 Load Scale Module |
Google Charting API | REST APIs

### Non-Functional Requirements
* Monitor user activity on the application
* Monitor liquor activity via Arduino Embedded System
* Notify vendors for liquor use violations
* Configure Spouts to Liquor association
* Consume data and provide reporting for locations and vendors
* Vendors can add locations
* Vendors can modify locations they oversee
* Vendors can add users to locations
* Location managers can implement all CRUD methods on liquor for inventory management
* Accessible from all access points
* Secure Login with permission-based actions

#### Sequence Diagram
![Arduino Action Reporting Sequence Diagram]
<p align="center"><img src="https://github.com/mmitar/capstone/blob//master/docs/images/Arduino%20Sequence%20Diagram.png" width="700"/></p>

#### Block Diagram
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/docs/images/Block%20Diagram.png" width="700"/></p>

## Application Architecture

### Logical Design
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/docs/images/Logical%20Design.JPG" width="700"/></p>

### Physical Design
<p align="center"><img src="https://raw.githubusercontent.com/mmitar/capstone/master/docs/images/Phyisical%20Design.JPG" width="700"/></p>

### Deployment Diagram
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/docs/images/deployment%20diagram.JPG" width="700"/></p>

### Component Diagram
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/docs/images/Component%20Diagram.png" width="700"/></p>

#### try-block
Controller requests are encapsulated in a try-catch block to support custom exception handling used to enforce rules from the business layer.
```java
try {
	User verifiedUser = userService.authenticateUser(user);
}
```
#### throw-exception
The business layer enforces logic that identifys if the data access layer returned a valid user based on the credentials.
```java
if(userDAO.find(user) == null) {
	throw new UserNotFoundException();
}
```
#### catch-block
If an exception is thrown from the business layer, the controller will catch the exception, and can tailor a proper response for the user.
```java
catch(UserNotFoundException e) {
	ModelAndView mv = new ModelAndView("login");
	mv.addObject("error", "Username or Password is incorrect.");
	return mv;
}
```


### Manifest Deployment
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/docs/images/Manifest%20Deployment.JPG" width="700"/></p>

### Class UML Diagrams
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/docs/images/Class%20Diagrams.png" width="700"/></p>

### Sitemap
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/docs/images/Sitemap.png" width="700"/></p>

### Wireframes
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/docs/images/Wireframes.png" width="700"/></p>

## Database Design

This database design meets our application's solution for the essesntial related table categories together. This ensures that data cannot be inserted into the database without containing verifying details that relate it to its depedent key. Users were designed to be independent, existing with or without a relationship to locations. This is because both vendors are location managers / users are combined, seperated by their permission role and status.

### ER Diagram
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/docs/images/ER%20Diagram.JPG" width="700"/></p>

