package com.tipikae.safetynetalerts.exception;

import java.io.Serializable;
import java.util.Date;

public class ControllerException implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int code;
	private String message;
	private Date timestamp;

	public ControllerException(int code, String message, Date timestamp) {
		this.code = code;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
