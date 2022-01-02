package com.tipikae.safetynetalerts.service;

import com.tipikae.safetynetalerts.dto.PersonDTO;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;

/**
 * An interface providing services for PersonController.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IPersonService {

	/**
	 * Add a person.
	 * @param person a PersonDTO object.
	 * @return PersonDTO
	 * @throws ServiceException
	 * @throws StorageException
	 */
	PersonDTO addPerson(PersonDTO personDTO) throws ServiceException, StorageException;
	/**
	 * Update a person.
	 * @param firstname a String.
	 * @param lastname a String.
	 * @param person a PersonDTO object.
	 * @return PersonDTO
	 * @throws ServiceException
	 * @throws StorageException
	 */
	PersonDTO updatePerson(String firstname, String lastname, PersonDTO personDTO) 
			throws ServiceException, StorageException;
	/**
	 * Delete a person.
	 * @param firstname a String.
	 * @param lastname a String.
	 * @throws ServiceException
	 * @throws StorageException
	 */
	void deletePerson(String firstname, String lastname) throws ServiceException, StorageException;
}
