package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

/**
 * A DTO for Person model object.
 * @author tipikae
 * @version 1.0
 *
 */
public class PersonDTO implements Serializable {

	/**
	 * FirstName.
	 */
	@NotBlank(message="FirstName is mandatory")
	private String firstName;
	/**
	 * LastName.
	 */
	@NotBlank(message="LastName is mandatory")
	private String lastName;
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
	public PersonDTO() {
		super();
	}

	/**
	 * The constructor.
	 * @param firstName a String.
	 * @param lastName a String.
	 * @param address a String.
	 * @param city a String.
	 * @param zip a String.
	 * @param phone a String.
	 * @param email a String.
	 */
	public PersonDTO(String firstName, String lastName, String address, String city, String zip, String phone,
			String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

	/**
	 * Get firstName.
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set firstName.
	 * @param firstName a String.
	 */
	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get lastName.
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set lastName.
	 * @param lastName a String.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
