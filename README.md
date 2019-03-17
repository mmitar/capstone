# Dream Stream Documentation & Architecture

AWS website 
### [dreamstream website](http://thedreamstream.us-east-2.elasticbeanstalk.com)
View all the Javadocumentation [here](https://mmitar.github.io/capstone/)

#### try-block
Call UserBusinessService.findBy() to see if user exists
```java
try {
	User verifiedUser = userService.authenticateUser(user);
}
```
#### throw-exception
```java
if(userDAO.find(user) == null) {
    throw new UserNotFoundException();
}
```

#### catch-block
```java
catch(UserNotFoundException e) {
	ModelAndView mv = new ModelAndView("login");
	mv.addObject("error", "Username or Password is incorrect.");
	return mv;
}
```
  
### Sequence Diagram
![Arduino Action Reporting Sequence Diagram](https://raw.githubusercontent.com/mmitar/capstone/master/docs/images/Arduino%20Sequence%20Diagram.png)

### Block Diagram
![Block Diagram](https://github.com/mmitar/capstone/blob/master/docs/images/Block%20Diagram.png?raw=true)

### Class UML Diagrams
![Class Diagrams](https://github.com/mmitar/capstone/blob/master/docs/images/Class%20Diagrams.png?raw=true)

### Component Diagram
![Component Diagram](https://github.com/mmitar/capstone/blob/master/docs/images/Component%20Diagram.png?raw=true)
 
### ER Diagram
![ER Diagram](https://raw.githubusercontent.com/mmitar/capstone/master/docs/images/ER%20Diagram.JPG?raw=true)

### Logical Design
![Logical Design](https://github.com/mmitar/capstone/blob/master/docs/images/Logical%20Design.JPG?raw=true)

### Manifest Deployment
![Manifest Deployment](https://raw.githubusercontent.com/mmitar/capstone/master/docs/images/Manifest%20Deployment.JPG?raw=true)

### Physical Design
![Physical Design](https://raw.githubusercontent.com/mmitar/capstone/master/docs/images/Phyisical%20Design.JPG?raw=true)

### Sitemap
![Sitemap](https://raw.githubusercontent.com/mmitar/capstone/master/docs/images/Sitemap.png?raw=true)

### Wireframes
![Wireframes](https://raw.githubusercontent.com/mmitar/capstone/master/docs/images/Wireframes.png?raw=true)

### Deployment Diagram
![Deployment Diagram](https://raw.githubusercontent.com/mmitar/capstone/master/docs/images/deployment%20diagram.JPG?raw=true)

