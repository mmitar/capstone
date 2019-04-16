# The Dream Stream Documentation Development


## Milestone App Review
Watch the latest review of the application on Youtube [here](https://www.youtube.com/embed/3njvE3QVbVk). 

AWS website [dreamstream website](http://thedreamstream.us-east-2.elasticbeanstalk.com)  
View all the Java documentation [here](https://mmitar.github.io/capstone/)

## Table of Contents	
1. [System Design](https://github.com/mmitar/capstone/tree/master/_System%20Design)
2. [Database Design](https://github.com/mmitar/capstone/tree/master/_Database%20Design)	
3. [Class Design](https://github.com/mmitar/capstone/tree/master/_Class%20Design)	
4. [API Design](https://github.com/mmitar/capstone/tree/master/_API%20Design)
5. [Application Design](https://github.com/mmitar/capstone/tree/master/_Application%20Design)
6. [Other Design Documentations](https://github.com/mmitar/capstone/tree/master/_Other)

## App Images

<img align="left" width="300" height="200" src="https://github.com/mmitar/capstone/blob/master/App%20Images/arduino.jpeg">  

### Arduino Stack

The Arduino stack is comprised of the Arduino Uno R3, an ESP8266 Wifi Shield, a prototype shield for good affect, and the HX711 Load cell micro controller. With the proper pinning and I/O port configuration, we have the stack ready to ready to POST data to the server. 

<br/><br/><br/><br/>

<img align="left" width="300" height="200" src="https://github.com/mmitar/capstone/blob/master/App%20Images/load_scale.jpeg">  

### Load Scale

This 5kg load cell uses the strain gauages to monitor the weight distribution. The HX711 micro controller can track all the small changes made fast, and accurately. A 3D printed base and coaster were printed in specification to the load cell.

<br/><br/><br/><br/>

<img align="left" width="300" height="200" src="https://github.com/mmitar/capstone/blob/master/App%20Images/scale_ard.jpeg">  

### Arduino Wired to the Scale

Together, the technology from both the Arduino Stack and the HX711 load cell micro controller provide a powerful combination that can provide real time monitoring and `POST` data directly to the server, providing an automation to liquor inventory tracking.

<br/><br/><br/><br/>

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



