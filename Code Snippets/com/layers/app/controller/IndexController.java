package com.layers.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.layers.app.model.User;

/**
 * <p>Used to reroute users based on a users current permission value. If user session is not recognized they
 * 		are navigated to the login page, else they are navigated to their respective views based on permission.</p>
 * <p>The {@link LoggerInterceptor} also plays a role in monitoring requests and permission levels.
 */
@Controller
@SessionAttributes({"userToken", "locationToken", "requestToken"})
public class IndexController
{
	/**
	 * Root index controller to redirect the user whether they have an active session.
	 * Used to avoid sending a logged in user to the login page. Also wont throw a 404.
	 * 
	 * @param model Session of the 
	 * @return ModelAndView loginUser
	 */
	@GetMapping("/")
	public ModelAndView rootDirectory(ModelMap model)
	{
		// check if the user has a session. Enable new view
		if(model.containsAttribute("userToken"))
		{
			User user = (User) model.get("userToken");
			
			// Verify if the user's permission is vendor role
			if(user.getPermission() == 797979)
			{
				return new ModelAndView("redirect:/location/listLocation");
			}
			
			// Verify if the user's permission is manager / employee role
			if(user.getPermission() == 131313 || user.getPermission() == 464646)
			{
				return new ModelAndView("redirect:/liquor/list");
			}
		}
		
		// session does not exist, redirect to the login page
		return new ModelAndView("redirect:/user/login");
	}
}
