package com.layers.app.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Liquor
{
	@Size(min=0, max=20)
	private String locationId;

	@NotNull(message="Please set a unique Liquor Code for this inventory item")
	@Size(min=5, max=20, message="Liquor Code must be between 5 and 20 characters")
	private String liquorCode;
	
	@NotNull(message="Please label a Brand Name for this inventory item")
	@Size(max=25, message="Brand Name must be less than 25 characters")
	private String brandName;
	
	@NotNull(message="Please give a brief description of the Alcohol Type for this inventory item")
	@Size(max=25, message="Alcohol Type must be less than 25 characters")
	private String alcoholType;

	@Min(value=0, message="Please set the Liquid Volume between 0 to 1500 milliliters.")
	@Max(value=1500, message="Please set the Liquid Volume between 0 to 1500 milliliters.")
	private int liquidVolume;
	
	@Min(value=0, message="Please set your current unused inventory level.")
	@Max(value=30, message="Please set your current unused inventory level.")
	private int overflow;
	
	@Min(value=0, message="Please set a minimum Alert Level inventory quantity.")
	@Max(value=30, message="Please set a minimum Alert Level inventory quantity.")
	private int alertLevel;
	
	public Liquor() 
	{
	}
	
	public Liquor(String liquorCode)
	{
		this.liquorCode = liquorCode;
	}
	
	public Liquor(String liquorCode, String brandName, String alcoholType, int liquidVolume,
			int overflow, int alertLevel) 
	{
		this.liquorCode = liquorCode;
		this.brandName = brandName;
		this.alcoholType = alcoholType;
		this.liquidVolume = liquidVolume;
		this.overflow = overflow;
		this.alertLevel = alertLevel;
	}
	
	public Liquor(String locationId, String liquorCode, String brandName, String alcoholType, int liquidVolume,
			int overflow, int alertLevel) {
		this.locationId = locationId;
		this.liquorCode = liquorCode;
		this.brandName = brandName;
		this.alcoholType = alcoholType;
		this.liquidVolume = liquidVolume;
		this.overflow = overflow;
		this.alertLevel = alertLevel;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	public String getLiquorCode() {
		return liquorCode;
	}

	public void setLiquorCode(String liquorCode) {
		this.liquorCode = liquorCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getAlcoholType() {
		return alcoholType;
	}

	public void setAlcoholType(String alcoholType) {
		this.alcoholType = alcoholType;
	}
	
	public int getLiquidVolume() {
		return liquidVolume;
	}

	public void setLiquidVolume(int liquidVolume) {
		this.liquidVolume = liquidVolume;
	}

	public int getOverflow() {
		return overflow;
	}

	public void setOverflow(int overflow) {
		this.overflow = overflow;
	}

	public int getAlertLevel() {
		return alertLevel;
	}

	public void setAlertLevel(int alertLevel) {
		this.alertLevel = alertLevel;
	}
	
	@Override
	public String toString() {
		return "'[ID: " + liquorCode + "] Brand: " + brandName + ", Alcohol: " + alcoholType + ", Liquid Volume: " + liquidVolume + ", "
				+ "Overflow: " + overflow + ", Alert Level: " + alertLevel +"'";
	}
	
	public String quickDescription() 
	{
		if(brandName == null && alcoholType == null) {
			return "Liquor";
		} else if(brandName == null) {
			return alcoholType; 
		} else if(alcoholType == null) {
			return brandName;
		} else {
			return brandName + " - " + alcoholType;
		}
	}

	public static String getLiquorSqlInsertStatement()
	{
		return 	  "(`LOCATIONID`, "
				+ "`LIQUORCODE`, "
				+ "`BRANDNAME`, "
				+ "`ALCOHOLTYPE`, "
				+ "`LIQUIDVOLUME`, "
				+ "`OVERFLOW`, "
				+ "`ALERTLEVEL`) VALUES (?,?,?,?,?,?,?)";
	}
	
	public static String getLiquorSqlUpdateSet(Liquor liquor)
	{
		return 	 "BRANDNAME = ?, "
				+ "ALCOHOLTYPE = ?, "
				+ "LIQUIDVOLUME = ?, "
				+ "OVERFLOW = ?, "
				+ "ALERTLEVEL = ?";
	}

	public void setLiquorResultSet(ResultSet rs) throws SQLException
	{
		this.locationId = rs.getString("LOCATIONID");
		this.liquorCode = rs.getString("LIQUORCODE");
		this.brandName = rs.getString("BRANDNAME");
		this.alcoholType = rs.getString("ALCOHOLTYPE");
		this.liquidVolume = rs.getInt("LIQUIDVOLUME");
		this.overflow = rs.getInt("OVERFLOW");
		this.alertLevel = rs.getInt("ALERTLEVEL");
	}
}
