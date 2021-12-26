package com.tipikae.safetynetalerts.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

/**
 * An object for Person entity.
 * @author tipikae
 * @version 1.0
 *
 */
public class Person implements Serializable {

	/**
	 * Firstname.
	 */
	@NotBlank(message="Firstname is mandatory")
	private String firstname;
	/**
	 * Lastname.
	 */
	@NotBlank(message="Lastname is mandatory")
	private String lastname;
	/**
	 * Address.
	 */
	@NotBlank(message="Address is mandatory")
	private String address;
	/**
	 * City.
	 */
	@NotBlank(message="City is mandatory")
	private String city;
	/**
	 * Zip code.
	 */
	@NotBlank(message="Zip code is mandatory")
	private String zip;
	/**
	 * Phone number.
	 */
	@NotBlank(message="Phone number is mandatory")
	private String phone;
	/**
	 * Email.
	 */
	@NotBlank(message="Email is mandatory")
	private String email;

	/**
	 * The default constructor.
	 */
	public Person() {
		super();
	}

	/**
	 * The constructor.
	 * @param firstname a String.
	 * @param lastname a String.
	 * @param address a String.
	 * @param city a String.
	 * @param zip a String.
	 * @param phone a String.
	 * @param email a String.
	 */
	public Person(String firstname, String lastname, String address, String city, String zip, String phone,
			String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
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
	 * @param firstName a String.
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
	 * @param lastName a String.
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
	 * @param address a String.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get city.
	 * @return String
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Set city.
	 * @param city a String.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Get zip code.
	 * @return String
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * Set zip code.
	 * @param zip  a String.
	 */
	public void setZip(String zip) {
		this.zip = zip;
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

	/**
	 * Get email.
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set email.
	 * @param email a String.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
