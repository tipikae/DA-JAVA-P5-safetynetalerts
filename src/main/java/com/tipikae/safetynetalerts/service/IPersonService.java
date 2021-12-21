package com.tipikae.safetynetalerts.service;

import java.util.List;

import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Person;

public interface IPersonService {

	Person addPerson(Person person) throws StorageException;
	List<Person> getPersons() throws StorageException;
	List<Person> getPersonsByAddress(String address) throws ServiceException, StorageException;
	List<Person> getPersonsByCity(String city) throws ServiceException, StorageException;
	Person getPersonByFirstnameLastname(String firstname, String lastname) throws ServiceException, StorageException;
	Person updatePerson(String firstname, String lastname, Person person) throws ServiceException, StorageException;
	void deletePerson(String firstname, String lastname) throws ServiceException, StorageException;
}
