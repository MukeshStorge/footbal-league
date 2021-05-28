package com.ps.fbl.exception;

import org.springframework.http.HttpStatus;

public class FblExceptionSchema {

	private String message;
	private HttpStatus code;

	protected FblExceptionSchema() {
	}

	public FblExceptionSchema(String message, HttpStatus code) {
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