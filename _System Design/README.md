# [System Design](https://github.com/mmitar/capstone/tree/master/_System%20Design)

## Table of Contents
[Home Directory](https://github.com/mmitar/capstone)
1. [Proof of Concepts](https://github.com/mmitar/capstone/tree/master/_Proof%20of%20Concept)	
2. [System Design](https://github.com/mmitar/capstone/tree/master/_System%20Design)
	* [Logical Design](#Logical-Design)
		* [N-Layer Architecture](#N-Layer-Architecture-Local-Design)
	* [Physical Design](#physical-design)
		* [1-Tier Architecture](#1-Tier-Architecture-Physical-Design)
		* [3-Tier Architecture](#3-Tier-Architecture-Physical-Design)
	* [UML Deployment Design](#Deployment-Design)
3. [Database Design](https://github.com/mmitar/capstone/tree/master/_Database%20Design)	
4. [Class Design](https://github.com/mmitar/capstone/tree/master/_Class%20Design)	
5. [API Design](https://github.com/mmitar/capstone/tree/master/_API%20Design)
6. [Application Design](https://github.com/mmitar/capstone/tree/master/_Application%20Design)
7. [Other Design Documentations](https://github.com/mmitar/capstone/tree/master/_Other)

## Logical Design

### N-layer-Architecture Local Design
The application is developed with an N-Layer architecture that uses an inversion of control philosophy. As represented in the picture, each layer of the design is established within its own section, communicating between each other by the required protocols or data channels. Each layer emphasizes its own logic and using its own technologies to accomplish the task. Users implement requests to the Presentation Layer from the Client Layer. The Presentation Layer behaves as the request handler and well ask help from business services to accomplish the task that was requested by the client. The Business Layer has rules defined to ensure compliance from the request, making sure the data being handled is sufficient to fully accomplish the task and will assemble or modify the data as necessary. However, the Data Access Layer is defined to communicate with the database directly. The Business Layer may request the help from Data Layer to get information from the database to either ensure business rules or to accomplish the initial request. This application utilizes an Arduino for real-time data monitoring. The data is submitted to the server’s REST application programming interface, that has an open endpoint for the HTTP POST request to be handled.
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/_System%20Design/N-Layer%20Architecture.png"/></p>

## Physical Design

### 1-Tier Architecture Physical Design
The N-Tier diagrams have information regarding the HTTP Port protocol used to establish a connection between the tiers or systems. Both diagrams include the technologies each tier uses respectively as well as the versions within the application tier.

This design is of a local hosted environment resembling a 1 Tier Architecture. All the application Tiers is on the same device. The 1 Tier physical solution is used for development testing, running on the Apache Tomcat Web Server. The local MySQL database is connected to using MAMP. 
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/_System%20Design/N-Tier%201%20Architecture.png"/></p>


### 3-Tier Architecture Physical Design
This design is of the application and the database being hosted on AWS resembling a 3 Tier Architecture. All the technology the application utilizes are remote from each other, on different networks, which means the physical solution during project’s deployment is truly partitioned.
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/_System%20Design/N-Tier%203%20Architecture.png"/></p>



### Deployment Design
The deployment diagram represents the application stack, its dependencies, interactions, and communication ports. Starting off with the application server hosted on an AWS Elastic Beanstalk container that is our virtual environment, encapsulating the Web Server. Defined within the Web Server is the application JSP Server Apache Tomcat 8.5 that runs the application in the JVM. Within that is our JDK compiler, which complies with the runtime execution. Finally, the application is deployed via war file to the Web Server. The war files contain the build of the application, consisting of configuration files, library management tools, and dispatch servlets.

Java database connections (JDBC) are made over the 3306 port from the app and to the MySQL database and back. The Database Server is hosted by AWS, is not network access restricted, but requires the password and username defined in the property files.

Maven Central is a library repository contains the dependencies of the application defined in the pom.xml. The Arduino Uno performs HTTP POST requests to the application server’s RESTful API open endpoint. The client side utilizes a 3rd party platform for drawing charts and graphs representative of the data it using. The client also requires JSP pages for dynamic view assembly with the model and view assembled in the dispatch servlet within the Spring MVC framework.
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/_System%20Design/Deployment%20Diagram.png">


Back to [Table of Contents](#Table-of-Contents)
