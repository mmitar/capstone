package com.layers.app.business;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.layers.app.data.UserDataInterface;
import com.layers.app.data.UserDataService;
import com.layers.app.exceptions.UserErrorException;
import com.layers.app.exceptions.UserFoundException;
import com.layers.app.exceptions.UserNotFoundException;
import com.layers.app.model.User;


/**
 * @author Joey & Matt
 *
 */
public class UserBusinessService implements UserBusinessInterface 
{
	/**
	 * Dependency Injected
	 */
	@Autowired
	private UserDataInterface userDAO;
	
	/**
	 * Junit Testing Default and Non-Default Constructors
	 */
	public UserBusinessService() {}
	public UserBusinessService(DataSource testdatasource) 
	{
		// Overwrite the userDAO with the testdatasource
		this.userDAO = new UserDataService(testdatasource);
	}
	
	/**
	 * Validation Method that forwards the request to the DAO to find the User.
	 * 
	 * @throws UserNotFoundException
	 * @param User user
	 * @return User
	 */
	public User authenticateUser(User user) throws UserNotFoundException
	{
		// Call DAO to find user from the database. Matches must be exact.
		user = userDAO.findBy(user);
		if(user == null)
		{
			// If user does not exist, throw exception
			throw new UserNotFoundException();
		}
	
		return user;
	}
	
	/**
	 * Creation Method that forwards the request to the DAO to create a new User.
	 * 
	 * @throws UserFoundException
	 * @param User user
	 * @return boolean
	 */
	public boolean addUser(User user) throws UserFoundException, UserErrorException
	{
		// Call DAO to find user in the database.
		boolean exists = userDAO.findIfExists(user);
		if(exists == true)
		{
			// If user exists, throw exception
			throw new UserFoundException();
		}
		
		// Call DAO to add a new user
		boolean result = userDAO.create(user);
		if(result == false)
		{
			// If error creating user, throw exception
			throw new UserErrorException();
		}
		
		return result;
	}
}
