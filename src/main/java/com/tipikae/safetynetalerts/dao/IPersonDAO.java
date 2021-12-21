package com.tipikae.safetynetalerts.dao;

import java.util.List;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Person;

public interface IPersonDAO {

	Person save(Person person) throws StorageException;
	List<Person> findAll() throws StorageException;
	Person findByFirstnameLastname(String firstname, String lastname) throws StorageException;
	List<Person> findByAddress(String address) throws StorageException;
	List<Person> findByCity(String city) throws StorageException;
	Person update(Person person) throws StorageException;
	void delete(Person person) throws StorageException;
}
