package com.layers.app.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.layers.app.exceptions.DatabaseException;
import com.layers.app.model.User;

/**
 * DAO to access persistence data regarding User information
 * 
 * @param <User>
 */
public class UserDataService implements UserDataInterface{

	/**
	 * Spring JDBC Dependency Setter Injection
	 */
	private JdbcTemplate jdbcTemplateObject;
	
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	/**
	 * Junit Testing Default and Non-Default Constructors
	 */
	public UserDataService() {}
	public UserDataService(DataSource testdatasource)
	{
		// Overwrite the jdbctemplate with the testdatasource
		this.jdbcTemplateObject = new JdbcTemplate(testdatasource);
	}

	/**
	 * READ Method
	 * Validation Login query checks if username exists in the database, case sensitive.
	 * 
	 * @param User user
	 * @return User user || null
	 * @throws DatabaseException
	 */
	@Override
	public User findBy(User user)
	{
		try
		{
			// READ query to identify the user by username and password.
			String sql = "SELECT * FROM `users` WHERE "
					+ "USERNAME = '"+user.getUsername()+"' "
					+ "AND PASSWORD = '"+user.getPassword()+"'";
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			
			// Goes to the Last Row of the Results
			srs.beforeFirst();
			srs.last();
			
			// Checks the Size of the Results. If anything other than 1, return null
			if(srs.getRow() != 1)
			{
				return null;
			}
			
			// Last Row should still be the First, and return the user
			return User.getSqlRowSet(srs);
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}

	/**
	 * CREATE Method
	 * Adds new user to DB << If using create(), findBy() first >>
	 * 
	 * @param User user
	 * @return boolean
	 * @throws DatabaseException
	 */
	@Override
	public boolean create(User user) 
	{
		// Create query that adds the user to the DB
		String sql = "INSERT INTO `users` (" + User.getAllSqlParams() + ") VALUES (" + User.getAllSqlValues(user) + ")";
		try
		{
			// Execute SQL Insert
			int rows = jdbcTemplateObject.update(sql);
			
			// Return Result of Insert
			return rows == 1 ? true : false;
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}
	
	/**
	 * READ Method
	 * Validation Registration query checks if username exists in the database, case insensitive.
	 * 
	 * @param User user
	 * @return boolean
	 * @throws DatabaseException
	 */
	@Override 
	public boolean findIfExists(User user)
	{
		try
		{
			// READ query to identify the user by username. Case Insensitive.
			String sql = "SELECT * FROM `users` WHERE "
					+ "UPPER(USERNAME) LIKE TRIM(UPPER('"+user.getUsername()+"'))";
			
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			
			// Goes to the Last Row of the Results
			srs.beforeFirst();
			srs.last();
			
			// Checks the Size of the Results. If anything was found, return true
			if(srs.getRow() > 0)
			{
				return true;
			}
			
			// if no results were found, nothing exists.
			return false;
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}
	
	/**
	 * Inactive.
	 */
	@Override
	public boolean update(User t) 
	{
		return false;
	}

	/**
	 * Inactive.
	 */
	@Override
	public boolean delete(User t) 
	{
		return false;
	}

	/**
	 * Inactive.
	 */
	@Override
	public User findById(int id) 
	{
		return null;
	}
	
	/**
	 * Inactive.
	 */
	@Override
	public List<User> findAll() 
	{
		return null;
	}
	
	/**
	 * Inactive.
	 */
	@Override 
	public List<User> findAll(User user)
	{
		return null;
	}
}
