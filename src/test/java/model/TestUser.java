package test.java.model;

import com.layers.app.model.User;

public class TestUser extends User
{
	public TestUser()
	{
		super();
	}
	
	public TestUser(String username, String password)
	{
		super(username, password);
	}
	
	public void setUsername(String username) 
	{
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
}
