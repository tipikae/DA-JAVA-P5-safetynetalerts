package com.tipikae.safetynetalerts.exception;

/**
 * An exception sent by all Services.
 * @author tipikae
 * @version 1.0
 *
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * The constructor.
	 * @param message a String.
	 */
	public ServiceException(String message) {
		super(message);
	}

}
