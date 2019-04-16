package com.layers.app.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.layers.app.data.LiquorDataInterface;
import com.layers.app.exceptions.LiquorFoundException;
import com.layers.app.exceptions.LiquorNotFoundException;
import com.layers.app.exceptions.ScaleNotFoundException;
import com.layers.app.model.Liquor;


/**
 * @author Joey & Matt
 *
 */
public class LiquorBusinessService implements LiquorBusinessInterface 
{
	/**
	 * Dependency Injected LiquorDataService
	 */
	@Autowired
	private LiquorDataInterface liquorDAO;
	
	@Autowired
	private ScaleBusinessInterface scaleService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Liquor> findAll(String locationId) throws LiquorNotFoundException
	{	
		// Calls the DAO to request all the liquor in a List
		List<Liquor> liquors = liquorDAO.findAll(locationId);
		
		// Check if the list is empty
		if(liquors.isEmpty())
		{
			throw new LiquorNotFoundException();
		}
		// Return the list of liquors
		return liquors;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Liquor getOneLiquor(String liquorCode, String locationId) throws LiquorNotFoundException 
	{
		// Check if the liquor exists in the DB
		Liquor exists = liquorDAO.findBy(liquorCode, locationId);
		
		// If liquor does not exist throw an exception
		if(exists == null)
		{
			// If liquor does not exist, throw exception
			throw new LiquorNotFoundException();
		}
		
		// Return the liquor 
		return exists;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addLiquor(Liquor liquor, String locationId) throws LiquorFoundException
	{
		// Check if the liquor exists in the DB
		Liquor exists = liquorDAO.findBy(liquor.getLiquorCode(), locationId);
		
		// if liquor exists, throws exception
		if(exists != null)
		{
			// If liquor does not exist, throw exception
			throw new LiquorFoundException();
		}
		
		// Set the session's location Id to the liquor
		liquor.setLocationId(locationId);
		
		// add the liquor to the DB
		boolean result = liquorDAO.create(liquor);
		
		// return the result
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean editLiquor(Liquor liquor, String locationId) throws LiquorNotFoundException 
	{
		// Check if the liquor exists in the DB
		Liquor exists = liquorDAO.findBy(liquor.getLiquorCode(), locationId);
		
		// if liquor does not throw exception
		if(exists == null)
		{
			// If liquor does not exist, throw exception
			throw new LiquorNotFoundException();
		}
		
		// Set the session's location Id to the liquor
		liquor.setLocationId(locationId);
		
		// update an existing liquor listing
		boolean result = liquorDAO.update(liquor);	
		// return the result
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteLiquor(String liquorCode, String locationId) throws LiquorNotFoundException, ScaleNotFoundException
	{
		// Check if the liquor exists in the DB
		Liquor liquor = liquorDAO.findBy(liquorCode, locationId);
		
		// if liquor does not exist throw exception
		if(liquor == null)
		{
			// If liquor does not exist, throw exception
			throw new LiquorNotFoundException();
		}
 
		boolean result = false;
		
		try 
		{
			// Try to remove the liquor from any scales, throws ScaleNotFoundException
			scaleService.unbindLiquorByLiquorCode(liquorCode, locationId);
			
			// delete an existing liquor from the DB
			result = liquorDAO.delete(liquor);
		}
		// If not scale found, finish the request and delete the liquor
		catch(ScaleNotFoundException e)
		{
			// delete an existing liquor from the DB
			liquorDAO.delete(liquor);
			// throw exception for notification
			throw e;
		}
		
		// Return the result
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Liquor> getUnRegisteredLiquors(String locationId) throws LiquorNotFoundException
	{
		List<Liquor> unregisteredLiquors = liquorDAO.findUnregisteredScales(locationId);
		
		if(unregisteredLiquors.isEmpty())
		{
			throw new LiquorNotFoundException();
		}
		
		return unregisteredLiquors;
	}
}
