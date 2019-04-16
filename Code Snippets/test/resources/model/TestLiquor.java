package test.resources.model;


import com.layers.app.model.Liquor;

import test.resources.TestUtilityFactory;

public class TestLiquor extends Liquor 
{
	public TestLiquor()
	{
		super();
	}
	
	/**
	 * Generates a unique Test Liquor model with a randomly generated Liquor Code
	 * 
	 * @return TestLiquor - junit testing
	 */
	public static TestLiquor newTestLiquor()
	{
		TestLiquor liquor = new TestLiquor();
		liquor.setLocationId("testlocation");
		liquor.setLiquorCode("testliquor");
		liquor.setBrandName(TestUtilityFactory.stringGenerator(10));
		liquor.setAlcoholType(TestUtilityFactory.stringGenerator(10));
		liquor.setLiquidVolume(1);
		liquor.setOverflow(1);
		liquor.setAlertLevel(1);
		return liquor;
	}
}
