package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * An object for FireDTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class Fire implements Serializable {

	/**
	 * Firstname.
	 */
	private String firstname;
	/**
	 * Lastname.
	 */
	private String lastname;
	/**
	 * Phone number.
	 */
	private String phone;
	/**
	 * Age.
	 */
	private int age;
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
	 * @param firstname a String firstname.
	 * @param lastname a String lastname.
	 * @param phone a String phone number.
	 * @param age an age Integer.
	 * @param medications a medications List.
	 * @param allergies an allergies List.
	 */
	public Fire(String firstname, String lastname, String phone, int age, List<String> medications,
			List<String> allergies) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.age = age;
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
	 * Get phone number.
	 * @return String
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Set phone number.
	 * @param phone a String phone number.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * @param age an age Integer.
	 */
	public void setAge(int age) {
		this.age = age;
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
	 * @param medications a medications List.
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
	 * @param allergies an allergies List.
	 */
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
}
