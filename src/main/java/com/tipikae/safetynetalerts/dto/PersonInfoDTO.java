package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

public class PersonInfoDTO implements Serializable {

	private String lastname;
	private List<PersonInfo> persons;
	
	public PersonInfoDTO(String lastname, List<PersonInfo> persons) {
		this.lastname = lastname;
		this.persons = persons;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<PersonInfo> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonInfo> persons) {
		this.persons = persons;
	}
}
