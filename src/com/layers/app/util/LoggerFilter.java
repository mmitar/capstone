package com.layers.app.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used as a facade to log for SLF4J implementing Log4j. Properties are detailed in log4j.properties.
 * @author Matt & Joey
 *
 */
public class LoggerFilter implements ILogger
{
	private static Logger logger = LoggerFactory.getLogger(LoggerFilter.class);

	/**
	 * Trace Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 */
	@Override
	public void trace(HttpServletRequest request, String msg) 
	{
		logger.trace(formatRequest(request) + msg);
	}
	
	/**
	 * Debug Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 */
	@Override
	public void debug(HttpServletRequest request, String msg) 
	{
		logger.debug(formatRequest(request) + msg);
	}

	/**
	 * Info Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 */
	@Override
	public void info(HttpServletRequest request, String msg) 
	{
		logger.info(formatRequest(request) + msg);
	}
	
	/**
	 * Warn Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 * 
	 */
	@Override
	public void warn(HttpServletRequest request, String msg) 
	{
		logger.warn(formatRequest(request) + msg);
	}

	/**
	 * Error Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 * 
	 */
	@Override
	public void error(HttpServletRequest request, String msg) 
	{
		logger.error(formatRequest(request) + msg);
	}

	/**
	 * Fatal Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 * 
	 */
	@Override
	public void fatal(HttpServletRequest request, String msg) 
	{
		logger.error(formatRequest(request) + msg);
	}
	
	/**
	 * Formats the request uri path so that it is easier to read
	 * 
	 * @param HttpServletRequest request
	 * @return String
	 */
	private String formatRequest(HttpServletRequest request)
	{
		// Get the method type
		String method = request.getMethod();
		// If method is GET, append an extra space to match the character count for POST
		if(method.length() == 3)
		{
			method += " ";
		}
		
		// Split the URI into sections
		String[] uris = request.getRequestURI().split("/");
		String url = " NOPATH: ";
		
		// If array contains [catalina]/[contextpath]/[controller]/[method request]
		if(uris.length == 4) {
			// Only concatenate [controller] & [method request]
			url = uris[2] +"."+ uris[3] +"(): ";
		}
		
		// Concat the method to the url
		return "["+method+"]" + url;
	}
}
