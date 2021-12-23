package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.tipikae.safetynetalerts.model.Person;

/**
 * An object for ChildAlertDTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class ChildAlert implements Serializable {

	/**
	 * Firstname.
	 */
	private String firstname;
	/**
	 * Lastname.
	 */
	private String lastname;
	/**
	 * Age.
	 */
	private int age;

	/**
	 * The constructor.
	 * @param firstname a String firstname.
	 * @param lastname a String lastname.
	 * @param age an Integer age.
	 */
	public ChildAlert(String firstname, String lastname, int age) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
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
	 * Get age.
	 * @return int
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Set age.
	 * @param age an Integer age.
	 */
	public void setAge(int age) {
		this.age = age;
	}
}
