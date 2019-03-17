package test.java.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Utility class for Test Suites and Test Classes to make db connections and test live implementations
 */
public class TestUtilityFactory 
{
	// DB connection and query channel
	private static Connection conn = null;
	private static Statement stmt = null;

	// Retains test schema table names that were requested in createTables(). Referenced later in dropTables().
	private static String[] tableArray = null;
	
	/**
	 * Defines the test data schema for the DAO to utilize to overwrite the JdbcTemplate
	 * 
	 * @return DriverManagerDataSource
	 */
	public static DriverManagerDataSource getDataSource() 
	{
		// Data source params for the testdream schema
		final String driverClassName = "com.mysql.jdbc.Driver";
		final String url = "jdbc:mysql://localhost:3306/testdream";
		final String dbUsername = "root";
		final String dbPassword = "root";
		
		// Initializing the test datasource
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);

		return dataSource;
	}
	
	/**
	 * Establishes a connection to the localdb, creates the test schema and sets the query connection to the test schema
	 * Use in the Test Suite @BeforeClass
	 */
	public static void startUp()
	{
		try 
		{
			// DB Connection Info for localhost
			final String url = "jdbc:mysql://localhost:3306";
			final String username = "root";
			final String password = "root";
			
			//established a connection to the localdb and query channel
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
			
			// create the test schema
			String sql = "CREATE DATABASE testdream;";
			stmt.executeUpdate(sql);
			System.out.println("testdream created");
			
			// set the query channel to USE the test schema
			sql = "USE `testdream`";
			stmt.executeUpdate(sql);
		}
		// Catch and Report the Error
		catch (Exception e)
		{
			System.out.println("Error on startUp(): " + e.toString());
		}
	}
	
	/**
	 * Drops the test schema and closes the connection for queries and to the localdb.
	 * Use in the Test Suite @AfterClass
	 */
	public static void tearDown()
	{
		try 
		{
			// drop the test schema
			String sql = "DROP DATABASE testdream;";
			stmt.executeUpdate(sql);
			System.out.println("testdream dropped");
		
			// Close the query channel
			stmt.close();
			// Close database connection
			conn.close();
		}
		// Catch and Report the Error
		catch(Exception e)
		{
			System.out.println("Error on tearDown(): " + e.toString());
		}
	}
		
	/**
	 * Reusable testing method that will build the requested schema structures. Removes invalid table names.
	 * Use in Test Service @BeforeClass
	 *  
	 * @param String tables - Request table names, delimit with a space
	 */
	public static void createTables(String tables)
	{
		try 
		{
			// Class level attribute, split the input into an array
			tableArray = tables.split(" ");
			
			// iterate through all the table names
			for (String table : tableArray)
			{
				// if the table name exists in the switch, create if it does not exist
				switch(table)
				{
					case "users":
						stmt.executeUpdate(usersTable);
						System.out.println("users table created");
						break;
					case "liquors":
						stmt.executeUpdate(liquorsTable);
						System.out.println("liquors table created");
						break;
					case "locations":
						stmt.executeUpdate(locationsTable);
						System.out.println("locations table created");
						break;
					default:
						// Remove the invalid table from the array via index of to not confuse other elements
						tableArray = (String[]) ArrayUtils.remove(tableArray, Arrays.asList(tableArray).indexOf(table));
						System.out.println("Table name `" + table + "` does not match. Removed from list");
						break;
				}
			}
		} 
		// Catch and Report the Error
		catch (Exception e) 
		{
			System.out.println("Error on createTables(): " + e.toString());
		}
	}
	
	/**
	 * Drop the data tables retained in the tableArray generated from createTables()
	 * Use in Test Service @AfterClass
	 */
	public static void dropTables()
	{
		try 
		{
			// Iterate through all the table names in the array list
			for (String table : tableArray)
			{
				// Drop the table if found in the array and matches in the switch
				String sql = "DROP TABLE IF EXISTS " + table;
				switch(table)
				{
					case "users":
						stmt.executeUpdate(sql);
						System.out.println("users table dropped");
						break;
					case "liquors":
						stmt.executeUpdate(sql);
						System.out.println("liquors table dropped");
						break;
					case "locations":
						stmt.executeUpdate(sql);
						System.out.println("locations table dropped");
						break;
					default:
						System.out.println("Table name `" + table + "` does not match");
						break;
				}
			}
		} 
		// Catch and Report the Error
		catch (Exception e) 
		{
			System.out.println("Error on dropTables(): " + e.toString());
		}
	}

	/**
	 * Generate a random character string. Define the length by inputing the number as the argument.
	 * Used for model attribute validation
	 * 
	 * @param length int - defines the string length
	 * @return String - randomly generated string
	 */
	public static String stringGenerator(int length) 
	{
		// Define the character set
		final String CHARACTER_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String generatedString = new String();
		
		// Iterate through the character set till the requested length
		while (length-- != 0) 
		{
			int character = (int)(Math.random()*CHARACTER_SET.length());
			generatedString += CHARACTER_SET.charAt(character);
		}
		
		// Return the generated string
		return generatedString;
	}
	
	/** ======= Data Table Structures for createTables() ======= **/

	// Removed for Snippet
}
