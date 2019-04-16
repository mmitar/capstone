# [Other Design Documentation](https://github.com/mmitar/capstone/tree/master/_Other)

## Table of Contents
[Home Directory](https://github.com/mmitar/capstone)	
1. [System Design](https://github.com/mmitar/capstone/tree/master/_System%20Design)
2. [Database Design](https://github.com/mmitar/capstone/tree/master/_Database%20Design)	
3. [Class Design](https://github.com/mmitar/capstone/tree/master/_Class%20Design)	
4. [API Design](https://github.com/mmitar/capstone/tree/master/_API%20Design)
5. [Application Design](https://github.com/mmitar/capstone/tree/master/_Application%20Design)
6. [Other Design Documentations](https://github.com/mmitar/capstone/tree/master/_Other)
   * [General Technical Approach](#general-technical-approach)
   * [Hardware and Software Technologies](#Hardware-and-Software-Technologies)
   * [Security Design](#security-design)
   * [Operational Support Design](#operational-support-design)
  
## General Technical Approach
Our key general technical approach was to design the scale to be simple, easy to use and compact. One of the ways we did this was we found the smallest parts for the physical scale we were able to, while keeping our scope for the project. We also decided to find chargeable scale, to help minimize the size of the product and to help keep cost down for our customers.

The functionality of the application is a huge part of our meeting our project’s goals. So, we wanted to make sure when designing the application, we wanted to give a lot of control to the user to be able to self configure the system and make sure that the app could scale to their requirements. We believe this is accomplished by letting managers have complete access to assigning scale ID and liquor ID and values. These are key associations for the report log. When a bartender performs an action with the scale, the Post call to the RestService method acts as a façade layer and makes requests to all the different elements of all application to seamlessly handle the inventory automatically.

## Hardware and Software Technologies
| | |
| --- | --- |
AWS Elastic Beanstalk | Spring MVC Framekwork 4.3 |
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

## Security Design
Performance is one example of an NFR we implemented since everything through our REST API is going be have an immediate response time and running real time. We also implemented all generic interfaces to make sure our stability is not limited at the aspect at the class level. Our product will also have great recoverability and maintainability through our extensive logging feature. Every action that is done through the scale is going to be recorded and logged into our database. Along with which scale ID was being used and which bottle it was attached to. By doing these any fatal errors or crashes that we experience will be fully documented and traceable for correction. Even though the data our application is holding is in no way sensitive, we did implement a bit of security. We have a login/authentication page before being able to read, write or delete any data regarding stock/inventory. We also have data validation in place and permission parameters for all accounts, which allows the manager to have full control of which accounts can do what.

## Operational Support Design
We are monitoring our entire project by logging every single action taken on the scale. Every time any action is taken on the scale, it will be recorded via logging, not only the action but also the scale ID and Liquor ID (the scale the bottle is on). By doing this every scale will have a full record of all actions taken on that specific scale and if anything should fail or break, we will be able to track all the steps that specific scale took.

Back to Table of Contents(#table-of-contents)
