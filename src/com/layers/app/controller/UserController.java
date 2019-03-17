package com.layers.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.layers.app.business.UserBusinessInterface;
import com.layers.app.exceptions.UserNotFoundException;
import com.layers.app.model.User;

@Controller
@RequestMapping("/user")
@SessionAttributes({"userToken", "locationToken", "requestToken"})
public class UserController
{
	/**
	 * Dependency Injected
	 */
	@Autowired
	private UserBusinessInterface userService;
	
	/**
	 * Navigates the user via URI to the login view with an empty User model
	 * 
	 * @param ModelMap model
	 * @return ModelAndView loginUser
	 */
	@RequestMapping(path="/login", method=RequestMethod.GET)
	public ModelAndView displayForm(ModelMap model)
	{
		// checks if a session was already established
		if(model.containsAttribute("userToken"))
		{
			// forward to the user to the main page
			return new ModelAndView("redirect:/liquor/listLiquor");
		}
		// Navigate the user to the login view with a blank form
		return new ModelAndView("login", "user", new User());
	}
	
	/**
	 * Validates the form post user model for validation errors. if error: nav back to loginUser
	 * If successful, nav forward to dashboard.
	 * 
	 * @param User user
	 * @param BindingResult result
	 * @return ModelAndView loginUser, user
	 * @return ModelAndView dashboard, user
	 */
	@RequestMapping(path="/validateUser", method=RequestMethod.POST)
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
	
	/**
	 * Log out the user and destroy the session
	 * 
	 * @param ModelMap model
	 * @param SessionStatus status
	 * @return ModelAndView
	 */
	@RequestMapping(path="/logout", method=RequestMethod.GET)
	public ModelAndView logoutUser(ModelMap model, SessionStatus status)
	{
		// Check if a session is active
		if(model.containsAttribute("userToken"))
		{
			// clear the session and remove the session of the hashmap
			status.setComplete();
			model.remove("userToken");
		}
		
		// Return the user to the login page
		return new ModelAndView("login", "user", new User());
	}
}
