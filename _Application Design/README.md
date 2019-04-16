# [Application Design](https://github.com/mmitar/capstone/tree/master/_Application%20Design)

## Table of Contents
[Home Directory](https://github.com/mmitar/capstone)	
1. [System Design](https://github.com/mmitar/capstone/tree/master/_System%20Design)
2. [Database Design](https://github.com/mmitar/capstone/tree/master/_Database%20Design)	
3. [Class Design](https://github.com/mmitar/capstone/tree/master/_Class%20Design)	
4. [API Design](https://github.com/mmitar/capstone/tree/master/_API%20Design)
5. [Application Design](https://github.com/mmitar/capstone/tree/master/_Application%20Design)
	* [Application Flowchart Design](#Application-Flowchart-Design)
	* [Scale Flowchart Design](#Scale-Flowchart-Design)
	* [Sitemap Design](#Sitemap-Design)
	* [UI Wireframe Design](#Wireframe-Design)
	* [Sequence Diagram Design](#Sequence-Diagram-Design)
	* [Custom Exception Events](#custom-exception-events)
	* [Block Diagram Design](#Block-Diagram-Design)
	* [Use Case Diagram Design](#Use-Case-Diagram-Design)
6. [Other Design Documentations](https://github.com/mmitar/capstone/tree/master/_Other)

### Application Flowchart Design
The flowchart represents the process flow of the application’s services. It also demonstrates the model validation or business logic that is used to verify the data submitted is appropriate and sufficient to finish the request. The request process flow may either trace back to the page from which the request was implemented or navigate to a different view to support the next request. 
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/_Application%20Design/App%20Flowchart.png"/></p>

### Scale Flowchart Design
This flow chart represents the logical flow of the Arduino scale and the actions it takes before POSTing the data to the server. On Startup, the Arduino attempts to establish a wifi connection to the network so that it has a means to communicate to the server. If the wifi cannot be established, the Arduino stops, and a red light is triggered to show that there’s an issue. After a connection is established, it goes into a loop calculating the initial weight, getting data from the scale. Then it waits calculating a difference in a following weight after some significant changes are registered. If the weight is significant to save it, it retains the data and tries to establish a connection to the server while initializing the client. If the connection fails, it stores the data locally till the connection to the server is made. If the client is proper initialized, the data is converted to JSON, a HTTP POST message is assembled and sent to the server. The response is returned and parsed. The light will reflect the status message returned from the server.
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/_Application%20Design/ScaleFlowChart.png"/></p>

### Sitemap Design
Both these sitemaps represent the page layout and navigational pathing to each view for businesses (managers and standard users) or vendors. Both start from the login page that navigate them to the home page. From there, a navigable header will appear that gives access to the rest of the website based on your account’s specific role.
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/_Application%20Design/Sitemap.png"/></p>

### Wireframe Design
The wireframes represent the views the application will use. Some of the pages show that validation will be implemented for the views if the fields are invalid based model validation. There is a distinction between some of the views based on the header links which refer to whether a business (manager & standard user) or a vendor a logged in. Vendors have their own views to manage all the businesses they oversee and license the liquor towards.
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/_Application%20Design/Wireframes.png"/></p>

### Sequence Diagram Design
This sequence diagram represents the sequential order of the request chain and all the classes, models, services, logic, and exceptions that the Arduino scale HTTP POST to REST API service uses to validate the request, save the log if possible, and return a response based on the result. Once the Arduino POSTs to the server, the API has an open endpoint to accept the request. Following the lifecyles chain, the REST service requests help from the `ScaleBusinessService`, calling a method called `saveScaleLog()`. The method takes the data and checks the database to see if the `scaleId` is registered to the `locationId`. If the data returns null, a `ScaleNotFoundException` is thrown, cancelling the rest of the request. If a scale exists to the location, the next check is to see if the scale is associated with a `liquorCode`. A `LiquorNotFoundException` is thrown if no liquor is associated to the scale because the calculated drink data has to have a liquor reference. Before saving the log, a check of the quantity amount is checked. If the amount is vastly negative, that means the bottle was replaced as decremented liquor differences are positive values. The liquor’s inventory quantity is adjusted accordingly in that case. If the log quantity is a standard positive value, it is saved as a normal log. If an exception was thrown at any point, the REST service will proceed to assemble a response based on the exception thrown. If the response was successfully saved and no issues were encountered, then a successful rest response is returned. All REST responses are assembled within a data transfer object, wrapped in a Response Entity, to which we can set the HTTP Status reflecting the response result.
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/_Application%20Design/Scale%20POST%20Sequence%20Diagram.png"/></p>

### Custom Exception Events
This code snippet resembles the business rule being enforced by validating the data before proceeding with the request. If the data does not meet the standard to finish the request, an exception is thrown and a response is handled. This snippet emmulates a request submitted from the controller.

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

### Block Diagram Design
The block diagram represents the areas of necessary input, from either the user or the scale data sent to the server in order to produce the desired output, or purpose of the application.
Businesses will start by configuring the inventory of liquors they have in stock. Once that is in place, they can add scales to their infrastructure and configure the inventory to them. The Arduino scale relies on this configuration to POST data to the server in order to comply with the API logic and ensure the business, scale, and liquor associations are compliant. This results in a pool of data that has to be assembled into proper lists and objects and converted into analytics for the business to view and manage your business strategies around.

<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/_Application%20Design/Block%20Diagram.png"/></p>

### Use Case Diagram Design
The use case diagram demonstrates the actors interacting with different services and the scale utility. The bartender will passively be activating the scale, registering data while they use drinks. Managers will manage the inventory configurations from the reporting application. Finally, vendors will have access to the array of business metrics and any quality of assurance alerts thrown from any of the businesses within the reporting application as well.
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/_Application%20Design/Use%20Case.png"/></p>

Back to [Table of Contents](#Table-of-Contents)

