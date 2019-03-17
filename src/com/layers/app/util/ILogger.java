package com.layers.app.util;

import javax.servlet.http.HttpServletRequest;

public interface ILogger 
{
	/**
	 * Trace Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 */
	public void trace(HttpServletRequest request, String msg);
	
	/**
	 * Debug Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 */
	public void debug(HttpServletRequest request, String msg);
	
	/**
	 * Info Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 */
	public void info(HttpServletRequest request, String msg);
	
	/**
	 * Warn Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 */
	public void warn(HttpServletRequest request, String msg);

	/**
	 * Error Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 */
	public void error(HttpServletRequest request, String msg);
	
	/**
	 * Fatal Log Level
	 * Takes the servlet request to document activity plus a relative message
	 * 
	 * @param HttpServletRequest request
	 * @param String msg
	 */
	public void fatal(HttpServletRequest request, String msg);
}
