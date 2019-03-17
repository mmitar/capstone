package com.layers.app.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Liquor
{
	@NotNull
	@Size(min=0, max=20)
	private String locationId;

	@NotNull(message="Please set a unique Liquor Code for this inventory item")
	@Size(min=5, max=10, message="Liquor Code must be between 5 and 10 characters")
	private String liquorCode;
	
	@NotNull(message="Please label a Brand Name for this inventory item")
	@Size(max=25, message="Brand Name must be less than 25 characters")
	private String brandName;
	
	@NotNull(message="Please give a brief description of the Alcohol Type for this inventory item")
	@Size(max=25, message="Alcohol Type must be less than 25 characters")
	private String alcoholType;

	@Min(value=0, message="Please set the Liquid Volume in liters between 0 to 1.5")
	@Max(value=(long) 1.5, message="Please set the Liquid Volume in liters between 0 to 1.5")
	private float liquidVolume;
	
	@Min(value=0, message="Please set your current unused inventory level")
	@Max(value=30, message="Please set your current unused inventory level")
	private float overflow;
	
	@Min(value=0, message="Please set a minimum Alert Level inventory quantity")
	@Max(value=30, message="Please set a minimum Alert Level inventory quantity")
	private float alertLevel;
	
	public Liquor() 
	{
		locationId = "location1";
		liquorCode = "";
		brandName = "";
		alcoholType = "";
		liquidVolume = 0;
		overflow = 0;
		alertLevel = 0;
	}
	
	public Liquor(String liquorCode, String brandName, String alcoholType, float liquidVolume,
			float overflow, float alertLevel) 
	{
		this.liquorCode = liquorCode;
		this.brandName = brandName;
		this.alcoholType = alcoholType;
		this.liquidVolume = liquidVolume;
		this.overflow = overflow;
		this.alertLevel = alertLevel;
	}
	
	public Liquor(String locationId, String liquorCode, String brandName, String alcoholType, float liquidVolume,
			float overflow, float alertLevel) {
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
	
	public float getLiquidVolume() {
		return liquidVolume;
	}

	public void setLiquidVolume(float liquidVolume) {
		this.liquidVolume = liquidVolume;
	}

	public float getOverflow() {
		return overflow;
	}

	public void setOverflow(float overflow) {
		this.overflow = overflow;
	}

	public float getAlertLevel() {
		return alertLevel;
	}

	public void setAlertLevel(float alertLevel) {
		this.alertLevel = alertLevel;
	}
	
	@Override
	public String toString() {
		return "Liquor [locationId=" + locationId + ", liquorCode=" + liquorCode + ", brandName=" + brandName
				+ ", alcoholType=" + alcoholType + ", liquidVolume=" + liquidVolume + ", overflow=" + overflow
				+ ", alertLevel=" + alertLevel + "]";
	}

	public static String getSqlParams()
	{
		return 	  "LOCATIONID, "
				+ "LIQUORCODE, "
				+ "BRANDNAME, "
				+ "ALCOHOLTYPE, "
				+ "LIQUIDVOLUME, "
				+ "OVERFLOW, "
				+ "ALERTLEVEL";
	}
	
	public static String getSqlSet(Liquor liquor)
	{
		return 	  "LOCATIONID = '" +liquor.getLocationId() + "', "
				+ "LIQUORCODE = '" +liquor.getLiquorCode() + "', "
				+ "BRANDNAME = '" +liquor.getBrandName() + "', "
				+ "ALCOHOLTYPE = '" +liquor.getAlcoholType() + "', "
				+ "LIQUIDVOLUME = " +liquor.getLiquidVolume() + ", "
				+ "OVERFLOW = " +liquor.getOverflow() + ", "
				+ "ALERTLEVEL = " +liquor.getAlertLevel();
	}

	public static String getSqlValues(Liquor liquor)
	{
		return  "'" +liquor.getLocationId() + "', " +
				"'" + liquor.getLiquorCode() + "', " +
				"'" + liquor.getBrandName() + "', " +
				"'" + liquor.getAlcoholType() + "', " +
				"" + liquor.getLiquidVolume() + ", " +
				"" + liquor.getOverflow() + ", " +
				"" + liquor.getAlertLevel() + "";
	}
	
	public static Liquor getResultSet(ResultSet rs) throws SQLException
	{
		Liquor liquor = new Liquor(
				rs.getString("LOCATIONID"),
				rs.getString("LIQUORCODE"),
				rs.getString("BRANDNAME"),
				rs.getString("ALCOHOLTYPE"),
				rs.getFloat("LIQUIDVOLUME"),
				rs.getFloat("OVERFLOW"),
				rs.getFloat("ALERTLEVEL")
				);
		return liquor;
	}
	
	public static Liquor getSqlRowSet(SqlRowSet srs) throws SQLException
	{
		Liquor liquor = new Liquor(
				srs.getString("LOCATIONID"),
				srs.getString("LIQUORCODE"),
				srs.getString("BRANDNAME"),
				srs.getString("ALCOHOLTYPE"),
				srs.getFloat("LIQUIDVOLUME"),
				srs.getFloat("OVERFLOW"),
				srs.getFloat("ALERTLEVEL")
				);
		return liquor;
	}
}
