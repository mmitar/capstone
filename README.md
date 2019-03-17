## Nice to see you

[dreamstream website]
[Java documentation](https://mmitar.github.io/capstone/)


```java

public ModelAndView loginUser(@Valid @ModelAttribute("user")User user, 
			BindingResult validate, ModelMap model)
	{
		// Validate the form
		if(validate.hasErrors())
		{
			// If form is invalid, return to login view with existing input
			return new ModelAndView("login", "user", user);
		}
		
		try 
		{
			// Call UserBusinessService.findBy() to see if user exists
			User verifiedUser = userService.authenticateUser(user);
			//Session Variable of User
			model.addAttribute("userToken", verifiedUser);
			
			if(verifiedUser.getPermission() == 797979)
			{
				return new ModelAndView("redirect:/location/listLocation");
			}
			
			// Forwards the user to the mage page
			return new ModelAndView("redirect:/liquor/listLiquor");
		}
		// If the credentials do not match an existing user
		catch(UserNotFoundException e)
		{
			// Return User back to the login view w/ Error Message
			ModelAndView mv = new ModelAndView("login");
			mv.addObject("error", "Username or Password is incorrect. Fields are Case-Sensitive.");
			return mv;
		}
	}
  
  ```
