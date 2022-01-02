package com.tipikae.safetynetalerts.service;

import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Person;

/**
 * An interface providing services for PersonController.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IPersonService {

	/**
	 * Add a person.
	 * @param person a Person object.
	 * @return Person
	 * @throws ServiceException
	 * @throws StorageException
	 */
	Person addPerson(Person person) throws ServiceException, StorageException;
	/**
	 * Update a person.
	 * @param firstname a String.
	 * @param lastname a String.
	 * @param person a Person object.
	 * @return Person
	 * @throws ServiceException
	 * @throws StorageException
	 */
	Person updatePerson(String firstname, String lastname, Person person) throws ServiceException, StorageException;
	/**
	 * Delete a person.
	 * @param firstname a String.
	 * @param lastname a String.
	 * @throws ServiceException
	 * @throws StorageException
	 */
	void deletePerson(String firstname, String lastname) throws ServiceException, StorageException;
}
