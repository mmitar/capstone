package test.resources.services;

import java.lang.reflect.Field;
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

import com.layers.app.business.UserBusinessInterface;
import com.layers.app.exceptions.UserErrorException;
import com.layers.app.exceptions.UserFoundException;
import com.layers.app.exceptions.UserNotFoundException;
import com.layers.app.model.User;

import test.resources.TestUtilityFactory;
import test.resources.junitConfiguration;
import test.resources.model.TestUser;

/**
 * Accesses app User functions to test business and data logic. 
 * 
 * @Test - designed to emulate its respective controller method by testing against exceptions.
 * @FixMethodOrder - Sets the tests to run in the alphabetical order. This is critical to the logic the tests run in order of.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=junitConfiguration.class)
public class UserServiceTest 
{
	/**
	 *  Used to validate every tests performance
	 */
	private boolean success = false;
	
	/**
	 * Test case dependent objects. Initialized in @BeforeClass
	 */
	private static TestUser commonUser = null;
	private static TestUser newUser = null;
	
    @Autowired
    private UserBusinessInterface service;
    
    /**
     * Before the class executes the tests, assemble to proper testing environemnt.
     */
	@BeforeClass
	public static void setUpBeforeClass()
	{
		// call the factory to create the required data tables
		TestUtilityFactory.createTables("users");
		
		// set the instance of a existing user and new user
		commonUser = TestUser.newTestUser();
		newUser = TestUser.newTestUser();
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
	 * Before each test, reset fields that get modified
	 */
	@Before
	public void resetTest()
	{
		// set the test success field to false to confirm Test Asserts
		success = false;
	}
	
	/**
	 * User Model Attribute Validation Test
	 * Tests all fields to be set outside of their validated bounds.
	 */
	@Test
	public void stage000_modelAttributeValidation()
	{	
		// Initialize a Validator Factory
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        // Create User Model. Set each field outside of its permitted bounds
		TestUser user = new TestUser();
		user.setUsername(TestUtilityFactory.stringGenerator(50));
		user.setPassword(TestUtilityFactory.stringGenerator(50));
		user.setFirstName(TestUtilityFactory.stringGenerator(50));
		user.setLastName(TestUtilityFactory.stringGenerator(50));
		user.setEmail(TestUtilityFactory.stringGenerator(50));
		user.setPhone(TestUtilityFactory.stringGenerator(50));
		user.setPermission(-1);
		user.setVendorId(TestUtilityFactory.stringGenerator(50));
		user.setLocationId(TestUtilityFactory.stringGenerator(50));
		
		// Validate the model. Use Reflection to Verify the # of fields tested equals # of violations
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		// Optional: Close Factory.
		factory.close();

		// Some libaries add synethic fields, check against them and tally up the originals
		int nonSyntheticFields = 0;
		for (Field field : User.class.getDeclaredFields())
		{
			if(field.isSynthetic() == false) nonSyntheticFields++;
		}
		
		// Verify the number of original fields are equal to the number of violations
        Assert.assertEquals(nonSyntheticFields, violations.size());
	}

	/**
	 * Add a New User Test
	 * @result User service should successfully add
	 */
	@Test
	public void stage1_addUser() 
	{
		try
		{
			// call the user service to add the user
			service.addUser(commonUser);
			success = true;
		}
		// if Error thrown the test failed
		catch(UserFoundException | UserErrorException e)
		{
		}
		
		// Verify the success of expected results
		Assert.assertTrue(success);
	}
	
	/**
	 * Add a New User Exception Test
	 * @result User service should fail to add and throw exception
	 */
	@Test
	public void stage2_addUserException() 
	{
		try
		{
			// call user service to add. If user added the test failed
			service.addUser(commonUser);
		}
		// Exception is expected to pass
		catch(UserFoundException | UserErrorException e)
		{
			success = true;
		}
		
		// Verify the success of expected results
		Assert.assertTrue(success);
	}
	
	/**
	 * Validate an existing user Test
	 * @result User service should validate the user
	 */
	@Test
	public void stage3_authenticateUser() 
	{
		try
		{
			// call user service to verify user credentials
			service.authenticateUser(commonUser);
			success = true;
		}
		// if Exception thrown test failed
		catch(UserNotFoundException e)
		{ }
		
		// Verify the success of expected results
		Assert.assertTrue(success);
	}
	
	/**
	 * Validate an existign user Exception test
	 * @result User service should fail to validate the user
	 */
	@Test
	public void stage4_authenticateUserException() 
	{
		try
		{
			// call user service to verify user credentials.
			service.authenticateUser(newUser);
		}
		// Exception is expected to pass
		catch(UserNotFoundException e)
		{
			success = true;
		}
		
		// Verify the success of expected results
		Assert.assertTrue(success);
	}
}
