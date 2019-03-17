package test.java.services;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.layers.app.business.LiquorBusinessInterface;
import com.layers.app.business.LiquorBusinessService;
import com.layers.app.exceptions.LiquorFoundException;
import com.layers.app.exceptions.LiquorNotFoundException;
import com.layers.app.model.Liquor;

	
/**
 * Accesses app Liquor functions to test business and data logic. 
 * 
 * @Test - designed to emulate its respective controller method by testing against exceptions.
 * @FixMethodOrder - Sets the tests to run in the alphabetical order. This is critical to the logic the tests run in order of.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LiquorServiceTest 
{
	// Used to validate every tests performance
	private boolean success = false;
	
	// A common liquor object utilized throughout the test class
	private static Liquor liquor = null;
		
	// Setter Injects the test data source for database connections to the test schema
    private LiquorBusinessInterface service = new LiquorBusinessService(TestUtilityFactory.getDataSource());
	
    /**
     * Before the class executes the tests, assemble to proper testing environemnt.
     */
	@BeforeClass
	public static void setUpBeforeClass()
	{
		// call the factory to create the required data tables
		TestUtilityFactory.createTables("liquors");
		
		// Initialize the common liquor model and set feild values
		liquor = new Liquor();
		liquor.setLocationId("test");
		liquor.setLiquorCode("test");
		liquor.setBrandName("test");
		liquor.setAlcoholType("test");
		liquor.setAlertLevel(1);
		liquor.setLiquidVolume(1);
		liquor.setOverflow(1);
	}

	/**
	 * After the class executes the tests, disassemble the requested environment.
	 */
	@AfterClass
	public static void tearDownAfterClass()
	{
		// call the factory to remove the data tables requested
		TestUtilityFactory.dropTables();
	}
	
	/**
	 * Liquor Model Attribute Validation Test
	 * Tests all fields to be set outside of their validated bounds.
	 */
	@Test
	public void stage000_modelAttributeValidation()
	{
		// Initialize a Validator Factory
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        // Create Liquor Model. Set each field outside of its permitted bounds
		Liquor liquor = new Liquor();
		liquor.setLocationId(TestUtilityFactory.stringGenerator(100));
		liquor.setLiquorCode(TestUtilityFactory.stringGenerator(100));
		liquor.setBrandName(TestUtilityFactory.stringGenerator(100));
		liquor.setAlcoholType(TestUtilityFactory.stringGenerator(100));
		liquor.setAlertLevel(-1);
		liquor.setLiquidVolume(-1);
		liquor.setOverflow(-1);
		
		// Validate the model. Use Reflection to Verify the # of fields tested equals # of violations
		Set<ConstraintViolation<Liquor>> violations = validator.validate(liquor);
		success = (violations.size() == Liquor.class.getDeclaredFields().length) ? true : false;
		
		// Optional: Close Factory.
		factory.close();

		// Verify the success of expected results
        Assert.assertTrue(success);
	}

	/**
	 * Add a New Liquor Test
	 * @result Liquor service should successfully add
	 */
	@Test
	public void stage001_addLiquor() 
	{	
		try {
			// call the liquor service to add the liquor
			service.addLiquor(liquor);
			success = true;
		}
		// if Error thrown the test failed
		catch (LiquorFoundException e)
		{
			success = false;
		}
		
		// Verify the success of expected results
		Assert.assertTrue(success);
	}
	
	/**
	 * Add a new Liquor Exception Test
	 * @return Liquor service should fail to add and throw exception
	 */
	@Test
	public void stage002_addLiquorException() 
	{
		try 
		{
			// call liquor service to add. If liquor added the test failed
			service.addLiquor(liquor);
			success = false;
		} 
		// Exception is expected to pass
		catch (LiquorFoundException e) 
		{
			success = true;
		}
		
		// Verify the success of expected results
		Assert.assertTrue(success);
	}
	
	/**
	 * Find an existing liquor test
	 * @result Liquor service should find the liquor
	 */
	@Test
	public void stage003_findOneLiquor() 
	{
		try 
		{
			// call liquor service to find the liquor
			service.getOneLiquor(liquor);
			success = true;
		} 
		// if Exception throw test failed
		catch (LiquorNotFoundException e) 
		{
			success = false;
		}
		
		// Verify the success of expected results
		Assert.assertTrue(success);
	}
	
	/**
	 * Find All Liquor Test
	 * @result Liquor service should assemble a list of all liquors
	 * 
	 * TODO: Update method when business logic supports exceptions
	 */
	@Test
	public void stage004_findAllLiquor() 
	{
		// call liquor service to assemble list
		List<Liquor> liquors = service.findAll();
		
		// if list is empty test failed
		if(liquors == null)
		{
			success = false;
		}
		// if list has content test passed
		else
		{
			success = true;
		}
		
		// Verify the success of expected results
		Assert.assertTrue(success);
	}
	
	/**
	 * Modify Existing Liquor Test
	 * @result Liquor service should update an existing liquor's info
	 */
	@Test
	public void stage005_editLiquor()
	{
		try
		{
			// Set a new value
			liquor.setLiquidVolume(3);
			// call liquor service to update liquor
			service.editLiquor(liquor);
			Liquor edittedLiquor = service.getOneLiquor(liquor);
			
			if(edittedLiquor.getLiquidVolume() == 3) 
			{
				success = true;
			}
		}
		// if Exception thrown test failed
		catch(LiquorNotFoundException e) 
		{
			success = false;
		}
		
		// Verify the success of expected results
		Assert.assertTrue(success);
	}
	
	/**
	 * Delete the liquor Test
	 * @result Liquor service should remove the liquor
	 */
	@Test
	public void stage006_deleteLiquor() 
	{
		try 
		{
			// call liquor service to delete the liquor
			service.deleteLiquor(liquor);
			success = true;
		} 
		// if Exception thrown test failed
		catch (LiquorNotFoundException e) 
		{
			success = false;
		}
		
		// Verify the success of expected results
		Assert.assertTrue(success);
	}
	
	/**
	 * Delete the liquor Exception Test
	 * @result Liquor service should fail and throw exception
	 */
	@Test
	public void stage007_deleteLiquorException()
	{	
		try 
		{
			// call liquor service to remove liquor
			service.deleteLiquor(liquor);
			success = false;
		} 
		// Exception is expected to pass
		catch (LiquorNotFoundException e) 
		{
			success = true;
		}
		
		// Verify the success of expected results
		Assert.assertTrue(success);
	}
	
	/**
	 * Modify Liquor Exception Test
	 * @result Liquor service should fail to find the liquor and throw exception
	 */
	@Test
	public void stage008_editLiquorException()
	{
		try 
		{
			// call liquor service to edit the liquor
			service.editLiquor(liquor);
			
			// should never reach this, exception should be thrown
			success = false;
		} 
		// Exception expected to pass
		catch (LiquorNotFoundException e) 
		{
			success = true;
		}
		
		// Verify the success of expected results
		Assert.assertTrue(success);
	}
	
	/**
	 * Find One liquor Exception Test
	 * @result Liquor service should fail to find and throw exception
	 */
	@Test
	public void stage009_findOneLiquorException() 
	{
		try 
		{
			// call liquor service to find liquor
			service.getOneLiquor(liquor);
			success = false;
		} 
		// Exception is expected to pass
		catch (LiquorNotFoundException e) 
		{
			success = true;
		}
		
		// Verify the success of expected results
		Assert.assertTrue(success);
	}
	
	/**
	 * Find all Liquor Exception Test
	 * @result Datatable should have no liquor, should return an empty list
	 * 
	 * TODO: Update method when business logic supports exceptions	
	 */
	@Test
	public void stage010_findAllLiquorException() 
	{
		// call liquor service to assemble list
		List<Liquor> liquors = service.findAll();
		
		// the list should return empty with no liquor in the data table
		if(liquors == null)
		{
			success = true;
		}
		// if list has content the test failed
		else
		{
			success = false;
		}
			
		// Verify the success of expected results
		Assert.assertTrue(success);
	}
}
