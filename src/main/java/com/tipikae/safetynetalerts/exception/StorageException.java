package com.tipikae.safetynetalerts.exception;

/**
 * An exception sent by JsonStorage.
 * @author tipikae
 * @version 1.0
 *
 */
public class StorageException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * The constructor.
	 * @param message a String.
	 * @param cause an Exception cause.
	 */
	public StorageException(String message, Exception cause) {
		super(message, cause);
	}
}
