package com.layers.app.model.alerts;

public class Error extends Alert
{
	public Error()
	{
		super();
		this.status = "error";
	}
	
	public Error(String message)
	{
		super(message);
		this.status = "error";
	}
}
