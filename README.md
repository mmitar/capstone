<h1 align="center">The Dream Stream | Development Hub</h1>

Visit the [Dream Stream website](http://thedreamstream.us-east-2.elasticbeanstalk.com) to check out the features the application has to offer. Or scroll down more to get a preview of the technology, applications features, and devOps integrated into the application!

## Table of Contents  
[Embedded System Technology](#Embedded-System-Technology)  
[Application Features](#application-features)  
[DevOps](#devops)  
[Proof of Concepts](#proof-of-concepts)
1. [System Design](https://github.com/mmitar/capstone/tree/master/_System%20Design)
2. [Database Design](https://github.com/mmitar/capstone/tree/master/_Database%20Design)
3. [Class Design](https://github.com/mmitar/capstone/tree/master/_Class%20Design)
4. [API Design](https://github.com/mmitar/capstone/tree/master/_API%20Design)
5. [Application Design](https://github.com/mmitar/capstone/tree/master/_Application%20Design)
6. [Other Design Documentations](https://github.com/mmitar/capstone/tree/master/_Other)

### If your interested in the application's `JavaDoc`, you can view all of it [HERE](https://mmitar.github.io/capstone/).
**Note:** If you've visited the JavaDoc before, clear cache or go incognito to view the most recent revision.

#### `Update!` Our project made the GCU Today Paper! Click the image to view the good news.
[<img width="600" src="https://github.com/mmitar/capstone/blob/master/App%20Images/developers.JPG">](https://d1wsaxfu7b8rcr.cloudfront.net/1d73e22c-d8ab-4b78-81ba-f42dc84f0b66/GCU%20Today%20Article%202019%20Capstone%20Showcase.pdf)

#### Watch the latest review of the application on `Youtube` below.
[<img width="600" src="https://github.com/mmitar/capstone/blob/master/App%20Images/thumbnail.JPG">](https://youtu.be/wjjhJc0QOs4 "Dream Stream Demo Video")

---

## Embedded System Technology

<img align="left" width="300" height="200" src="https://github.com/mmitar/capstone/blob/master/App%20Images/arduino.jpeg">  

### Arduino Stack

The Arduino stack is comprised of the Arduino Uno R3, an ESP8266 Wifi Shield, a prototype shield for good affect, and the HX711 Load cell micro controller. With the proper pinning and I/O port configuration, we have the stack ready to ready to POST data to the server. 

<br/><br/>

<img align="left" width="300" height="200" src="https://github.com/mmitar/capstone/blob/master/App%20Images/load_scale.jpeg">  

### Load Scale

This 5kg load cell uses the strain gauages to monitor the weight distribution. The HX711 micro controller can track all the small changes made fast, and accurately. A 3D printed base and coaster were printed in specification to the load cell.

<br/><br/>

<img align="left" width="300" height="200" src="https://github.com/mmitar/capstone/blob/master/App%20Images/scale_ard.jpeg">  

### Arduino Wired to the Scale

Together, the technology from both the Arduino Stack and the HX711 load cell micro controller provide a powerful combination that can provide real time monitoring and `POST` data directly to the server, providing an automation to liquor inventory tracking.

<br/><br/>

## Application Features

<img align="right" height="300" src="https://github.com/mmitar/capstone/blob/master/App%20Images/inventory.JPG"> 
<img align="right" height="300" src="https://github.com/mmitar/capstone/blob/master/App%20Images/inventory3.JPG"> 

### Inventory Management

Input the inventory you plan to monitor. This can be purposed for identifying a units popularity, tracking its general traffic, or to be notified during low inventory levels. In the future we hope to automate this feature further.

<br/><br/><br/>

<img align="left" height="300" src="https://github.com/mmitar/capstone/blob/master/App%20Images/Activity.JPG"> 

### User Activity Log

Track user activity through this logging utility. Keep users will access to the application accountable for their actions after they've modified your business's inventory or reconfigured a scale. All the data in context is provided.

<br/><br/><br/>

<img align="right" height="300" src="https://github.com/mmitar/capstone/blob/master/App%20Images/Scale%20Config.JPG"> 
<img align="right" height="300" src="https://github.com/mmitar/capstone/blob/master/App%20Images/Scale%20config%20pg2.JPG"> 

### Scale Configuration View

Remotely configure your inventory to the scales it will be using. Only inventory that is unbinded can be applied to a scale. Add more scales based on your business requirements. Remove scales you don't plan to use. 

<br/><br/><br/>

<img align="left" height="300" src="https://github.com/mmitar/capstone/blob/master/App%20Images/Metrics%20p1.JPG"> 
<img align="left" height="300" src="https://github.com/mmitar/capstone/blob/master/App%20Images/Metrics%20p2.JPG"> 

### Business and Inventory Metrics

Monitor real time data to align your inventory stock and consumption rate expectations with your business strategy. Analytics help identify the most popular drinks and stay on top of the competition by giving you up-to-date information to help inform your next move.

<br/><br/><br/>

<br/><br/>

## DevOps

<img align="right" width="300" height="200" src="https://github.com/mmitar/capstone/blob/master/App%20Images/Junit%20Test%20Cases.JPG"> 

### JUnit Test Cases

Our application integrates JUnit into the development process so that after a new module integration, or any significant changes to a service function, we can pinpoint any services that have bugs after changes in under 5 seconds. JUnit is also integrated into the war file build, to ensure all services are opperable before deployment.

<br/><br/>

<img align="right" width="300" height="200" src="https://github.com/mmitar/capstone/blob/master/App%20Images/Code%20Coverage.JPG">  

### Code Coverage

JUnit alone is a huge utility, but what more so can help developers realize what code is actually being executed, is use a Code Coverage tool. We went with EclEmma to give a detailed synopsis over what lines are being executed. We can further configure the percentage of lines covered as a requirement to successfully build a war file.

<br/><br/>

<img align="right" width="300" height="200" src="https://github.com/mmitar/capstone/blob/master/App%20Images/debug%20log.JPG">  

### Logging Strategy

Our application utilizes the LOG4J logging implementation wrapped with the SLF4J framework specification. This is so we can monitor all the activity that happens thoughout the application. We took it a step further and used this tool to provide the `User Activity Logs` feature within the application.

<br/><br/>

## Proof of Concepts
|    Description                                                                                               |    Rational                                                                                                                                                                                             |
|--------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|    1 -    Inconsistent   tasting drinks at higher end bars.                                                   |    System that controls the flow of liquor and is measured.                                                                                                                                             |
|    2 – Documented waste at bars                                                                              |    All liquor use will be measured. If the liquor profit and   consumption don’t add up, managers will know that the bartenders have been   spilling and keep them accountable.                         |
|    3 – Waste of time and labor hours taking inventory                                                        |    Scale will measure and keep track of liquor used and will   communicate with a backend database that will continually be updated with   used liquor.                                                 |
|    4 – Bars/managers not knowing when products are running   low and end up running out and losing sales.    |    Dream   Stream will   offer a web-based application that will keep track of inventory and alert   managers when certain products reach and specified low point.                                      |
|    5 – Happy customers = returning customers                                                                 |    By offering consistent tasting drinks that customers love,   will create a level of trust and satisfaction from customers making them want   to come back for more.                                  |
|    6 – analytics of producer & consumer products behind   bar                                                |    Every business owner needs to understand where his/her   products are going. Dream stream offers this data to display in what ways the   inventory is being consumed – either for profit or waste    |
|    7 - Employee accountability                                                                                |    The dream stream will document when a specific liquor is   used and what time its used. Because of this the manager can see which   bartenders are pouring heavy or too light on drinks.             |
|    8 - More consistent and accurate inventory management.                                                      |    This will prevent bars from running out of their most sold   liquors.                                                                                                                                |
|    9 - company growth due to better strategic product plans.                                                  |    That the bar managers and owners have these analytics,   they can more efficiently and effectively develop their liquor inventory to   more accurately suit the customers wishes.                    |

Back to [Table of Contents](#Table-of-Contents)

