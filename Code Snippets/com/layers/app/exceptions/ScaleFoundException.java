package com.layers.app.exceptions;

import com.layers.app.model.Scale;

/**
 * Used in the business layer to reinforce business logic regarding registered Scale Found Exception
 */
public class ScaleFoundException extends Exception 
{
	private Scale scale;
	
	private static final long serialVersionUID = 1L;
	
	public ScaleFoundException() {}
	
	public ScaleFoundException(Scale scale)
	{
		super();
		this.scale = scale;
	}
	
	public Scale getScale()
	{
		return scale;
	}
}
