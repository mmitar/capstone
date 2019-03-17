package test.java.services;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


/**
 * Accesses app REST API functions to test business and data logic. 
 * 
 * @Test - designed to emulate its respective controller method by testing against exceptions.
 * @FixMethodOrder - Sets the tests to run in the alphabetical order. This is critical to the logic the tests run in order of.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestServiceTest 
{
	// Setter Injects the test data source for database connections to the test schema
//    private RestBusinessService service = new RestBusinessService(UtilityFactory.getDataSource());

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
	 * 
	 */
	@Test
	public void stage0_testServiceClass() 
	{
		// Verify the success of expected results
		Assert.assertTrue(true);
	}
}