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
import com.layers.app.business.ScaleBusinessInterface;
import com.layers.app.exceptions.LiquorFoundException;
import com.layers.app.exceptions.LiquorNotFoundException;
import com.layers.app.exceptions.ScaleLimitException;
import com.layers.app.exceptions.ScaleNotFoundException;
import com.layers.app.model.Scale;

import test.resources.TestUtilityFactory;
import test.resources.junitConfiguration;
import test.resources.model.TestLiquor;
import test.resources.model.TestScale;
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
public class ScaleServiceTest 
{
	/**
	 *  Used to validate every tests performance
	 */
	private boolean success = false;
	final int MAXSCALEQTY = 6;
	final int MAXSCALEID = MAXSCALEQTY - 1;
	
	/**
	 * Test case dependent objects. Initialized in @BeforeClass
	 */
	private static TestLiquor liquor = null;
	private static TestUser user = null;
	private static TestScale scale = null;
	
	@Autowired
	private ScaleBusinessInterface scaleService;
	
	@Autowired
	private LiquorBusinessInterface liquorService;
	
    /**
     * Before the class executes the tests, assemble to proper testing environemnt.
     */
	@BeforeClass
	public static void setUpBeforeClass()
	{
		// call the factory to create the required data tables
		TestUtilityFactory.createTables("liquors scale_liquor");
		
		liquor = TestLiquor.newTestLiquor();
		user = TestUser.newTestUser();
		scale = TestScale.newTestScale();
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
	public void stage000_modelAttributeValidation() 
	{
		// Initialize a Validator Factory
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        // Create Scale Model. Set each field outside of its permitted bounds
        Scale scale = new Scale();
        
		scale.setLocationId(TestUtilityFactory.stringGenerator(100));
		scale.setScaleId(100);
		scale.setLiquorCode(TestUtilityFactory.stringGenerator(100));
		scale.setLogQuantity(2000);
		scale.setLogMessage(TestUtilityFactory.stringGenerator(1500));
//		scale.setLogTime(null);
		
		/**
		 * @Pattern regex annotation cannot be Junit validated and will be ignored
		 * scale.setLogTime(new Timestamp(new Date(0).getTime()));
		 */
		
		// Validate the model. Use Reflection to Verify the # of fields tested equals # of violations
		Set<ConstraintViolation<Scale>> violations = validator.validate(scale);
		// Optional: Close Factory.
		factory.close();
		
		// Plus 2 for the synthetic fields I cant NOT validate.. $jaCoCo, serialVersionUID..
		int nonSyntheticSerialFields = 2;
		// Some libraries add synthetic fields + Serialized fields for Json
		for (Field field : Scale.class.getDeclaredFields())
		{
			// check against synthetic and serialized fields
			if(!field.isSynthetic() && !field.getName().contains("serialVersionUID"))
			{	
//				System.out.println("nonsynthetic--->" + field.getName());
				// tally total original model fields
				nonSyntheticSerialFields++;
			}
		}
		
		// Verify the number of original fields are equal to the number of violations
        Assert.assertEquals(nonSyntheticSerialFields, violations.size());
	}

	@Test
	public void stage001_displayScalesException()
	{
		try
		{
			scaleService.getAllRegisteredScales(user.getLocationId());
			liquorService.getUnRegisteredLiquors(user.getLocationId());
		}
		catch(ScaleNotFoundException | LiquorNotFoundException e)
		{
			success = true;
		} 
		
		Assert.assertTrue(success);
	}
	
	@Test
	public void stage002_addScale() 
	{
		try
		{
			// Call the scale service to add the number of scales requested to the location
			success = scaleService.addScalesToLocation(MAXSCALEQTY, user.getLocationId());
		}
		// Max quantity of configure scales has been reached
		catch(ScaleLimitException e)
		{
		}
		
		Assert.assertTrue(success);
	}
	
	@Test
	public void stage003_addScaleException() 
	{
		try
		{
			// Call the scale service to add the number of scales requested to the location
			scaleService.addScalesToLocation(MAXSCALEQTY, user.getLocationId());
		}
		// Max quantity of configure scales has been reached
		catch(ScaleLimitException e)
		{
			success = true;
		}
		
		Assert.assertTrue(success);
	}
	
	@Test
	public void stage004_displayScales() 
	{
		try
		{
			List<Scale> scales = scaleService.getAllRegisteredScales(user.getLocationId());
			
			success = true;
		}
		catch(ScaleNotFoundException e)
		{
		} 
		
		Assert.assertTrue(success);
	}
	
	@Test
	public void stage005_addLiquorToScale()
	{
		try 
		{
			liquorService.addLiquor(liquor, user.getLocationId());
			
			scale.setScaleId(MAXSCALEID);
			success = scaleService.configureLiquorToScale(scale, user.getLocationId());
		} 
		catch (ScaleNotFoundException | LiquorFoundException | LiquorNotFoundException e) 
		{
		}
		
		Assert.assertTrue(success);
	}
	
	@Test
	public void stage006_addLiquorToScaleException()
	{
		try 
		{
			scale.setScaleId(MAXSCALEID);
			scaleService.configureLiquorToScale(scale, user.getLocationId());
		} 
		catch (ScaleNotFoundException | LiquorFoundException | LiquorNotFoundException e) {
			success = true;
		}
		
		Assert.assertTrue(success);
	}

	@Test
	public void stage007_removeScaleException()
	{
		try {
			scaleService.removeScaleFromLocation(user.getLocationId());
		} catch (LiquorFoundException e) {
			success = true;
		}
		Assert.assertTrue(success);
	}
	
	@Test
	public void stage008_removeLiquorFromScale()
	{
		try 
		{
			success = scaleService.unbindLiquorByScaleId(MAXSCALEID, user.getLocationId());
		}
		catch(ScaleNotFoundException e)
		{
		}
		
		Assert.assertTrue(success);
	}
	
	@Test
	public void stage009_removeScale()
	{
		try {
			success = scaleService.removeScaleFromLocation(user.getLocationId());
		} catch (LiquorFoundException e) 
		{
		}
		Assert.assertTrue(success);
	}

	
	@Test
	public void stage010_removeLiquorFromScaleException()
	{
		try 
		{
			scaleService.unbindLiquorByScaleId(MAXSCALEID, user.getLocationId());
		}
		catch(ScaleNotFoundException e)
		{
			success = true;
		}
		
		Assert.assertTrue(success);
	}
}