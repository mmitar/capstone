package com.layers.app.model.alerts;

public class Info extends Alert
{
	public Info()
	{
		super();
		this.status = "info";
	}
	
	public Info(String message)
	{
		super(message);
		this.status = "info";
	}
}
