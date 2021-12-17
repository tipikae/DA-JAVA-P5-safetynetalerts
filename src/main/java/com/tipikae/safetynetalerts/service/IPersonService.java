package com.tipikae.safetynetalerts.service;

import java.util.List;

import com.tipikae.safetynetalerts.model.Person;

public interface IPersonService {

	Person addPerson(Person person);
	List<Person> getPersons();
	List<Person> getPersonsByAddress(String address);
	List<Person> getPersonsByCity(String city);
	Person getPersonByName(String firstname, String lastname);
	boolean updatePerson(String firstname, String lastname, Person person);
	boolean deletePerson(String firstname, String lastname);
}
