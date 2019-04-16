package com.layers.app.model.alerts;

public class Warn extends Alert
{
	public Warn()
	{
		super();
		this.status = "warn";
	}
	
	public Warn(String message)
	{
		super(message);
		this.status = "warn";
	}
}
