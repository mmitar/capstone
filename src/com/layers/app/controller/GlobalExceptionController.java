package com.layers.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.layers.app.exceptions.DatabaseException;
import com.layers.app.exceptions.LiquorException;

/**
 * Global Exception Handler Controller that catches all uncaught exceptions
 */
@ControllerAdvice
@SessionAttributes({"userToken", "locationToken", "requestToken"})
public class GlobalExceptionController 
{
	/**
	 * 500 Status Errors
	 * Catches DatabaseExceptions and assembles an error page for Server Side Stuff.
	 * 
	 * @param Exception e
	 * @return ModelAndView
	 */
	@ExceptionHandler(DatabaseException.class)
	public ModelAndView handleDatabaseException(Exception e) 
	{
		// Set the view for the error page
		ModelAndView mv = new ModelAndView("error");
		
		// Set the status, a message, and the image relative to the scenario
		mv.addObject("status", 500);
		mv.addObject("error", e.toString());
//		mv.addObject("error", "We're putting out the fire right away!");
		mv.addObject("image","https://trinutrition2.files.wordpress.com/2015/05/inflammation.jpg");
		
		// Return MAV with error
		return mv;
	}
	
	/**
	 * 404 Status Errors
	 * When a Page that is being search for does not exist
	 * 
	 * 
	 * @return ModelAndView
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNoHandlerFoundException(Exception e) 
	{
		// Set the view for the error page
		ModelAndView mv = new ModelAndView("error");
		
		// Set the status, a message, and the image relative to the scenario
		mv.addObject("status", 404);
//		mv.addObject("error", e.toString());
		mv.addObject("error", "Sorry, we couldn't find the secret sauce!");
		mv.addObject("image","https://vignette.wikia.nocookie.net/spongebob/images/d/d4/Krabby_Patty_transparentpng.png/revision/latest?cb=20170310181007");
		
		// Return MAV with error
		return mv;
    }
	
	/**
	 * Catches LiquorExceptions and assembles an error page.
	 * 
	 * @param Exception e
	 * @return ModelAndView
	 */
	@ExceptionHandler(LiquorException.class)
	public ModelAndView handleLiquorException(Exception e) 
	{
		// Return MAV with the error
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("error", e.toString());
		return mv;
	}
}