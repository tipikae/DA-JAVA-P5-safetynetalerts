package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;

/**
 * An object for PhoneAlertDTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class PhoneAlert implements Serializable {

	/*
	 * Phone number.
	 */
	private String phone;

	/**
	 * The constructor.
	 * @param phone A String.
	 */
	public PhoneAlert(String phone) {
		this.phone = phone;
	}

	/**
	 * Get phone number.
	 * @return String
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Set phone number.
	 * @param phone a String.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
