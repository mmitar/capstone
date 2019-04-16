package com.layers.app.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.layers.app.data.UserDataInterface;
import com.layers.app.exceptions.UserErrorException;
import com.layers.app.exceptions.UserFoundException;
import com.layers.app.exceptions.UserNotFoundException;
import com.layers.app.model.User;

/**
 * User business service handles requests specific to the users account. Primarily works to validate users on login and 
 * 		validate the user is in the system.
 * 
 * <p>Business services serve has a facade to requesters, concealing the processes while enforcing business logic via checked exception rules
 * 		 and verify the request has been processed appropriately. Business services partition data access logic responsibilities to
 * 		the respective data access object or other services to assemble the data.</p>
 * <p>-- example of business logic exception:</p>
 * <pre> {@code
 * Object object = serviceDAO.findObject();
 * if(object == null) 
 * {
 * 	throw new ObjectNotFoundException();
 * }</pre>
 * @see UserDataInterface
 */
public class UserBusinessService implements UserBusinessInterface 
{
	/**
	 * Dependency Injected
	 */
	@Autowired
	private UserDataInterface userDAO;
	
	/**
	 * {@inheritDoc}
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
		// Return the result
		return user;
	}
	
	/**
	 * {@inheritDoc}
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
		// Return the result
		return result;
	}
}
