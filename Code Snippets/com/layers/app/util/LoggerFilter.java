package com.layers.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.layers.app.business.ActivityBusinessInterface;
import com.layers.app.model.Activity;
import com.layers.app.model.User;

/**
 * Used as a facade to log for SLF4J implementing Log4j. Properties are detailed in log4j.properties.
 * @author Matt & Joey
 * 
 * @see ActivityBusinessService
 * @see Logger
 */
public class LoggerFilter implements ILogger
{
	@Autowired
	private ActivityBusinessInterface activityService;
	
	private static Logger logger = LoggerFactory.getLogger(LoggerFilter.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void trace(User user, String method, String message, boolean saveToDB) 
	{	
		logger.trace(reportActivity("trace", user, method, message, saveToDB));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void debug(User user, String method, String message, boolean saveToDB) 
	{
		logger.debug(reportActivity("debug", user, method, message, saveToDB));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void info(User user, String method, String message, boolean saveToDB) 
	{
		logger.info(reportActivity("info", user, method, message, saveToDB));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void warn(User user, String method, String message, boolean saveToDB) 
	{
		logger.warn(reportActivity("warn", user, method, message, saveToDB));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void error(User user, String method, String message, boolean saveToDB) 
	{
		logger.error(reportActivity("error", user, method, message, saveToDB));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fatal(User user, String method, String message, boolean saveToDB) 
	{
		logger.error(reportActivity("fatal", user, method, message, saveToDB));
	}
	
	private String reportActivity(String level, User user, String method, String message, boolean saveToDB)
	{
		Activity log = new Activity();
		
		if(user == null || !(user instanceof User))
		{
			log.setLocationId("NO LOCATION");
			log.setLogUser("NO USER");
		}
		else 
		{
			log.setLocationId(user.getLocationId());
			log.setLogUser(user.getUsername());
		}
		
		log.setLogLevel(level.toUpperCase());
		log.setLogMethod(method);
		log.setLogMessage(message);
		
		// Saving log to the database will allow users of that location to view the report
		if(saveToDB == true)
		{
			// Call activity to save the log in the db
			activityService.saveActivityLog(log);
		}
		
		// Return the toLog string converter for the format of the logged message
		return log.toLog();
	}
}
