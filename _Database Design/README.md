# Database Design


## Table of Contents
[Root Directory](https://github.com/mmitar/capstone)
1. [Proof of Concepts](https://github.com/mmitar/capstone/tree/master/_Proof%20of%20Concept)	
2. [System Design](https://github.com/mmitar/capstone/tree/master/_System%20Design)
3. [Database Design](https://github.com/mmitar/capstone/tree/master/_Database%20Design)	
	* [ER Diagram Design](#ER-Diagram)
	* [DDL Script](#DDL-Script)
4. [Class Design](https://github.com/mmitar/capstone/tree/master/_Class%20Design)	
5. [API Design](https://github.com/mmitar/capstone/tree/master/_API%20Design)
6. [Application Design](https://github.com/mmitar/capstone/tree/master/_Application%20Design)
7. [Other Design Documentations](https://github.com/mmitar/capstone/tree/master/_Other)

#### ER-Diagram
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/_Database%20Design/ER.png"/></p>

#### DDL-Script
```ddl
CREATE TABLE `liquors` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `liquorID` int(11) NOT NULL,
  `brandName` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `volume` float NOT NULL,
  `overflow` int(11) NOT NULL,
  `alertLevel` int(11) NOT NULL,
  `liquorcol` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1


CREATE TABLE `logs` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `spoutID` int(11) NOT NULL,
  `liquorID` int(11) NOT NULL,
  `alert` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `spoutID_idx` (`spoutID`),
  KEY `liquorID_idx` (`liquorID`),
  CONSTRAINT `spoutID` FOREIGN KEY (`spoutID`) REFERENCES `spout_liquor` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `liquorID` FOREIGN KEY (`liquorID`) REFERENCES `liquors` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1


CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `FIRSTNAME` varchar(45) NOT NULL,
  `LASTNAME` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `PERMISSION` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

CREATE TABLE `spout_liquor` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `spoutID` int(11) NOT NULL,
  `liquorID` int(11) NOT NULL,
  `action` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `liquorID_idx` (`liquorID`),
  CONSTRAINT `FK_LIQUORID` FOREIGN KEY (`liquorID`) REFERENCES `liquors` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1
```

Back to [Table of Contents](#Table-of-Contents)
