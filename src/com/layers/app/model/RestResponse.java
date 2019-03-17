package com.layers.app.model;


/**
 * Rest response model from spout action
 * 
 * @author Joey & Matt
 *
 */
public class RestResponse 
{
	private int status;
	private String messgae;
	private Liquor data;
	
	public RestResponse(int status, String messgae, Liquor data) 
	{
		super();
		this.status = status;
		this.messgae = messgae;
		this.data = data;
	}

	public RestResponse() {
		super();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessgae() {
		return messgae;
	}

	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}

	public Liquor getData() {
		return data;
	}

	public void setData(Liquor data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "RestResponse [status=" + status + ", messgae=" + messgae + ", data=" + data + "]";
	}
	
	
	
	
	
}
