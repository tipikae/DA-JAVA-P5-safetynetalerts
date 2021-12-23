package com.tipikae.safetynetalerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Person;
import com.tipikae.safetynetalerts.storage.JsonStorage;

/**
 * An implementation of IPersonDAO.
 * @author tipikae
 * @version 1.0
 *
 */
@Repository
public class PersonDAOImpl extends AbstractDAOImpl implements IPersonDAO {

	/**
	 * The constructor.
	 */
	public PersonDAOImpl() {
		jsonStorage = new JsonStorage();
	}

	@Override
	public Person save(Person person) throws StorageException {
		storage = jsonStorage.readStorage();
		List<Person> persons = storage.getPersons();
		persons.add(person);
		storage.setPersons(persons);
		jsonStorage.writeStorage(storage);
		
		return person;
	}

	@Override
	public List<Person> findAll() throws StorageException {
		storage = jsonStorage.readStorage();
		return storage.getPersons();
	}
	
	@Override
	public Person findByFirstnameLastname(String firstname, String lastname) throws StorageException {
		storage = jsonStorage.readStorage();
		Person person = null;
		for (Person item : storage.getPersons()) {
			if (item.getFirstname().equals(firstname) && item.getLastname().equals(lastname)) {
				person = new Person(firstname, lastname, item.getAddress(), item.getCity(), item.getZip(), 
						item.getPhone(), item.getEmail());
				break;
			}
		}
		
		return person;
	}

	@Override
	public List<Person> findByAddress(String address) throws StorageException {
		storage = jsonStorage.readStorage();
		List<Person> results = new ArrayList<>();
		for (Person item : storage.getPersons()) {
			if (item.getAddress().equals(address)) {
				results.add(item);
			}
		}
		
		return results;
	}

	@Override
	public List<Person> findByCity(String city) throws StorageException {
		storage = jsonStorage.readStorage();
		List<Person> results = new ArrayList<>();
		for (Person item : storage.getPersons()) {
			if (item.getCity().equals(city)) {
				results.add(item);
			}
		}
		
		return results;
	}

	@Override
	public Person update(Person person) throws StorageException {
		storage = jsonStorage.readStorage();
		List<Person> persons = storage.getPersons();
		int i = -1;
		
		for(int j = 0; j < persons.size(); j++) {
			if(persons.get(j).getFirstname().equals(person.getFirstname()) && 
					persons.get(j).getLastname().equals(person.getLastname())) {
				i = j;
				break;
			}
		}
		
		if (i != -1) {
			persons.set(i, person);
			storage.setPersons(persons);
			jsonStorage.writeStorage(storage);
			return person;
		} else {
			return null;
		}
	}

	@Override
	public void delete(Person person) throws StorageException {
		storage = jsonStorage.readStorage();
		List<Person> persons = storage.getPersons();
		int i = -1;
		
		for(int j = 0; j < persons.size(); j++) {
			if(persons.get(j).getFirstname().equals(person.getFirstname()) && 
					persons.get(j).getLastname().equals(person.getLastname())) {
				i = j;
				break;
			}
		}
		
		if (i != -1) {
			persons.remove(i);
			storage.setPersons(persons);
			jsonStorage.writeStorage(storage);
		}
	}

}
