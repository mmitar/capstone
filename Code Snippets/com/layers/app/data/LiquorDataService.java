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
import com.layers.app.model.Liquor;



public class LiquorDataService implements LiquorDataInterface 
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
	public List<Liquor> findAll(String locationId) 
	{
		try {
			// Read query to select all the liquors from the table
			String query = "SELECT * FROM `liquors` WHERE LOCATIONID = ? LIMIT 100";	
			
			// Execute query and get result set
			List<Liquor> liquors = jdbcTemplateObject.query(
					query,
					new PreparedStatementSetter()
					{
						@Override
						public void setValues(PreparedStatement ps) throws SQLException
						{
							ps.setString(1, locationId);
						}
					},
					new RowMapper<Liquor>()
					{
						@Override
						public Liquor mapRow(ResultSet rs, int rowNum) throws SQLException
						{
							Liquor liquor = new Liquor();
							liquor.setLiquorResultSet(rs);
							return liquor;
						}
					}
					);
			
			return liquors;
		}
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
	public Liquor findBy(String liquorCode, String locationId) 
	{
		try
		{
			// READ query to find the liquor by ID
			String query = "SELECT * FROM `liquors` "
					+ "WHERE `LIQUORCODE` = ? "
							+ "AND `LOCATIONID` = ? ";
			
			// Execute query and get result set
			List<Liquor> liquors = jdbcTemplateObject.query(
					query,
					new PreparedStatementSetter()
					{
						@Override
						public void setValues(PreparedStatement ps) throws SQLException
						{
							ps.setString(1, liquorCode);
							ps.setString(2, locationId);
						}
					},
					new RowMapper<Liquor>()
					{
						@Override
						public Liquor mapRow(ResultSet rs, int rowNum) throws SQLException
						{
							Liquor liquor = new Liquor();
							liquor.setLiquorResultSet(rs);
							return liquor;
						}
					}
					);
			
			// Checks the Size of the Results. If anything other than 1, return null
			if(liquors.size() != 1)
			{
				return null;
			}
			
			// Last Row should still be the First, and return the liquor
			return liquors.get(0);
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
	public boolean update(Liquor liquor) 
	{
		try
		{
			// UPDATE query that updates the selected liquor by its params
			String query = "UPDATE `liquors` SET " + Liquor.getLiquorSqlUpdateSet(liquor) 
					+ " WHERE LIQUORCODE = ? AND LOCATIONID = ?";
			
			// Execute query and get the rows affected
			int rows = jdbcTemplateObject.update(
					new PreparedStatementCreator() 
					{ 
						@Override
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException 
						{
							PreparedStatement ps = connection.prepareStatement(query, new String[] { "ID" } );
	
							ps.setString(1, liquor.getBrandName());
							ps.setString(2, liquor.getAlcoholType());
							ps.setInt(3, liquor.getLiquidVolume());
							ps.setInt(4, liquor.getOverflow());
							ps.setInt(5, liquor.getAlertLevel());
							ps.setString(6, liquor.getLiquorCode());
							ps.setString(7, liquor.getLocationId());
							
							return ps; 
						}
					},
					new GeneratedKeyHolder()
					);
			
			// If affected rows is one, create was successful
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
	public boolean create(Liquor liquor) 
	{
		try
		{
			// CREATE query that adds the liquor to the database
			String query = "INSERT INTO `liquors` " + Liquor.getLiquorSqlInsertStatement();
		
			// Execute query and get the rows affected
			int rows = jdbcTemplateObject.update(
					new PreparedStatementCreator() 
					{ 
						@Override
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException 
						{
							PreparedStatement ps = connection.prepareStatement(query, new String[] { "ID" } );
	
							ps.setString(1, liquor.getLocationId());
							ps.setString(2, liquor.getLiquorCode());
							ps.setString(3, liquor.getBrandName());
							ps.setString(4, liquor.getAlcoholType());
							ps.setInt(5, liquor.getLiquidVolume());
							ps.setInt(6, liquor.getOverflow());
							ps.setInt(7, liquor.getAlertLevel());
							
							return ps; 
						}
					},
					new GeneratedKeyHolder()
					);
			
			// If affected rows is one, create was successful
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
	public boolean delete(Liquor liquor) 
	{
		try
		{
			// DELETE query that removes the liquor by ID
			String query = "DELETE FROM  `liquors` WHERE LIQUORCODE = ? AND LOCATIONID = ?";
					
			// Execute query and get the rows affected
			int rows = jdbcTemplateObject.update(
					new PreparedStatementCreator() 
					{ 
						@Override
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException 
						{
							PreparedStatement ps = connection.prepareStatement(query, new String[] { "ID" } );
	
							ps.setString(1, liquor.getLiquorCode());
							ps.setString(2, liquor.getLocationId());
							
							return ps; 
						}
					},
					new GeneratedKeyHolder()
					);
			
			// If affected rows is one, create was successful
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
	public List<Liquor> findUnregisteredScales(String locationId)
	{
		try
		{
			// READ query that identifies all the non configured liquors to scales by locationId
			String query = "SELECT * FROM `liquors` WHERE `liquors`.`LOCATIONID` = ? " + 
					" AND NOT EXISTS (SELECT * FROM `scale_liquor` " + 
					" WHERE `liquors`.`LIQUORCODE` = `scale_liquor`.`LIQUORCODE`);";
			
			// Execute query and get result set
			List<Liquor> liquors = jdbcTemplateObject.query(
					query,
					new PreparedStatementSetter()
					{
						@Override
						public void setValues(PreparedStatement ps) throws SQLException
						{
							ps.setString(1, locationId);
						}
					},
					new RowMapper<Liquor>()
					{
						@Override
						public Liquor mapRow(ResultSet rs, int rowNum) throws SQLException
						{
							Liquor liquor = new Liquor();
							liquor.setLiquorResultSet(rs);
							return liquor;
						}
					}
					);
			
			return liquors;
		}
		// Catches SQL / DB Connection Issues.
		catch(Exception e)
		{
			// Throw Custom DB Exception
			throw new DatabaseException(e);
		}
	}
}
