package test.java;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;

import test.java.services.LiquorServiceTest;
import test.java.services.RestServiceTest;
import test.java.services.TestUtilityFactory;
import test.java.services.UserServiceTest;

/**
 * This suite runs through all business logic and data logic as standalone implementations. No cross over implemented.
 * 
 * @FixMethodOrder - Sets the classes to run in the alphabetical order.
 * @RunWith - Run as a Suite to initialize the test cases
 * @Suite.SuiteClasses - Call the test cases to run in this suite
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Suite.class)
@Suite.SuiteClasses({
	LiquorServiceTest.class, RestServiceTest.class, UserServiceTest.class
})
public class TestSuite 
{
	/**
	 * Before the Suite implements the Test classes, setup the database schema and connections
	 */
	@BeforeClass
	public static void setUpBeforeSuite()
	{
		TestUtilityFactory.startUp();
	}
	
	/**
	 * After the Suite completes running through the test classes, tear down the schema and close connections
	 */
	@AfterClass
	public static void tearDownAfterSuite()
	{
		TestUtilityFactory.tearDown();
	}
}
