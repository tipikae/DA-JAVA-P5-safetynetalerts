package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for PersonInfo request.
 * @author tipikae
 * @version 1.0
 *
 */
public class PersonInfoDTO extends DTOResponse implements Serializable {

	/**
	 * Lastname.
	 */
	private String lastname;
	/**
	 * Persons.
	 */
	private List<PersonInfo> persons;

	/**
	 * The constructor.
	 * @param lastname a String.
	 * @param persons a List of PersonInfo.
	 */
	public PersonInfoDTO(String lastname, List<PersonInfo> persons) {
		this.lastname = lastname;
		this.persons = persons;
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
	 * Get persons.
	 * @return List<PersonInfo>
	 */
	public List<PersonInfo> getPersons() {
		return persons;
	}

	/**
	 * Set persons.
	 * @param persons a List of PersonInfo.
	 */
	public void setPersons(List<PersonInfo> persons) {
		this.persons = persons;
	}
}
