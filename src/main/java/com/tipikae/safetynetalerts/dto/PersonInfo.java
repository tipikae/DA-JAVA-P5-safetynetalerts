package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

/**
 * An object for PersonInfoDTO.
 * @author tipikae
 *
 */
public class PersonInfo implements Serializable {

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
	 * Age.
	 */
	private int age;
	/**
	 * Email.
	 */
	private String email;
	/**
	 * Medications.
	 */
	private List<String> medications;
	/**
	 * Allergies.
	 */
	private List<String> allergies;

	/**
	 * The constructor.
	 * @param firstname a String.
	 * @param lastname a String.
	 * @param address a String.
	 * @param age an Integer.
	 * @param email a String.
	 * @param medications a List of String.
	 * @param allergies a List of String.
	 */
	public PersonInfo(String firstname, String lastname, String address, int age, String email,
			List<String> medications, List<String> allergies) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.age = age;
		this.email = email;
		this.medications = medications;
		this.allergies = allergies;
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
	 * @param firstname a String.
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
	 * @param lastname a String.
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
	 * Get age.
	 * @return int
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Set age.
	 * @param age an int.
	 */
	public void setAge(int age) {
		this.age = age;
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

	/**
	 * Get medications.
	 * @return List<String>
	 */
	public List<String> getMedications() {
		return medications;
	}

	/**
	 * Set medications.
	 * @param medications a List of String.
	 */
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	/**
	 * Get allergies.
	 * @return List<String>
	 */
	public List<String> getAllergies() {
		return allergies;
	}

	/**
	 * Set allergies.
	 * @param allergies a List of String.
	 */
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	
}
