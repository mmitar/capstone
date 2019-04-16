package com.layers.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.layers.app.exceptions.DatabaseException;
import com.layers.app.model.User;

/**
 * DAO to access persistence data regarding User information
 * 
 * @param <User>
 */
public class UserDataService implements UserDataInterface
{
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
	 * {@inheritDoc}
	 */
	@Override
	public User findBy(User user)
	{
		try
		{
			// READ query to identify the user by username and password.
			String query = "SELECT * FROM `users` WHERE "
					+ "BINARY USERNAME = ? "
					+ "AND BINARY PASSWORD = ? ";
			
			// Execute query and get result set
			List<User> users = jdbcTemplateObject.query(
					query,
					new PreparedStatementSetter()
					{
						@Override
						public void setValues(PreparedStatement ps) throws SQLException
						{
							ps.setString(1, user.getUsername());
							ps.setString(2, user.getPassword());
						}
					},
					new RowMapper<User>()
					{
						@Override
						public User mapRow(ResultSet rs, int rowNum) throws SQLException
						{
							User user = new User();
							
							user.setUserResultSet(rs);
							
							return user;
						}
					}
					);
			
			return users.size() == 1 ? users.get(0) : null;
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean create(User user) 
	{
		try
		{
			// Create query that adds the user to the DB
			String query = "INSERT INTO `users` " + User.getUserSqlInsertStatement();
			
			// Execute query and get the rows affected
			int rows = jdbcTemplateObject.update(
					new PreparedStatementCreator() 
					{ 
						@Override
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException 
						{
							PreparedStatement ps = connection.prepareStatement(query, new String[] { "ID" } );
							
							ps.setString(1, user.getUsername());
							ps.setString(2, user.getPassword());
							ps.setString(3, user.getFirstName());
							ps.setString(4, user.getLastName());
							ps.setString(5, user.getEmail());
							ps.setString(6, user.getPhone());
							ps.setInt(7, user.getPermission());
							ps.setString(8, user.getVendorId());
							ps.setString(9, user.getLocationId());
							
							return ps; 
						}
					},
					new GeneratedKeyHolder()
					);
			
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
	 * {@inheritDoc}
	 */
	@Override 
	public boolean findIfExists(User user)
	{
		try
		{
			// READ query to identify the user by username. Case Insensitive.
			String query = "SELECT * FROM `users` WHERE "
					+ "UPPER(USERNAME) LIKE TRIM(UPPER(?))";
			
			// Execute query and get result set
			List<User> users = jdbcTemplateObject.query(
					query,
					new PreparedStatementSetter()
					{
						@Override
						public void setValues(PreparedStatement ps) throws SQLException
						{
							ps.setString(1, user.getUsername());
						}
					},
					new RowMapper<User>()
					{
						@Override
						public User mapRow(ResultSet rs, int rowNum) throws SQLException
						{
							User user = new User();
							
							user.setUserResultSet(rs);
							
							return user;
						}
					}
					);
			
			return users.size() == 1 ? true : false;
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}
}
