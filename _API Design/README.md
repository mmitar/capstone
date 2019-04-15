# The Dream Stream Documentation Development


## Milestone App Review
Watch the latest review of the application on Youtube [here](https://www.youtube.com/embed/3njvE3QVbVk). 

AWS website [dreamstream website](http://thedreamstream.us-east-2.elasticbeanstalk.com)  
View all the Java documentation [here](https://mmitar.github.io/capstone/)

## Table of Contents 
[Root Directory](https://github.com/mmitar/capstone)
1. [Proof of Concepts](https://github.com/mmitar/capstone/tree/master/_Proof%20of%20Concept)	
2. [System Design](https://github.com/mmitar/capstone/tree/master/_System%20Design)
3. [Database Design](https://github.com/mmitar/capstone/tree/master/_Database%20Design)	
4. [Class Design](https://github.com/mmitar/capstone/tree/master/_Class%20Design)	
5. [API Design](https://github.com/mmitar/capstone/tree/master/_API%20Design)
	*[Swagger Documentation](https://github.com/mmitar/capstone/tree/master/_API%20Design)
6. [Application Design](https://github.com/mmitar/capstone/tree/master/_Application%20Design)
7. [Other Design Documentations](https://github.com/mmitar/capstone/tree/master/_Other)

# Dream Stream - Log Scale Action

Used to collect data from load scale

**URL** : `/api/login/`

**Method** : `POST`

**Auth required** : NO

**Data constraints**

```json
{
    "locationId": "[Valid Location that also is paired with Scale ID]",
    "scaleId": [Scale ID that matches with location ID],
    "logQuantity": [valid log quantity]
}
```

**Data example**

```json
{
    "locationId": "location1",
    "scaleId": 0,
    "logQuantity": 35
}
```

## 200 Success Response

**Code** : `200 OK`

**Content example**

```json
{
    "signal": "GREEN",
    "message": "Success"
}
```

## 400 Error Response

**Condition** : If scale does not appear to be registered to the location.

**Code** : `400 BAD REQUEST`

**Content** :

```json
{
    "signal": "YELLOW",
    "message": "Scale does not appear to be registered to the location."
}
```

## 400Error Response

**Condition** : If liquor is not associated to the scale.

**Code** : `400 BAD REQUEST`

**Content** :

```json
{
    "signal": "YELLOW",
    "message": "Liquor is not associated to the scaleon."
}
```

## 500 Error Response

**Condition** : If there is no connection to server.

**Code** : `500 FATAL ERROR`

**Content** :

```json
{
    "signal": "RED",
    "message": "Database Error."
}
```

