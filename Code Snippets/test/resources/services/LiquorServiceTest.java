package test.resources.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.layers.app.business.LiquorBusinessInterface;
import com.layers.app.exceptions.LiquorFoundException;
import com.layers.app.exceptions.LiquorNotFoundException;
import com.layers.app.exceptions.ScaleNotFoundException;
import com.layers.app.model.Liquor;

import test.resources.TestUtilityFactory;
import test.resources.junitConfiguration;
import test.resources.model.TestLiquor;
import test.resources.model.TestUser;

	
/**
 * Accesses app Liquor functions to test business and data logic. 
 * 
 * @Test - designed to emulate its respective controller method by testing against exceptions.
 * @FixMethodOrder - Sets the tests to run in the alphabetical order. This is critical to the logic the tests run in order of.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=junitConfiguration.class)
public class LiquorServiceTest 
{
	/**
	 *  Used to validate every tests performance
	 */
	private boolean success = false;
	
	/**
	 * Test case dependent objects. Initialized in @BeforeClass
	 */
	private static TestLiquor liquor = null;
	private static TestUser user = null;
	
    @Autowired
    private LiquorBusinessInterface service;
    
    /**
     * Before the class executes the tests, assemble to proper testing environemnt.
     */
	@BeforeClass
	public static void setUpBeforeClass()
	{
		// call the factory to create the required data tables
		TestUtilityFactory.createTables("liquors scale_liquor");
		
		// Initialize the common models and set feild values
		user = TestUser.newTestUser();
		liquor = TestLiquor.newTestLiquor();
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
	
	@Before
	public void beforeTest()
	{
		success = false;
	}
	
	/**
	 * Liquor Model Attribute Validation Test. Tests all fields to be set outside of their validated bounds.
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
		// Optional: Close Factory.
		factory.close();

		int nonSyntheticSerialFields = 0;
		// Some libraries add synthetic fields + Serialized fields for Json
		for (Field field : Liquor.class.getDeclaredFields())
		{
			// check against synthetic and serialized fields
			if(!field.isSynthetic() && !field.getName().contains("serialVersionUID"))
			{
				// tally total original model fields
				nonSyntheticSerialFields++;
			}
		}
		
		// Verify the number of original fields are equal to the number of violations
        Assert.assertEquals(nonSyntheticSerialFields, violations.size());
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
			service.addLiquor(liquor, user.getLocationId());
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
			service.addLiquor(liquor, user.getLocationId());
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
	
	@Test
	public void stage003_getUnRegisteredLiquors()
	{
		try {
			List<Liquor> unregisteredLiquors = service.getUnRegisteredLiquors(user.getLocationId());
			success = true;
		} catch (LiquorNotFoundException e) {
		}
		
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
			service.getOneLiquor(liquor.getLiquorCode(), user.getLocationId());
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
	 * Find All Liquor Test
	 * @result Liquor service should assemble a list of all liquors
	 * 
	 * TODO: Update method when business logic supports exceptions
	 */
	@Test
	public void stage004_findAllLiquor() 
	{
		try
		{
			// call liquor service to assemble list
			service.findAll(user.getLocationId());
			success = true;
		}
		// if exception thrown fail test
		catch(LiquorNotFoundException e)
		{
			success = false;
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
			service.editLiquor(liquor, user.getLocationId());
			Liquor edittedLiquor = service.getOneLiquor(liquor.getLiquorCode(), user.getLocationId());
			
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
			service.deleteLiquor(liquor.getLiquorCode(), user.getLocationId());
			success = true;
		} 
		// if Exception thrown test failed
		catch (LiquorNotFoundException e) 
		{
			success = false;
		}
		// At this point, the liquor was deleted, used for building a message
		catch (ScaleNotFoundException e)
		{
			success = true;
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
			service.deleteLiquor(liquor.getLiquorCode(), user.getLocationId());
			success = false;
		} 
		// Exception is expected to pass
		catch (LiquorNotFoundException e) 
		{
			success = true;
		}
		// At this point, an existing liquor would be deleted
		catch (ScaleNotFoundException e)
		{
			success = false;
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
			service.editLiquor(liquor, user.getLocationId());
			
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
			service.getOneLiquor(liquor.getLiquorCode(), user.getLocationId());
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
	 */
	@Test
	public void stage010_findAllLiquorException() 
	{
		try
		{
			// call liquor service to assemble list
			service.findAll(user.getLocationId());
			
			// if list has content the test failed
			success = false;
		}
		// the list should return empty with no liquor in the data table
		catch(LiquorNotFoundException e)
		{
			success = true;
		}

		// Verify the success of expected results
		Assert.assertTrue(success);
	}
	
	@Test
	public void stage012_getUnRegisteredLiquorsException()
	{
		try {
			List<Liquor> unregisteredLiquors = service.getUnRegisteredLiquors(user.getLocationId());
		} catch (LiquorNotFoundException e) 
		{
			success = true;
		}
		
		Assert.assertTrue(success);
	}
}
