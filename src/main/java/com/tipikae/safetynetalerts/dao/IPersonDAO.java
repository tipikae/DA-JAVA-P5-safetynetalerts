package com.tipikae.safetynetalerts.dao;

import java.util.List;

import com.tipikae.safetynetalerts.model.Person;

public interface IPersonDAO {

	Person save(Person person);
	List<Person> findAll();
	Person findByName(String firstname, String lastname);
	List<Person> findByAddress(String address);
	List<Person> findByCity(String city);
	boolean update(Person person);
	boolean deleteByName(String firstname, String lastname);
}
