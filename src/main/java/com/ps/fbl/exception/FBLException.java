package com.ps.fbl.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Mukesh.V
 *
 */

public class FBLException extends Exception {
	/**
	* 
	*/
	private static final long serialVersionUID = 8235804283731814238L;
	private String message;
	private HttpStatus code;

	protected FBLException() {
	}

	public FBLException(String message, HttpStatus code) {
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}


}
