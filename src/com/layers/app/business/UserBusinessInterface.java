package com.layers.app.business;

import com.layers.app.exceptions.UserErrorException;
import com.layers.app.exceptions.UserFoundException;
import com.layers.app.exceptions.UserNotFoundException;
import com.layers.app.model.User;


/**
 * @author Joey & Matt
 *
 */
public interface UserBusinessInterface {
	/**
	 * Validation Method that forwards the request to the DAO to find the User.
	 * 
	 * @throws UserNotFoundException
	 * @param User user
	 * @return User
	 */
	public User authenticateUser(User user) throws UserNotFoundException;
	
	/**
	 * Creation Method that forwards the request to the DAO to create a new User.
	 * 
	 * @throws UserFoundException, UserErrorException
	 * @param User user
	 * @return boolean
	 */
	public boolean addUser(User user) throws UserFoundException, UserErrorException;

}
