package com.layers.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.layers.app.business.UserBusinessInterface;
import com.layers.app.exceptions.UserNotFoundException;
import com.layers.app.model.User;
import com.layers.app.model.alerts.Alert;
import com.layers.app.model.alerts.Error;
import com.layers.app.model.alerts.Info;
import com.layers.app.util.ILogger;

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
	
	@SuppressWarnings("unused")
	@Autowired
	private ILogger logger;
	
	/**
	 * Navigates the user via URI to the login view with an empty User model
	 * 
	 * @param ModelMap model
	 * @return ModelAndView loginUser
	 */
	@GetMapping({"/login","/"})
	public ModelAndView displayForm(ModelMap model, @ModelAttribute("alert") Alert alert)
	{
		// checks if a session was already established
		if(model.containsAttribute("userToken"))
		{
			// redirect to the root directory for permission directing
			return new ModelAndView("redirect:/");
		}

		// Navigate the user to the login view with a blank form
		ModelAndView mv = new ModelAndView("login", "user", new User());
		mv.addObject("alert", alert);
		return mv;
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
	@PostMapping("/login")
	public ModelAndView loginUser(@Valid @ModelAttribute("user")User user, 
			BindingResult validate, ModelMap model, RedirectAttributes redir)
	{
		// Validate the form
		if(validate.hasErrors())
		{
			logger.debug(user, "User Service - Login" , "User form POST unsuccessful with invalid field values: " + validate.toString(), false);
			// If form is invalid, return to login view with existing input
			return new ModelAndView("login", "user", user);
		}
		
		try 
		{
			// Call UserBusinessService.findBy() to see if user exists
			User verifiedUser = userService.authenticateUser(user);
			//Session Variable of User
			model.addAttribute("userToken", verifiedUser);
			
			logger.info(verifiedUser, "User Service - Login", "User successfuly logged in from a verified account.", false);
			
			// Redirect to the root directory for permission routing
			return new ModelAndView("redirect:/");
		}
		// If the credentials do not match an existing user
		catch(UserNotFoundException e)
		{
			logger.warn(user, "User Service - Login", "User unsuccessfully logged in with data: " + user.toString() , false);

			redir.addFlashAttribute("alert", new Error("Username or Password is incorrect."));
			return new ModelAndView("redirect:/user/login");
		}
	}
	
	/**
	 * Log out the user and destroy the session
	 * 
	 * @param ModelMap model
	 * @param SessionStatus status
	 * @return ModelAndView
	 */
	@GetMapping("/logout")
	public ModelAndView logoutUser(ModelMap model, SessionStatus status,
			RedirectAttributes redir)
	{
		// Check if a session is active
		if(model.containsAttribute("userToken"))
		{
			logger.info((User)model.get("userToken"), "User Service - Logout", "User successfully logged out from a verified account.", false);
			// clear the session and remove the session of the hashmap
			status.setComplete();
			model.remove("userToken");
			
			redir.addFlashAttribute("alert", new Info("Log out successful."));
		}
		
		// Redirect the user to the login page
		return new ModelAndView("redirect:/user/login");
	}
}