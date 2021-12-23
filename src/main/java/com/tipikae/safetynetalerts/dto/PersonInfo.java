package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

public class PersonInfo implements Serializable {

	private String firstname;
	private String lastname;
	private String address;
	private int age;
	private String email;
	private List<String> medications;
	private List<String> allergies;
	
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	
}
