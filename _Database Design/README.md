# [Database Design](https://github.com/mmitar/capstone/tree/master/_Database%20Design)


## Table of Contents
[Home Directory](https://github.com/mmitar/capstone)	
1. [System Design](https://github.com/mmitar/capstone/tree/master/_System%20Design)
2. [Database Design](https://github.com/mmitar/capstone/tree/master/_Database%20Design)	
	* [ER Diagram Design](#ER-Diagram)
	* [Data Table Dictionary](#Data-Table-Dictionary)
		* [users Database Table](#users-Database-Table)
		* ['locations' Database Table](#locations-Database-Table)
		* ['liquors' Database Table](#'liquors'-Database-Table:)
		* ['scale_liquor' Database Table](#'scale_liquor'-Database-Table:)
		* ['scale_logs' Database Table](#'scale_logs'-Database-Table:)
		* ['activity_logs' Database Table](#'activity_logs'-Database-Table:)
	* [DDL Script](#DDL-Script)
3. [Class Design](https://github.com/mmitar/capstone/tree/master/_Class%20Design)	
4. [API Design](https://github.com/mmitar/capstone/tree/master/_API%20Design)
5. [Application Design](https://github.com/mmitar/capstone/tree/master/_Application%20Design)
6. [Other Design Documentations](https://github.com/mmitar/capstone/tree/master/_Other)

### ER Diagram
This ER Diagram represents our application database structure. All the tables besides `users` table have foreign key constraints with the locations table on the `LOCATIONID` column. This column is defined as a unique index because of its strict dependency on all the other data associated. In all fields, the foreign key cannot be null and always be defined. This is to support multiple different locations, or businesses, each with its defined infrastructure. Only the `scale_logs` table has another foreign key constraint to the `LIQUORCODE` column from the `liquors` table. Data reported from the scale should never be retained if the liquor code does not exist in the `liquors` table. There are business rules to enforce the foreign key constraints before saving the log to report an accurate message back to the requester. 
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/_Database%20Design/ER.png"/></p>

### Data Table Dictionary

#### users Database Table:

|    Column Name    |    Data Type    |    Character Length    |    Required?    |    Description                                                                                              |
|-------------------|-----------------|------------------------|-----------------|-------------------------------------------------------------------------------------------------------------|
|    ID             |    INT          |    11                  |    Y            |    Primary Key, Auto Incrementing                                                                           |
|    USERNAME       |    VARCHAR      |    20                  |    Y            |    Username of the account. Unique in the system.                                                           |
|    PASSWORD       |    VARCHAR      |    20                  |    Y            |    Username of the account. Not unique.                                                                     |
|    FIRSTNAME      |    VARCHAR      |    20                  |    N            |    First name of the account                                                                                |
|    LASTNAME       |    VARCHAR      |    20                  |    N            |    Last name of the account                                                                                 |
|    EMAIL          |    VARCHAR      |    30                  |    N            |    Email of the account. May be used for messaging alerts.                                                  |
|    PHONE          |    VARCHAR      |    14                  |    N            |    Phone number of the account. Formatted with symbols.                                                     |
|    PERMISSION     |    INT          |    11                  |    N            |    Permission value of the account that defines its role.                                                   |
|    VENDORID       |    VARCHAR      |    20                  |    N            |    For accounts defined with a vendor permission, used to   associate to locations.                         |
|    LOCATIONID     |    VARCHAR      |    20                  |    N            |    For accounts defined with a manager or user permission,   used to associate to the specific location.    |

#### ';locations Database Table:
|    Column Name      |    Data Type    |    Character Length    |    Required?    |    Description                                                                                                         |
|---------------------|:---------------:|-----------------------:|-----------------|------------------------------------------------------------------------------------------------------------------------|
|    ID               |       INT       |                  11    |    Y            |    Primary Key, Auto Incrementing                                                                                      |
|    VENDORID         |     VARCHAR     |                  20    |    Y            |    Id of the vendor’s account binded to this location.                                                                 |
|    LOCATIONID       |     VARCHAR     |                  20    |    Y            |    Unique index used as a foreign constraint for   `activity_logs`, `scale_logs`, `liquors`, `scale_liquor` tables.    |
|    ADDRESS          |    VARCHAR      |    100                 |    Y            |    Address of the location                                                                                             |
|    ZIPCODE          |    VARCHAR      |    100                 |    Y            |    Zipcode of the location                                                                                             |
|    STATE            |    VARCHAR      |    100                 |    Y            |    State of the location                                                                                               |
|    COUNTRY          |    VARCHAR      |    100                 |    Y            |    Country of the location                                                                                             |
|    LICENSENUMBER    |    VARCHAR      |    100                 |    Y            |                                                                                                                        |
|    LICENSEDATE      |    TIMESTAMP    |    n/a                 |    Y            |    Time the vendors licensed the location.                                                                             |
|    DESCRIPTION      |    VARCHAR      |    1000                |    N            |    Markdown description of the location.                                                                               |

#### 'liquors' Database Table:
|    Column Name     |    Data Type    |    Character Length    |    Required?    |    Description                                                                                                                  |
|--------------------|:---------------:|-----------------------:|-----------------|---------------------------------------------------------------------------------------------------------------------------------|
|    ID              |       INT       |                  11    |    Y            |    Primary Key, Auto Incrementing                                                                                               |
|    LOCATIONID      |     VARCHAR     |                  20    |    Y            |    Foreign key constraint to the `locations` table. Defines   where this data belongs.                                          |
|    LIQUORCODE      |     VARCHAR     |                  20    |    Y            |    Unique index that the `scale_logs` is constraint with.                                                                       |
|    BRANDNAME       |    VARCHAR      |    25                  |    Y            |    Name of the drink                                                                                                            |
|    ALCOHOLTYPE     |    VARCHAR      |    25                  |    Y            |    Proof type                                                                                                                   |
|    LIQUIDVOLUME    |    INT          |    11                  |    Y            |    How much the bottle holds in milliliters                                                                                     |
|    OVERFLOW        |    INT          |    11                  |    Y            |    How many currently in stock, like inventory quantity.                                                                        |
|    ALERTLEVEL      |    INT          |    11                  |    Y            |    When the overflow number hits this fields number, it   behaves as a flag and alerts the business of low inventory levels.    |
#### 'scale_liquor' Database Table:
|    Column Name    |    Data Type    |    Character Length    |    Required?    |    Description                                                                                               |
|-------------------|:---------------:|-----------------------:|-----------------|--------------------------------------------------------------------------------------------------------------|
|    ID             |       INT       |                  11    |    Y            |    Primary Key, Auto Incrementing                                                                            |
|    LOCATIONID     |     VARCHAR     |                  20    |    Y            |    Foreign key constraint to the `locations` table. Defines   where this data belongs.                       |
|    SCALEID        |       INT       |                  11    |    Y            |    Index of the scale based on how many belong to the   location.                                            |
|    LIQUORCODE     |    VARCHAR      |    20                  |    N            |    Value of the liquor’s unique code. Allows null so that the   scale can be configured and deconfigured.    |
#### 'scale_logs' Database Table:
|    Column Name    |    Data Type    |    Character Length    |    Required?    |    Description                                                                                                               |
|-------------------|:---------------:|-----------------------:|-----------------|------------------------------------------------------------------------------------------------------------------------------|
|    ID             |       INT       |                  11    |    Y            |    Primary Key, Auto Incrementing                                                                                            |
|    LOCATIONID     |     VARCHAR     |                  20    |    Y            |    Foreign key constraint to the `locations` table. Defines   where this data belongs.                                       |
|    SCALEID        |       INT       |                  11    |    Y            |    Index of the scale that submitted the data.                                                                               |
|    LIQUORCODE     |    VARCHAR      |    20                  |    Y            |    Code of the liquor that the scale, posting the data, is configured   to.                                                  |
|    LOGTIME        |    TIMESTAMP    |    n/a                 |    Y            |    Time of the log.                                                                                                          |
|    LOGQUANTITY    |    FLOAT        |    n/a                 |    Y            |    Log quantity. Data submitted from the Arduino will be   rounded off to integers. Column set to float for input safety.    |
|    LOGMESSAGE     |    VARCHAR      |    1000                |    Y            |                                                                                                                              |
#### 'activity_logs' Database Table:
|    Column Name    |    Data Type    |    Character Length    |    Required?    |    Description                                                                            |
|-------------------|:---------------:|-----------------------:|-----------------|-------------------------------------------------------------------------------------------|
|    ID             |       INT       |                  11    |    Y            |    Primary Key, Auto Incrementing                                                         |
|    LOCATIONID     |     VARCHAR     |                  20    |    Y            |    Foreign key constraint to the `locations` table.   Defines where this data belongs.    |
|    LOGLEVEL       |     VARCHAR     |                   5    |    Y            |    Matches the log level from log4j logging                                               |
|    LOGUSER        |    VARCHAR      |    1000                |    N            |    User invoking the log                                                                  |
|    LOGMETHOD      |    VARCHAR      |    1000                |    N            |    The method the log was invoked from                                                    |
|    LOGMESSAGE     |    VARCHAR      |    1000                |    N            |    The message regarding the action, request invoked, or   response from request.         |
|    LOGTIME        |    TIMESTAMP    |    n/a                 |    N            |    Time of the executed event.                                                            |
### DDL Script
```ddl
CREATE DATABASE  IF NOT EXISTS `streamdream` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `streamdream`;

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locations` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `VENDORID` varchar(20) NOT NULL,
  `LOCATIONID` varchar(20) NOT NULL,
  `ADDRESS` varchar(100) NOT NULL,
  `ZIPCODE` varchar(100) NOT NULL,
  `STATE` varchar(100) NOT NULL,
  `COUNTRY` varchar(100) NOT NULL,
  `LICENSENUMBER` varchar(100) NOT NULL,
  `LICENSEDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `LOCATIONID_UNIQUE` (`LOCATIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `liquors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `liquors` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOCATIONID` varchar(20) NOT NULL,
  `LIQUORCODE` varchar(20) NOT NULL,
  `BRANDNAME` varchar(25) NOT NULL,
  `ALCOHOLTYPE` varchar(25) NOT NULL,
  `LIQUIDVOLUME` int(11) NOT NULL,
  `OVERFLOW` int(11) NOT NULL,
  `ALERTLEVEL` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `LIQUORCODE_UNIQUE` (`LIQUORCODE`),
  KEY `FK_LOCATION_ID_idx` (`LOCATIONID`),
  CONSTRAINT `LOCATIONID_LIQUOR_FK` FOREIGN KEY (`LOCATIONID`) REFERENCES `locations` (`LOCATIONID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `activity_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_logs` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOCATIONID` varchar(20) NOT NULL,
  `LOGTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LOGLEVEL` varchar(5) NOT NULL,
  `LOGMETHOD` varchar(100) NOT NULL,
  `LOGUSER` varchar(1000) NOT NULL,
  `LOGMESSAGE` varchar(1000) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `LOCATIONID_FK_idx` (`LOCATIONID`),
  CONSTRAINT `LOCATIONID_FK` FOREIGN KEY (`LOCATIONID`) REFERENCES `locations` (`LOCATIONID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `scale_liquor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scale_liquor` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOCATIONID` varchar(20) NOT NULL,
  `SCALEID` int(11) NOT NULL,
  `LIQUORCODE` varchar(20) NULL,
  PRIMARY KEY (`ID`),
  KEY `LOCATIONID_SCALE_FK_idx` (`LOCATIONID`),
  CONSTRAINT `LOCATIONID_SCALE_FK` FOREIGN KEY (`LOCATIONID`) REFERENCES `locations` (`LOCATIONID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `scale_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scale_logs` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOCATIONID` varchar(20) NOT NULL,
  `SCALEID` int(11) NOT NULL,
  `LIQUORCODE` varchar(20) NOT NULL,
  `LOGTIME` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LOGQUANTITY` float NOT NULL,
  `LOGMESSAGE` varchar(1000) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `LOCATIONID_SCALE_FK_idx` (`LOCATIONID`),
  KEY `LIQUOR_SCALELOGS_FK_idx` (`LIQUORCODE`),
  CONSTRAINT `LOCATIONID_SCALELOGS_FK` FOREIGN KEY (`LOCATIONID`) REFERENCES `locations` (`LOCATIONID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `LIQUOR_SCALELOGS_FK` FOREIGN KEY (`LIQUORCODE`) REFERENCES `liquors` (`LIQUORCODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `FIRSTNAME` varchar(20) DEFAULT NULL,
  `LASTNAME` varchar(20) DEFAULT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `PHONE` varchar(14) DEFAULT NULL,
  `PERMISSION` int(11) DEFAULT NULL,
  `VENDORID` varchar(20) DEFAULT NULL,
  `LOCATIONID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
```

Back to [Table of Contents](#Table-of-Contents)
