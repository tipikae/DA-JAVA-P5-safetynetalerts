package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.tipikae.safetynetalerts.model.Person;

public class ChildAlert implements Serializable {

	private String firstname;
	private String lastname;
	private int age;
	
	public ChildAlert(String firstname, String lastname, int age) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
