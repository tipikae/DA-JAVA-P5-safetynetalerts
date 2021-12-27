package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;

/**
 * An object for FirestationDTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class FirestationInfo implements Serializable {

	/**
	 * Firstname.
	 */
	private String firstname;
	/**
	 * Lastname.
	 */
	private String lastname;
	/**
	 * Address.
	 */
	private String address;
	/**
	 * Phone number.
	 */
	private String phone;

	/**
	 * The constructor.
	 * @param firstname a String firstname.
	 * @param lastname a String lastname.
	 * @param address a String address.
	 * @param phone a String phone number.
	 */
	public FirestationInfo(String firstname, String lastname, String address, String phone) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.phone = phone;
	}

	/**
	 * Get firstname.
	 * @return String
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Set firstname.
	 * @param firstname a String firstname.
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Get lastname.
	 * @return String
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Set lastname.
	 * @param lastname a String lastname.
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Get address.
	 * @return String
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set address.
	 * @param address a String address.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get phone number.
	 * @return String
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Set phone number
	 * @param phone a String phone number.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
