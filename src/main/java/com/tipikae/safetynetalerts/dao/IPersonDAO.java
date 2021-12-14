package com.tipikae.safetynetalerts.dao;

import java.util.List;

import com.tipikae.safetynetalerts.models.Person;

public interface IPersonDAO {

	Person save(Person person);
	List<Person> findAll();
	Person findByName(String firstname, String lastname);
	List<Person> findByAddress(String address);
	List<Person> findByCity(String city);
	void update(Person person);
	void deleteByName(String firstname, String lastname);
}
