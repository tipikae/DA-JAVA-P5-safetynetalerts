package com.tipikae.safetynetalerts.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class Person implements Serializable {

	@NotBlank(message="Firstname is mandatory")
	private String firstName;
	
	@NotBlank(message="Lastname is mandatory")
	private String lastName;
	
	@NotBlank(message="Address is mandatory")
	private String address;
	
	@NotBlank(message="City is mandatory")
	private String city;
	
	@NotBlank(message="Zip code is mandatory")
	private String zip;
	
	@NotBlank(message="Phone number is mandatory")
	private String phone;
	
	@NotBlank(message="Email is mandatory")
	private String email;
	
	public Person(String firstName, String lastName, String address, String city, String zip, String phone,
			String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
