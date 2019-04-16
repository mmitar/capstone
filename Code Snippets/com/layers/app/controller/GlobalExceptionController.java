package com.layers.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.layers.app.exceptions.DatabaseException;
import com.layers.app.exceptions.LiquorErrorException;
import com.layers.app.model.User;
import com.layers.app.model.alerts.Error;
import com.layers.app.model.alerts.Warn;
import com.layers.app.util.ILogger;

/**
 * Global exception controller that catches all unchecked exceptions. Each exception handler logs the exception and
 * 		the user it was executed by. A proper message is assembled for the user to get a gist of the issue.
 * 
 * @see ILogger
 */
@ControllerAdvice
@SessionAttributes({"userToken", "locationToken", "requestToken"})
public class GlobalExceptionController 
{
	@Autowired
	private ILogger logger;
	
	/**
	 * <p>500 Status Errors</p>
	 * <p>Catches DatabaseExceptions and assembles an error page to report internal server side issues.
	 * 		During development, devs may want to print out the proper message to the view to
	 * 		get details on the issue, otherwise they'll be met with a generic response.</p>
	 * <p>-- example of using exception message:</p>
	 * <pre>ModelAndView mv = new ModelAndView("error");
	 * mv.addObject("error", e.getMessage());
	 *  // mv.addObject("error", "Other text");
	 * return mv;</pre>
	 * 
	 * @param e The throwable exception clause to extract details on the exception thrown
	 * @param model Used to get user session model map
	 * @return error.jsp
	 */
	@ExceptionHandler(DatabaseException.class)
	public ModelAndView handleDatabaseException(Exception e, ModelMap model) 
	{
		logger.error((User)model.get("userToken"), "Status 500 - Internal Server Error", 
				e.getMessage(), false);
		
		// Set the view for the error page
		ModelAndView mv = new ModelAndView("error");
		
		// Set the status, a message, and the image relative to the scenario
		mv.addObject("status", 500);
		mv.addObject("alert", new Error("We're putting out the fire right away!"));
		mv.addObject("error", e.getMessage());
		mv.addObject("error", "Server error recorded and will be addressed soon. <br/><br/> YOU WILL BE CONTACTED!");
		mv.addObject("image","https://trinutrition2.files.wordpress.com/2015/05/inflammation.jpg");
		
		// Return MAV with error
		return mv;
	}
	
	/**
	 * <p>404 Status Errors</p>
	 * <p>Garbage collects bad URI requests and navigates them to an error page to reflect the issue.</p>
	 * 
	 * @param model Session model map
	 * @param request Gives the controller access to the bad URI request to identify
	 * @return 404 Status error page.
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNoHandlerFoundException(ModelMap model, HttpServletRequest request) 
	{
		logger.error((User)model.get("userToken"), "Status 404 - Page Not Found",
				"Searched page does not exist: " + request.getRequestURI(), true);
		
		// Set the view for the error page
		ModelAndView mv = new ModelAndView("error");
		
		// Set the status, a message, and the image relative to the scenario
		mv.addObject("status", 404);
		mv.addObject("alert", new Warn("Page not found."));
		mv.addObject("error", "Sorry, we couldn't find the secret sauce!");
		mv.addObject("image","https://vignette.wikia.nocookie.net/spongebob/images/d/d4/Krabby_Patty_transparentpng.png/revision/latest?cb=20170310181007");
		
		// Return MAV with error
		return mv;
    }
	
	/**
	 * Catches Data logic Exceptions from LiquorDAO. Currently inactive.
	 * 
	 * @param Exception e
	 * @return ModelAndView
	 */
	@ExceptionHandler(LiquorErrorException.class)
	public ModelAndView handleLiquorErrorException(RedirectAttributes redir, ModelMap model) 
	{
		logger.error((User)model.get("userToken"), "Liquor Error Exception - Data Logic",
				"Error performing last action. Requirement not met according to defined logic." , false);
		
		// Return MAV with the error
		redir.addFlashAttribute("alert", new Error("Error performing last action. Bug reported."));
		return new ModelAndView("redirect:/liquor/list");
	}
}