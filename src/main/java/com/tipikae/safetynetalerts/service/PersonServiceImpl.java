package com.tipikae.safetynetalerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.safetynetalerts.dao.IPersonDAO;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Person;

@Service
public class PersonServiceImpl implements IPersonService {

	private static final Logger LOGGER = LogManager.getLogger("PersonService");

	@Autowired
	private IPersonDAO personDao;

	public void setPersonDao(IPersonDAO personDao) {
		this.personDao = personDao;
	}

	@Override
	public Person addPerson(Person person) throws StorageException {
		return personDao.save(person);
	}
	
	@Override
	public List<Person> getPersons() throws StorageException {
		return personDao.findAll();
	}

	@Override
	public Person getPersonByFirstnameLastname(String firstname, String lastname) 
			throws ServiceException, StorageException {
		Person person = personDao.findByFirstnameLastname(firstname, lastname);
		if (person != null) {
			return person;
		} else {
			LOGGER.error("getPersonByFirstnameLastname: Firstname: " + firstname + " and lastname:" 
					+ lastname + " not found in Person.");
			throw new ServiceException("Firstname: " + firstname + " and lastname:" + lastname 
					+ " not found in Person.");
		}
	}

	@Override
	public List<Person> getPersonsByAddress(String address) throws ServiceException, StorageException {
		List<Person> persons = personDao.findAll();
		boolean exist = false;
		
		for(Person person: persons) {
			if(address.equals(person.getAddress())) {
				exist = true;
				break;
			}
		}
		
		if(exist) {
			return personDao.findByAddress(address);
		} else {
			LOGGER.error("getPersonsByAddress: Address: " + address + " not found in Person.");
			throw new ServiceException("Address: " + address + " not found in Person.");
		}
	}

	@Override
	public List<Person> getPersonsByCity(String city) throws ServiceException, StorageException {
		List<Person> persons = personDao.findAll();
		boolean exist = false;
		
		for(Person person: persons) {
			if(city.equals(person.getCity())) {
				exist = true;
				break;
			}
		}
		
		if(exist) {
			return personDao.findByCity(city);
		} else {
			LOGGER.error("getPersonsByCity: City: " + city + " not found in Person.");
			throw new ServiceException("City: " + city + " not found in Person.");
		}
	}

	@Override
	public Person updatePerson(String firstname, String lastname, Person person) 
			throws ServiceException, StorageException {
		if (firstname.equals(person.getFirstname()) && lastname.equals(person.getLastname())) {
			if (personDao.findByFirstnameLastname(firstname, lastname) != null) {
				return personDao.update(person);
			} else {
				LOGGER.error("updatePerson: Firstname: " + firstname + " and lastname:"
						+ lastname + " not found in Person.");
				throw new ServiceException("Firstname: " + firstname + " and lastname:"
						+ lastname + " not found in Person.");
			} 
		} else {
			LOGGER.error("updatePerson: Firstname: " + firstname + " and lastname:"
					+ lastname + " are different from Person firstname: " + person.getFirstname() 
					+ " lastname: " + person.getLastname());
			throw new ServiceException("Firstname: " + firstname + " and lastname:"
					+ lastname + " are different from Person firstname: " + person.getFirstname()
					+ "	lastname: " + person.getLastname());
		}
	}

	@Override
	public void deletePerson(String firstname, String lastname) throws StorageException, ServiceException {
		Person person = personDao.findByFirstnameLastname(firstname, lastname);
		if(person != null) {
			personDao.delete(person);
		} else {
			LOGGER.error("deletePerson: Firstname: " + firstname + " and lastname:"
					+ lastname + " not found in Person.");
			throw new ServiceException("Firstname: " + firstname + " and lastname:"
					+ lastname + " not found in Person.");
		}
	}

}
