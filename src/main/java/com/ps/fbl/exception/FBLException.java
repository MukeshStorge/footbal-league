package com.ps.fbl.exception;

/**
 * 
 * @author Mukesh.V
 *
 */
public class FBLException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7138183112138884480L;
	public FBLException(String message) {
		
		super(message);
	}
	public FBLException(String message, Throwable trace)
	{
		super(message,trace);
	}
	
	

}

