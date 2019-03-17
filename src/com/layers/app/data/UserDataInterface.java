package com.layers.app.data;

import java.util.List;

import com.layers.app.model.User;

/**
 * 
 * User data Interface(Only used for login)
 * @author Joey & Matt
 * 
 */
public interface UserDataInterface {
	
	/**
	 *  used to find user in database at login
	 * @param user
	 * @return user
	 */
	User findBy(User user);
	
	/**
	 *  create method registration
	 * @param user
	 * @return boolean
	 */
	boolean create(User user);
	
	/**
	 *  used to search database when someone tries to register to prevent duplicate usernames
	 * @param user
	 * @return boolean
	 */
	boolean findIfExists(User user);
	
	/**
	 *  Updates user based off user input
	 * @param user
	 * @return boolean
	 */
	boolean update(User t);
	
	/**
	 *  delete user based off user input
	 * @param user
	 * @return boolean
	 */
	boolean delete(User t);
	
	/**
	 *  find user by searched ID
	 * @param id
	 * @return User
	 */
	User findById(int id);
	
	/**
	 *  returns all users in a list
	 * @param 
	 * @return List<User>
	 */
	List<User> findAll();
	
	/**
	 *  returns all users in a list
	 * @param User
	 * @return List<User>
	 */
	List<User> findAll(User user);
	

}
