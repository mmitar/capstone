# [API Design Documentation](https://github.com/mmitar/capstone/tree/master/_API%20Design)

## Table of Contents 
[Home Directory](https://github.com/mmitar/capstone)	
1. [System Design](https://github.com/mmitar/capstone/tree/master/_System%20Design)
2. [Database Design](https://github.com/mmitar/capstone/tree/master/_Database%20Design)	
3. [Class Design](https://github.com/mmitar/capstone/tree/master/_Class%20Design)	
4. [API Design](https://github.com/mmitar/capstone/tree/master/_API%20Design)
	* [Log Scale Action](#Log-Scale-Action)
	* [201 Success Response](#201-Success-Response)
	* [208 Error Response](#208-Error-Response)
	* [404 Error Response](#404-Error-Response)
	* [409 Error Response](#409-Error-Response)
	* [500 Error Response](#500-Error-Response)
	* [Sample Call](#Sample-Call)
5. [Application Design](https://github.com/mmitar/capstone/tree/master/_Application%20Design)
6. [Other Design Documentations](https://github.com/mmitar/capstone/tree/master/_Other)

# Log Scale Action
This API is used for collecting data from the Load scale that is connected to are arduino. The Arduino sends a POSTs, whenever in action is taken on any scale. The data sent is what we keep our inventory updated with and along with updating our analytics data.

**URL** : `/rest/inventory/logScale`

**Method** : `POST`

**Auth required** : NO

**Data constraints**

| Value | Required? | Data Type | Explanation |
| ------ | ------ | ------ | ------ |
| locationId | Yes | alphanumeric |Is an uniquely random generated value that represents a location of a resturant |
| scaleId | Yes | integer | Is a unique index value from 0-5 that represent the scales value|
| logQuantity | Yes | integer | Is a number that represents the amount of volume in milliliters removed from the bottle from when the bottle is removed then placed back on the scale|

**Data example**

```json
{
    "locationId": "location1",
    "scaleId": 0,
    "logQuantity": 35
}
```

## 201 Success Response

**Code** : `201 CREATED`

**Content example**

```json
{
    "signal": "GREEN",
    "message": "Success"
}
```

## 208 Error Response

**Condition** : If the arduino sends a POSTs from  the scale and the quanity difference is zero, it will give a 208 response
letting the user know that there was no  difference in volume on this call.

**Code** : `208 ALREADY REPORTED`

**Content example**

```json
{
    "signal": "GREEN",
    "message": "Log quantity insufficient to retain"
}
```

## 404 Error Response

**Condition** : Each scale must be registered to a location before it can be used. If the arduino tries to send a POST, the while the
scale is not registered, it will send a 404 error response.

**Code** : `404 NOT FOUND`

**Content** :

```json
{
    "signal": "YELLOW",
    "message": "Scale does not appear to be registered to the location."
}
```

## 409 Error Response

**Condition** : If is scale is registered to a location but not a liquor, it will send a 409 error reponse because it does not know which liquor in the inventory to update with the data sent.

**Code** : `409 CONFLICT`

**Content** :

```json
{
    "signal": "YELLOW",
    "message": "Liquor is not associated to the scaleon."
}
```

## 500 Error Response

**Condition** : If there is no connection to server, a 500 error reponse will be sent.

**Code** : `500 INTERNAL SERVER ERROR`

**Content** :

```json
{
    "signal": "RED",
    "message": "Database Error."
}
```

## Sample Call

The sample call is made up of HTTP message and are used for the communication between the client and server to send data via API. The sample call is being posted from the arduino based off the system requirements.

```
POST /rest/inventory/logScale HTTP/1.1  
Host: thedreamstream.us-east-2.elasticbeanstalk.com  
Content-Type: application/json  
Content-Length: 55  
Accept: application/json  
User-Agent: BuildFailureDetectorESP8266  
Cache-Control: no-cache, no-store, must-revalidate  
Pragma: no-cache  
Expires: 0  
Connection: close  

{"locationId":"location1","scaleId":0,"logQuantity":35}
```
Back to [Table of Contents](#Table-of-Contents)
