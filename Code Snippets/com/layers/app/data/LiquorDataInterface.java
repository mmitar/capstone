package com.layers.app.data;

import java.util.List;

import com.layers.app.exceptions.LiquorErrorException;
import com.layers.app.model.Liquor;

/**
 * Contracts the respective data access service for dependency injection. Guarentees its functions are provided
 * 		and available during runtime.
 * 
 * @see LiquorDataService
 */
public interface LiquorDataInterface {
	
	/**
	 * READ method
	 * returns all liquor in a list
	 * 
	 * @param none
	 * @return 
	 */ 
	public List<Liquor> findAll(String locationId);
	
	/**
	 * <p>READ method</p>
	 * <p>Returns a single current instance of liquor from the database based on the liquor code ID
	 * 		 and the location ID</p>
	 * 
	 * @param liquorCode Requested liquor ID
	 * @param locationId Requested Business location ID
	 * @return Current saved instance of the database based on the arguments
	 */
	public Liquor findBy(String liquorCode, String locationId);
	
	/**
	 * <p>UPDATE method</p>
	 * <p>Adds liquor in database based off user input</p>
	 * 
	 * @param liquor {@link Liquor} containing all the modified liquor values including the location Id
	 * @return Result of the request
	 * @throws LiquorErrorException Verifies that the update took into affect
	 * @see com.layers.app.data.LiquorDataService#update(Liquor) LiquorDataService.update(Liquor)
	 */
	public boolean update(Liquor liquor) throws LiquorErrorException;
	
	/**
	 * <p>CREATE method</p>
	 * <p>Edits liquor in database based off user input</p>
	 * 
	 * @param Liquor 
	 * @return boolean
	 */
	public boolean create(Liquor liquor);
	
	/**
	 * <p>DELETE method</p>
	 * </p>Deletes liquor in database based off user input</p>
	 * 
	 * @param Liquor 
	 * @return boolean
	 */
	public boolean delete(Liquor liquor);
	
	/**
	 * </p>returns all available scales</p>
	 * 
	 * @param String for location ID 
	 * @return List<Liquor>
	 */	
	public List<Liquor> findUnregisteredScales(String locationId);

}
