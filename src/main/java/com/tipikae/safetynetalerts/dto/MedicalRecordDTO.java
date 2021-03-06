package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

/**
 * A DTO for MedicalRecord model object.
 * @author tipikae
 * @version 1.0
 *
 */
public class MedicalRecordDTO implements Serializable {

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
	 * Birthdate.
	 */
	@NotNull(message="Birthdate is mandatory")
	@Past(message="Birthdate must be passed")
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
	public MedicalRecordDTO() {
		super();
	}

	/**
	 * The constructor.
	 * @param firstName a String.
	 * @param lastName a String.
	 * @param birthdate a LocalDate.
	 * @param medications a List of String.
	 * @param allergies a List of String.
	 */
	public MedicalRecordDTO(String firstName, String lastName, LocalDate birthdate, List<String> medications,
			List<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
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
	public void setFirstName(String firstName) {
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
