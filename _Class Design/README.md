# [Class Design](https://github.com/mmitar/capstone/tree/master/_Class%20Design)

## Table of Contents
[Home Directory](https://github.com/mmitar/capstone)
1. [System Design](https://github.com/mmitar/capstone/tree/master/_System%20Design)
2. [Database Design](https://github.com/mmitar/capstone/tree/master/_Database%20Design)	
3. [Class Design](https://github.com/mmitar/capstone/tree/master/_Class%20Design)
	* [UML Class Diagram Design](#Class-Diagram)	
	* [Component Diagram Design](#Component-Diagram)
4. [API Design](https://github.com/mmitar/capstone/tree/master/_API%20Design)
5. [Application Design](https://github.com/mmitar/capstone/tree/master/_Application%20Design)
6. [Other Design Documentations](https://github.com/mmitar/capstone/tree/master/_Other)

### Class Diagram
The UML class diagram is the totality of the applications classes and models. Each class shows what class level fields or objects are being used. Services that are injected to the class via Context Dependency Injection will be @Autowired , its methods, parameters, return types, used exceptions, models, and who is using what. This diagram goes into great detail to also show the relationships between services and their respective interfaces, as well as any classes using services with arrows. Because our application using the N-Layer Architecture design pattern, we colored all the controllers and models in blue, the business services in green, data access objects in yellow, and utility classes in purple. Also, the request pipeline complies with that principle, controllers call business services for help, and business services call other services or their respective data access objects to connect to the database. Some models have protected visibility level fields because there are JUnit Testing models that extends them
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/_Class%20Design/Class%20Diagram.png"/></p>

### Component Diagram
The component diagram resembles the same design pattern philosophy demonstrated in the UML class diagram. The color conventions match, such that the `controllers` are blue, the `business services` are green, `data access objects` are yellow. However, this diagram does better to show the `N-Layer` design pattern by encapsulating the respective classes within a `<<SubSystem>>`. The diagram represents the interfaces the classes request the service from and what ports are available to use. While `controllers` can request the use of any `interface` from the Business Layer subsystem, those services only request the help from their respective Data Access Layer subsystem. Classes within the business layer subsystem will also request help from each other to accomplish the task as necessary. The Arduino embedded device is its only entity, interfacing via HTTP `POST` to the `RestBusinessService`. All Data Access Layer classes have a `JDBC` connection to the database.
<p align="center"><img src="https://github.com/mmitar/capstone/blob/master/_Class%20Design/Component.png"/></p>

Back to [Table of Contents](#Table-of-Contents)
