package com.tipikae.safetynetalerts.dao;

import java.util.List;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Person;

/**
 * An interface for CRUD operations on Person.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IPersonDAO {

	/**
	 * Save a Person object.
	 * @param person a Person object.
	 * @return Person
	 * @throws StorageException
	 */
	Person save(Person person) throws StorageException;

	/**
	 * Find all persons.
	 * @return List<Person>
	 * @throws StorageException
	 */
	List<Person> findAll() throws StorageException;

	/**
	 * Find a person by firstname and lastname.
	 * @param firstname a String firtname.
	 * @param lastname a String lastname.
	 * @return Person
	 * @throws StorageException
	 */
	Person findByFirstnameLastname(String firstname, String lastname) throws StorageException;

	/**
	 * Find persons by address.
	 * @param address a String address.
	 * @return List<Person>
	 * @throws StorageException
	 */
	List<Person> findByAddress(String address) throws StorageException;

	/**
	 * Find persons by city.
	 * @param city a String city
	 * @return List<Person>
	 * @throws StorageException
	 */
	List<Person> findByCity(String city) throws StorageException;

	/**
	 * Update a person.
	 * @param person a Person object.
	 * @return Person
	 * @throws StorageException
	 */
	Person update(Person person) throws StorageException;

	/**
	 * Delete a person.
	 * @param person a Person object.
	 * @throws StorageException
	 */
	void delete(Person person) throws StorageException;
}
