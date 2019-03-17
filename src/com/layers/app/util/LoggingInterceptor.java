package com.layers.app.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.layers.app.model.User;

public class LoggingInterceptor extends HandlerInterceptorAdapter 
{
	/**
	 * Dependency Injected LoggerFilter
	 */
	@Autowired
	private ILogger logger;
	
	/**
	 * Handler that is invoked before fulfilling the request. Used for security and filtering requests.
	 * Filters users based on their session status. Logs all activity and methods invoked.
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param Object handler
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception 
	{
		// Get user if they started a session
		User session = (User) request.getSession().getAttribute("userToken");
		
		// Checking if a session exists
		if(session != null)
		{
			logger.debug(request, "prehandle. " + session.toString());
			// Continue the action
			return true;
		}
		
		// Enable an open path for redirects to the login page
		else if(request.getRequestURI().contains(request.getContextPath() + "/user"))
		{
			logger.trace(request, "prehandle. Allowing login method action to be enabled.");
			// Continue the redirect
			return true;
		}
		
		// Redirecting user to the login 
		response.sendRedirect(request.getContextPath() +"/user/login");
		logger.trace(request, "prehandle. User Has Not Been Authenticated. Redirecting to "+request.getContextPath() +"/user/login");
		
		// Stops the action from being completed.
		return false;
		
	}
	
	/**
	 * Handler that is invoked after fulfilling the request. Used to log the success of an action.
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param Object handler
	 * @param ModelAndView modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception 
	{
		// Log the redirect was successful
		if(request.getRequestURI().equals(request.getContextPath() + "/login/user"))
		{
			logger.info(request, "posthandle. Unauthenticated user directed to login view.");
		}
		else 
		{
			logger.info(request, "posthandle. Action Completed.");
			System.out.println(request.getRequestURI() + "    " +request.getRequestURL());
			request.getSession().setAttribute("requestToken", request.getRequestURI());
		}
	}

	/**
	 * Handler invoked after an exception is thrown. Not fully completed.
	 * Will notify administrators of error or fatal log levels
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param Object handler
	 * @param Exception ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception 
	{
		if(ex != null)
		{
			logger.warn(request, "aftercompletion, exception: " + ex);
		}	
	}
		
}