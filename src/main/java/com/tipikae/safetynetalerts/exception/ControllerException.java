package com.tipikae.safetynetalerts.exception;

import java.io.Serializable;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Exception sent by all controllers.
 * @author tipikae
 * @version 1.0
 *
 */
public class ControllerException implements Serializable {

	private static final Logger LOGGER = LogManager.getLogger("ControllerException");
	private static final long serialVersionUID = 1L;

	/**
	 * Code.
	 */
	private int code;
	/**
	 * Message.
	 */
	private String message;
	/**
	 * Timestamp.
	 */
	private Date timestamp;

	/**
	 * The constructor.
	 * @param code an Integer HTTP status.
	 * @param message a String.
	 */
	public ControllerException(int code, String message) {
		this.code = code;
		this.message = message;
		this.timestamp = new Date();
	}

	/**
	 * Get code.
	 * @return int
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Set code.
	 * @param code an Integer HTTP status.
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Get message.
	 * @return String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set message.
	 * @param message a String.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Get timestamp.
	 * @return Date
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * Set timestamp.
	 * @param timestamp a Date
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
