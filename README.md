# Dream Stream Documentation Demo

### Table of Contents
1. [Business Requirements](#business-requirements)
2. [Technical Requirements](#technical-requirements)

AWS website [dreamstream website](http://thedreamstream.us-east-2.elasticbeanstalk.com)
View all the Java documentation [here](https://mmitar.github.io/capstone/)
___
### Milestone App Review
Watch the latest review of the application on Youtube [here](https://www.youtube.com/embed/3njvE3QVbVk). 

##### Business Requirements
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

Technical Requirements | |
--- | ---
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
Google Charting API | 

##### try-block
Controller requests are encapsulated in a try-catch block to support custom exception handling used to enforce rules from the business layer.
```java
try {
	User verifiedUser = userService.authenticateUser(user);
}
```
##### throw-exception
The business layer enforces logic that identifys if the data access layer returned a valid user based on the credentials.
```java
if(userDAO.find(user) == null) {
    throw new UserNotFoundException();
}
```
##### catch-block
If an exception is thrown from the business layer, the controller will catch the exception, and can tailor a proper response for the user.
```java
catch(UserNotFoundException e) {
	ModelAndView mv = new ModelAndView("login");
	mv.addObject("error", "Username or Password is incorrect.");
	return mv;
}
```

### Sequence Diagram
![Arduino Action Reporting Sequence Diagram](https://github.com/mmitar/capstone/blob//master/docs/images/Arduino%20Sequence%20Diagram.png?raw=true)

### Block Diagram
![Block Diagram](https://github.com/mmitar/capstone/blob/master/docs/images/Block%20Diagram.png?raw=true)

### Class UML Diagrams
![Class Diagrams](https://github.com/mmitar/capstone/blob/master/docs/images/Class%20Diagrams.png?raw=true)

### Component Diagram
![Component Diagram](https://github.com/mmitar/capstone/blob/master/docs/images/Component%20Diagram.png?raw=true)
 
### ER Diagram
![ER Diagram](https://github.com/mmitar/capstone/blob/master/docs/images/ER%20Diagram.JPG?raw=true)

### Logical Design
![Logical Design](https://github.com/mmitar/capstone/blob/master/docs/images/Logical%20Design.JPG?raw=true)

### Manifest Deployment
![Manifest Deployment](https://github.com/mmitar/capstone/blob/master/docs/images/Manifest%20Deployment.JPG?raw=true)

### Physical Design
![Physical Design](https://raw.githubusercontent.com/mmitar/capstone/master/docs/images/Phyisical%20Design.JPG?raw=true)

### Sitemap
![Sitemap](https://github.com/mmitar/capstone/blob/master/docs/images/Sitemap.png?raw=true)

### Wireframes
![Wireframes](https://github.com/mmitar/capstone/blob/master/docs/images/Wireframes.png?raw=true)

### Deployment Diagram
![Deployment Diagram](https://github.com/mmitar/capstone/blob/master/docs/images/deployment%20diagram.JPG?raw=true)
