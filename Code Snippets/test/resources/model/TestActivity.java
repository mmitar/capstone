package test.resources.model;


import com.layers.app.model.Activity;

public class TestActivity extends Activity 
{
	public TestActivity()
	{
		super();
	}
	
	/**
	 * Generates a unique Test Liquor model with a randomly generated Liquor Code
	 * 
	 * @return TestLiquor - junit testing
	 */
	public static TestActivity newTestLiquor()
	{
		TestActivity activity = new TestActivity();
		return activity;
	}
}
