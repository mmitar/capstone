package com.layers.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({"userToken", "locationToken", "requestToken"})
public class IndexController
{
	/**
	 * Root index controller to redirect the user whether they have an active session.
	 * Used to avoid sending a logged in user to the login page. Also wont throw a 404.
	 * 
	 * @param ModelMap model
	 * @return ModelAndView loginUser
	 */
	@GetMapping("/")
	public ModelAndView rootDirectory(ModelMap model)
	{
		// check if the user has a session
		if(model.containsAttribute("userToken"))
		{
			// session exists, forward to main page
			return new ModelAndView("redirect:/liquor/listLiquor");
		}
		
		// session does not exist, redirect to the login page
		return new ModelAndView("redirect:/user/login");
	}
}
