package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for PhoneAlert request.
 * @author tipikae
 * @version 1.0
 *
 */
public class PhoneAlertDTO implements Serializable {

	/**
	 * Phone numbers.
	 */
	private List<PhoneAlert> phones;

	/**
	 * The constructor.
	 * @param phones a List of PhoneAlert.
	 */
	public PhoneAlertDTO(List<PhoneAlert> phones) {
		this.phones = phones;
	}

	/**
	 * Get phone numbers.
	 * @return List<PhoneAlert>
	 */
	public List<PhoneAlert> getPhones() {
		return phones;
	}

	/**
	 * Set phone numbers.
	 * @param phones a List of PhoneAlert.
	 */
	public void setPhones(List<PhoneAlert> phones) {
		this.phones = phones;
	}
	
}
