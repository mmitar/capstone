package com.layers.app.business;

import java.util.List;

import com.layers.app.exceptions.LiquorFoundException;
import com.layers.app.exceptions.LiquorNotFoundException;
import com.layers.app.exceptions.ScaleNotFoundException;
import com.layers.app.model.Liquor;

/**
 * Contracts the respective business service for dependency injection. Guarentees its functions are provided
 * 		and the business logic checked exceptions are implemented.
 * 
 * @see LiquorBusinessService
 */
public interface LiquorBusinessInterface 
{
	/**
	 * 
	 * @param locationId User session location identification value 
	 * @return The list of Liquor models
	 * @throws LiquorNotFoundException
	 */
	public List<Liquor> findAll(String locationId) throws LiquorNotFoundException;
	
	/**
	 * returns a single instance of liquor
	 * @param Liquor - 
	 * @param User - invoking request
	 * @return Liquor
	 */
	public Liquor getOneLiquor(String liquorCode, String locationId) throws LiquorNotFoundException;
	/**
	 * Adds liquor in database based off user input
	 * @param Liquor - 
	 * @param User - invoked request
	 * @return boolean
	 */
	public boolean addLiquor(Liquor liquor, String locationId) throws LiquorFoundException;
	/**
	 * edits liquor in database based off user input
	 * @param Liquor
	 * @param User - invoking request
	 * @return boolean
	 */
	public boolean editLiquor(Liquor liquor, String locationId) throws LiquorNotFoundException;
	/**
	 * deletes liquor in database based off user input
	 * @param Liquor 
	 * @param User - invoking request
	 * @return boolean
	 * @throws ScaleNotFoundException 
	 */
	public boolean deleteLiquor(String liquorCode, String locationId) throws LiquorNotFoundException, ScaleNotFoundException;

	/**
	 *	returns all unregisteredLiquors
	 * @param String of locationId
	 * @return List<Liquors>
	 * @throws LiquorNotFoundException
	 */
	public List<Liquor> getUnRegisteredLiquors(String locationId) throws LiquorNotFoundException;

}
