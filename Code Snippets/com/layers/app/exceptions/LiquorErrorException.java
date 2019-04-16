package com.layers.app.exceptions;

/**
 * Used in the business layer to reinforce business logic regarding execution issues.
 */
public class LiquorErrorException extends RuntimeException
{
	private static final long serialVersionUID = 0L;
	
	public LiquorErrorException(Throwable e)
	{
		super(e);
	}
}
