package test.resources.model;

import com.layers.app.model.User;

import test.resources.TestUtilityFactory;

public class TestUser extends User
{
	public TestUser()
	{
		super();
	}
	
	public static TestUser newTestUser()
	{
		TestUser user = new TestUser();
		user.setLocationId("testlocation");
		user.setUsername(TestUtilityFactory.stringGenerator(10));
		user.setPassword(TestUtilityFactory.stringGenerator(10));
		user.setFirstName(TestUtilityFactory.stringGenerator(10));
		user.setLastName(TestUtilityFactory.stringGenerator(10));
		user.setPhone(TestUtilityFactory.stringGenerator(14));
		user.setEmail(TestUtilityFactory.stringGenerator(10));
		user.setPermission(464646);
		user.setVendorId(TestUtilityFactory.stringGenerator(10));
		return user;
	}
}
