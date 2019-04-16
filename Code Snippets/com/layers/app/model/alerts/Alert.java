package com.layers.app.model.alerts;

public class Alert
{
	protected String status;
	protected String message;
	
	public Alert()
	{
	}
	
	public Alert(String message)
	{
		this.message = message;
	}
	
	public Alert(String status, String message)
	{
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Alert [status=" + status + ", message=" + message + "]";
	}
	
}
