package com.layers.app.model.alerts;

public class Success extends Alert
{
	public Success()
	{
		super();
		this.status = "success";
	}
	
	public Success(String message)
	{
		super(message);
		this.status = "success";
	}
}
