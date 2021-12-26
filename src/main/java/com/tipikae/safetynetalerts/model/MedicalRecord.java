package com.tipikae.safetynetalerts.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

/**
 * An object for MedicalRecord entity.
 * @author tipikae
 * @version 1.0
 *
 */
public class MedicalRecord implements Serializable {

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
	 * Birthdate.
	 */
	@Past
	private LocalDate birthdate;
	/**
	 * Medications.
	 */
	private List<String> medications;
	/**
	 * Allergies.
	 */
	private List<String> allergies;
	
	/**
	 * The default constructor. 
	 */
	public MedicalRecord() {
		super();
	}

	/**
	 * The constructor.
	 * @param firstname a String.
	 * @param lastname a String.
	 * @param birthdate a LocalDate.
	 * @param medications a List of String.
	 * @param allergies a List of String.
	 */
	public MedicalRecord(String firstname, String lastname, LocalDate birthdate, List<String> medications,
			List<String> allergies) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
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
	 * Get birthdate.
	 * @return LocalDate
	 */
	public LocalDate getBirthdate() {
		return birthdate;
	}

	/**
	 * Set birthdate.
	 * @param birthdate a LocalDate.
	 */
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
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
