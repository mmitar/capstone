package test.resources.model;


import com.layers.app.model.Scale;

import test.resources.TestUtilityFactory;

public class TestScale extends Scale 
{
	private static final long serialVersionUID = 1L;

	public TestScale()
	{
		super();
	}
	
	/**
	 * Generates a unique Test Liquor model with a randomly generated Liquor Code
	 * 
	 * @return TestLiquor - junit testing
	 */
	public static TestScale newTestScale()
	{
		TestScale scale = new TestScale();
		scale.setLocationId("testlocation");
		scale.setLiquorCode("testliquor");
		scale.setScaleId(0);
		return scale;
	}
	
	public static TestScale newTestLog()
	{
		TestScale log = new TestScale();
		log.setLocationId("testlocation");
		log.setScaleId(0);
		log.setLiquorCode("testliquor");
		log.setLogQuantity(500);
		log.setLogMessage(TestUtilityFactory.stringGenerator(500));
		return log;
	}
}
