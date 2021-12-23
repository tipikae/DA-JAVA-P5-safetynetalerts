package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;

/**
 * An object for CommunityEmailDTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class CommunityEmail implements Serializable {

	/**
	 * Email.
	 */
	private String email;

	/**
	 * The constructor.
	 * @param email a String email.
	 */
	public CommunityEmail(String email) {
		this.email = email;
	}

	/**
	 * Get email.
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set email.
	 * @param email a String email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
