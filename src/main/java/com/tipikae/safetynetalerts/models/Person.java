package com.tipikae.safetynetalerts.models;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Person {

	private String firstname;
	private String lastname;
	private String phone;
	private String email;
	private Date birthdate;
	private Location location;
	private Map<String, String> medications;
	private List<String> allergies;
}
