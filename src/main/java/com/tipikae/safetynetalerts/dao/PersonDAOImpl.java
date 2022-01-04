package com.tipikae.safetynetalerts.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Person;
import com.tipikae.safetynetalerts.storage.IStorage;

/**
 * An implementation of IPersonDAO.
 * @author tipikae
 * @version 1.0
 *
 */
@Repository
public class PersonDAOImpl extends AbstractDAOImpl implements IPersonDAO {

	@Autowired
	private IStorage jsonStorage;

	/**
	 * {@inheritDoc}
	 * @param person {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public Optional<Person> save(Person person) throws StorageException {
		storage = jsonStorage.readStorage();
		List<Person> persons = storage.getPersons();
		persons.add(person);
		storage.setPersons(persons);
		jsonStorage.writeStorage(storage);
		
		return Optional.of(person);
	}

	/**
	 * {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public Optional<List<Person>> findAll() throws StorageException {
		storage = jsonStorage.readStorage();
		return Optional.ofNullable(storage.getPersons());
	}

	/**
	 * {@inheritDoc}
	 * @param firstname {@inheritDoc}
	 * @param lastname {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public Optional<Person> findByFirstnameLastname(String firstname, String lastname) throws StorageException {
		storage = jsonStorage.readStorage();
		Person person = null;
		for (Person item : storage.getPersons()) {
			if (item.getFirstName().equals(firstname) && item.getLastName().equals(lastname)) {
				person = new Person(firstname, lastname, item.getAddress(), item.getCity(), item.getZip(), 
						item.getPhone(), item.getEmail());
				break;
			}
		}
		
		return Optional.ofNullable(person);
	}

	/**
	 * {@inheritDoc}
	 * @param address {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public Optional<List<Person>> findByAddress(String address) throws StorageException {
		storage = jsonStorage.readStorage();
		List<Person> results = null;
		for (Person item : storage.getPersons()) {
			if (item.getAddress().equals(address)) {
				if(results == null) {
					results = new ArrayList<>();
				}
				results.add(item);
			}
		}
		
		return Optional.ofNullable(results);
	}

	/**
	 * {@inheritDoc}
	 * @param city {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public Optional<List<Person>> findByCity(String city) throws StorageException {
		storage = jsonStorage.readStorage();
		List<Person> results = null;
		for (Person item : storage.getPersons()) {
			if (item.getCity().equals(city)) {
				if(results == null) {
					results = new ArrayList<>();
				}
				results.add(item);
			}
		}
		
		return Optional.ofNullable(results);
	}

	/**
	 * {@inheritDoc}
	 * @param person {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public Optional<Person> update(Person person) throws StorageException {
		storage = jsonStorage.readStorage();
		List<Person> persons = storage.getPersons();
		int i = -1;
		
		for(int j = 0; j < persons.size(); j++) {
			if(persons.get(j).getFirstName().equals(person.getFirstName()) && 
					persons.get(j).getLastName().equals(person.getLastName())) {
				i = j;
				break;
			}
		}
		
		if (i != -1) {
			persons.set(i, person);
			storage.setPersons(persons);
			jsonStorage.writeStorage(storage);
			return Optional.of(person);
		} else {
			return Optional.empty();
		}
	}

	/**
	 * {@inheritDoc}
	 * @param person {@inheritDoc}
	 */
	@Override
	public void delete(Person person) throws StorageException {
		storage = jsonStorage.readStorage();
		List<Person> persons = storage.getPersons();
		int i = -1;
		
		for(int j = 0; j < persons.size(); j++) {
			if(persons.get(j).getFirstName().equals(person.getFirstName()) && 
					persons.get(j).getLastName().equals(person.getLastName())) {
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
