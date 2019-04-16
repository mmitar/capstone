package com.layers.app.data;

import com.layers.app.exceptions.DatabaseException;
import com.layers.app.model.User;

/**
 * Contracts the respective data access service for dependency injection. Guarentees its functions are provided
 * 		and available during runtime.
 * 
 * @see UserDataService
 */
public interface UserDataInterface {
	
	/**
	 * READ Method
	 * Validation Login query checks if username exists in the database, case sensitive.
	 * 
	 * @param User user
	 * @return User user || null
	 * @throws DatabaseException
	 */
	User findBy(User user);
	
	/**
	 * CREATE Method
	 * Adds new user to DB << If using create(), findBy() first >>
	 * 
	 * @param User user
	 * @return boolean
	 * @throws DatabaseException
	 */
	boolean create(User user);
	
	/**
	 * READ Method
	 * Validation Registration query checks if username exists in the database, case insensitive.
	 * 
	 * @param User user
	 * @return boolean
	 * @throws DatabaseException
	 */
	boolean findIfExists(User user);
}
