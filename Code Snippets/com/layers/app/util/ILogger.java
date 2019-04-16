package com.layers.app.util;

import com.layers.app.model.User;

/**
 * Contracts the logger utility service for dependency injection. Guarentees its functions are provided
 * 		and available during runtime.
 * 
 * @see LoggerFilter
 */
public interface ILogger 
{
	/**
	 * Trace Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 */
	public void trace(User user, String method, String message, boolean saveToDB);
	
	/**
	 * Debug Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 */
	public void debug(User user, String method, String message, boolean saveToDB);
	
	/**
	 * Info Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * @param string 
	 * @param handler 
	 * @param response 
	 * @param user 
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 */
	public void info(User user, String method, String message, boolean saveToDB);
	
	/**
	 * Warn Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 */
	public void warn(User user, String method, String message, boolean saveToDB);

	/**
	 * Error Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 */
	public void error(User user, String method, String message, boolean saveToDB);
	
	/**
	 * Fatal Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 */
	public void fatal(User user, String method, String message, boolean saveToDB);
}
