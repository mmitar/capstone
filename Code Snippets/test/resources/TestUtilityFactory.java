package test.resources;

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
			System.out.println("Error on startUp(): " + e.getMessage());
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
			System.out.println("Error on tearDown(): " + e.getMessage());
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
					case "activity_logs":
						stmt.executeUpdate(activity_logsTable);
						System.out.println("activity_logs table created");
						break;
					case "scale_liquor":
						stmt.executeUpdate(scale_liquorTable);
						System.out.println("scale_liquor table created");
						break;
					case "scale_logs":
						stmt.executeUpdate(scale_logsTable);
						System.out.println("scale_logs table created");
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
			System.out.println("Error on createTables(): " + e.getMessage());
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
					case "activity_logs":
						stmt.executeUpdate(sql);
						System.out.println("activity_logs table dropped");
						break;
					case "scale_liquor":
						stmt.executeUpdate(sql);
						System.out.println("scale_liquor table dropped");
						break;
					case "scale_logs":
						stmt.executeUpdate(sql);
						System.out.println("scale_logs table dropped");
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
			System.out.println("Error on dropTables(): " + e.getMessage());
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
	
	// TODO: UPDATE THIS TABLE
	private static String usersTable = "CREATE TABLE IF NOT EXISTS `users` (\r\n" + 
			"  `ID` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `USERNAME` varchar(20) NOT NULL,\r\n" + 
			"  `PASSWORD` varchar(20) NOT NULL,\r\n" + 
			"  `FIRSTNAME` varchar(20) DEFAULT NULL,\r\n" + 
			"  `LASTNAME` varchar(20) DEFAULT NULL,\r\n" + 
			"  `EMAIL` varchar(30) DEFAULT NULL,\r\n" + 
			"  `PHONE` varchar(14) DEFAULT NULL,\r\n" + 
			"  `PERMISSION` int(11) DEFAULT NULL,\r\n" + 
			"  `VENDORID` varchar(20) DEFAULT NULL,\r\n" + 
			"  `LOCATIONID` varchar(20) DEFAULT NULL,\r\n" + 
			"  PRIMARY KEY (`ID`)\r\n" + 
			") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;";
	
	// TODO: UPDATE THIS TABLE
	private static String liquorsTable = "CREATE TABLE IF NOT EXISTS `liquors` (\r\n" + 
			"  `ID` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `LOCATIONID` varchar(20) NOT NULL,\r\n" + 
			"  `LIQUORCODE` varchar(20) NOT NULL,\r\n" + 
			"  `BRANDNAME` varchar(25) NOT NULL,\r\n" + 
			"  `ALCOHOLTYPE` varchar(25) NOT NULL,\r\n" + 
			"  `LIQUIDVOLUME` int(11) NOT NULL,\r\n" + 
			"  `OVERFLOW` int(11) NOT NULL,\r\n" + 
			"  `ALERTLEVEL` int(11) NOT NULL,\r\n" + 
			"  PRIMARY KEY (`ID`),\r\n" + 
			"  UNIQUE KEY `LIQUORCODE_UNIQUE` (`LIQUORCODE`)\r\n" + 
			") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;";

	private static String locationsTable = "CREATE TABLE IF NOT EXISTS `locations` (\r\n" + 
			"  `ID` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `VENDORID` varchar(20) NOT NULL,\r\n" + 
			"  `LOCATIONID` varchar(20) NOT NULL,\r\n" + 
			"  `ADDRESS` varchar(100) NOT NULL,\r\n" + 
			"  `ZIPCODE` varchar(100) NOT NULL,\r\n" + 
			"  `STATE` varchar(100) NOT NULL,\r\n" + 
			"  `COUNTRY` varchar(100) NOT NULL,\r\n" + 
			"  `LICENSENUMBER` varchar(100) NOT NULL,\r\n" + 
			"  `LICENSEDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\r\n" + 
			"  `DESCRIPTION` varchar(1000) DEFAULT NULL,\r\n" + 
			"  PRIMARY KEY (`ID`),\r\n" + 
			"  UNIQUE KEY `LOCATIONID_UNIQUE` (`LOCATIONID`)\r\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
	
	private static String activity_logsTable = "CREATE TABLE IF NOT EXISTS `activity_logs` (\r\n" + 
			"  `ID` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `LOCATIONID` varchar(20) NOT NULL,\r\n" + 
			"  `LOGTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\r\n" + 
			"  `LOGLEVEL` varchar(5) NOT NULL,\r\n" + 
			"  `LOGMETHOD` varchar(100) NOT NULL,\r\n" + 
			"  `LOGUSER` varchar(1000) NOT NULL,\r\n" + 
			"  `LOGMESSAGE` varchar(1000) NOT NULL,\r\n" + 
			"  PRIMARY KEY (`ID`)\r\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
	
	private static String scale_liquorTable = "CREATE TABLE IF NOT EXISTS `scale_liquor` (\r\n" + 
			"  `ID` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `LOCATIONID` varchar(20) NOT NULL,\r\n" + 
			"  `SCALEID` int(11) NOT NULL,\r\n" + 
			"  `LIQUORCODE` varchar(20) NULL,\r\n" + 
			"  PRIMARY KEY (`ID`)\r\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
	
	private static String scale_logsTable = "CREATE TABLE IF NOT EXISTS `scale_logs` (\r\n" + 
			"  `ID` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `LOCATIONID` varchar(20) NOT NULL,\r\n" + 
			"  `SCALEID` int(11) NOT NULL,\r\n" + 
			"  `LIQUORCODE` varchar(20) NOT NULL,\r\n" + 
			"  `LOGTIME` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\r\n" + 
			"  `LOGQUANTITY` float NOT NULL,\r\n" + 
			"  `LOGMESSAGE` varchar(1000) NOT NULL,\r\n" + 
			"  PRIMARY KEY (`ID`)\r\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
}
