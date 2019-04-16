package test.resources.services;

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

import com.layers.app.business.ScaleBusinessInterface;

import test.resources.TestUtilityFactory;
import test.resources.junitConfiguration;
import test.resources.model.TestLiquor;
import test.resources.model.TestUser;


/**
 * Accesses app REST API functions to test business and data logic. 
 * 
 * @Test - designed to emulate its respective controller method by testing against exceptions.
 * @FixMethodOrder - Sets the tests to run in the alphabetical order. This is critical to the logic the tests run in order of.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=junitConfiguration.class)
public class RestServiceTest 
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
	private ScaleBusinessInterface scaleService;
	
    /**
     * Before the class executes the tests, assemble to proper testing environemnt.
     */
	@BeforeClass
	public static void setUpBeforeClass()
	{
		// call the factory to create the required data tables
		TestUtilityFactory.createTables("users liquors locations activity_logs scale_liquor scale_logs invalidname");
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
	public void beforeTest()
	{
		success = false;
	}
	
	/**
	 * 
	 */
	@Test
	public void stage0_testServiceClass() 
	{
		// Verify the success of expected results
		Assert.assertTrue(true);
	}
}