package com.layers.app.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User Class with informative attributes to distinguish users from each other and validate their accounts.
 * @author Matt & Joey
 *
 */
public class User 
{
	@NotNull(message="Username cannot be null.")
	@Size(min=5, max=20, message="Username must be between 5 and 20 characters.")
	protected String username;
	
	@NotNull(message="Password cannot be null.")
	@Size(min=5, max=20, message="Password must be between 5 and 20 characters.")
	protected String password;
	
	@Size(max=20, message="First name can be up to 20 characters.")
	protected String firstName;
	
	@Size(max=20, message="Last name can be up to 20 characters.")
	protected String lastName;
	
	@Size(max=30, message="Email address can be up to 30 characters.")
	protected String email;
	
	@Size(max=14, message="Phone number must 10 digits. Include Region #")
	protected String phone;
	
	// Values = { 131313 -> standard, 464646 -> manager, 797979 -> vendor }
	@Min(value=0, message="Cannot set permission below 0")
	@Max(value=5, message="Cannot set permission beyond 5")
	protected int permission;
	
	@Size(min=0, max=20)
	protected String vendorId;
	
	@Size(min=0, max=20)
	protected String locationId;
	
	public User()
	{
/*		firstName="";
		lastName="";
		email="";
		phone="";
		permission = 0;
		vendorId = "";
		locationId = "";*/
	}
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
/*	public User(String firstName, String lastName, String email, String phone) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}*/

/*	public User(String username, String password, String firstName, String lastName, 
			String email, String phone, int permission, String vendorId, String locationId) 
	{
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.permission = permission;
		this.vendorId = vendorId;
		this.locationId = locationId;
	}*/

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}
	
	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", phone=" + phone + ", permission=" + permission + ", vendorId="
				+ vendorId + ", locationId=" + locationId + "]";
	}
	
/*	public static String getTwoSqlParams()
	{
		return 	  "USERNAME, "
				+ "PASSWORD";
	}*/
	
/*	public static String getTwoSqlValues(User user)
	{
		return  "'" + user.getUsername() + "', " +
				"'" + user.getPassword() + "'";
	}*/
	
	public void setUserResultSet(ResultSet rs) throws SQLException
	{
		this.username = rs.getString("USERNAME");
		this.password = rs.getString("PASSWORD");
		this.firstName = rs.getString("FIRSTNAME");
		this.lastName = rs.getString("LASTNAME");
		this.email = rs.getString("EMAIL");
		this.phone = rs.getString("PHONE");
		this.permission = rs.getInt("PERMISSION");
		this.vendorId = rs.getString("VENDORID");
		this.locationId = rs.getString("LOCATIONID");
	}
	
	public static String getUserSqlInsertStatement()
	{
		return 	  "(USERNAME, "
				+ "PASSWORD, "
				+ "FIRSTNAME, "
				+ "LASTNAME, "
				+ "EMAIL, "
				+ "PHONE, "
				+ "PERMISSION, "
				+ "VENDORID, "
				+ "LOCATIONID) VALUES (?,?,?,?,?,?,?,?,?)";
	}
}
