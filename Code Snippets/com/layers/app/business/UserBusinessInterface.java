package com.layers.app.business;

import com.layers.app.exceptions.UserErrorException;
import com.layers.app.exceptions.UserFoundException;
import com.layers.app.exceptions.UserNotFoundException;
import com.layers.app.model.User;

/**
 * Contracts the respective business service for dependency injection. Guarentees its functions are provided
 * 		and the business logic checked exceptions are implemented.
 * 
 * @see UserBusinessService
 */
public interface UserBusinessInterface 
{
	/**
	 * <p>Validation method that forwards the request to the DAO to find the instance of the user.</p>
	 * 
	 * @param user Credentials an unauthenticated user submitted to be verified 
	 * @return Current instance of the user found from the database
	 * @throws UserNotFoundException The credentials provided were insufficient to find an exact match
	 * @see com.layers.app.data.UserDataService#findBy(User) UserDataService.findBy(User)
	 */
	public User authenticateUser(User user) throws UserNotFoundException;
	
	/**
	 * <p>Creation Method that forwards the request to the DAO to create a new User.</p>
	 * 
	 * @param user Contains new {@link User} data to be validated 
	 * @return Result of the execution
	 * @throws UserFoundException User already exists in the database and credentials cannot be replicated
	 * @throws UserErrorException User account failed to be created
	 * @see com.layers.app.data.UserDataService#findIfExists(User) UserDataService.findIfExists(User)
	 * @see com.layers.app.data.UserDataService#create(User) UserDataService.create(User)
	 */
	public boolean addUser(User user) throws UserFoundException, UserErrorException;
}
