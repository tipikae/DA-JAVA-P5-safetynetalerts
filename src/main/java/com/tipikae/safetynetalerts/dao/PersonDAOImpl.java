package com.tipikae.safetynetalerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tipikae.safetynetalerts.model.Person;

@Repository
public class PersonDAOImpl extends AbstractDAOImpl implements IPersonDAO {
	
	public PersonDAOImpl() {
		super();
	}

	@Override
	public Person save(Person person) {
		if (!person.getFirstname().equals("") && !person.getLastname().equals("") && 
				!person.getAddress().equals("") && !person.getCity().equals("") && !person.getZip().equals("") && 
				!person.getPhone().equals("") && !person.getEmail().equals("")) {
			
			if (storage != null) {
				List<Person> persons = storage.getPersons();
				if(persons == null) {
					persons = new ArrayList<>();
				}
				
				persons.add(person);
				storage.setPersons(persons);
				
				if (jsonStorage.writeStorage(storage)) {
					return person;
				}
			}
		}
		return null;
	}
	
	@Override
	public Person findByName(String firstname, String lastname) {
		Person person = null;
		if (firstname != "" && lastname != "") {
			List<Person> persons = findAll();
			if (persons != null && !persons.isEmpty()) {
				for (Person item : persons) {
					if (item.getFirstname().equals(firstname) && item.getLastname().equals(lastname)) {
						person = item;
						break;
					}
				}
			} 
		}
		return person;
	}

	@Override
	public List<Person> findByAddress(String address) {
		List<Person> results = null;
		if (address != "") {
			List<Person> persons = findAll();
			if (persons != null && !persons.isEmpty()) {
				results = new ArrayList<>();
				for (Person item : persons) {
					if (item.getAddress().equals(address)) {
						results.add(item);
					}
				}
			} 
		}
		return results;
	}

	@Override
	public List<Person> findByCity(String city) {
		List<Person> results = null;
		if (city != "") {
			List<Person> persons = findAll();
			if (persons != null && !persons.isEmpty()) {
				results = new ArrayList<>();
				for (Person item : persons) {
					if (item.getAddress().equals(city)) {
						results.add(item);
					}
				}
			} 
		}
		return results;
	}

	@Override
	public List<Person> findAll() {
		List<Person> persons = null;
		
		if(storage != null) {
			persons = storage.getPersons();
		}
		
		return persons;
	}

	@Override
	public boolean update(Person person) {
		if (!person.getFirstname().equals("") && !person.getLastname().equals("") && 
				!person.getAddress().equals("") && !person.getCity().equals("") && !person.getZip().equals("") && 
				!person.getPhone().equals("") && !person.getEmail().equals("")) {
			boolean found = false;
			
			if (storage!= null) {
				List<Person> persons = storage.getPersons();
				
				if (persons != null) {
					for (int i = 0; i < persons.size(); i++) {
						if (persons.get(i).getFirstname().equals(person.getFirstname()) && 
								persons.get(i).getLastname().equals(person.getLastname())) {
							found = true;
							persons.set(i, person);
							break;
						}
					}
					if (found) {
						storage.setPersons(persons);
						if (jsonStorage.writeStorage(storage)) {
							return true;
						}
					} 
				} 
			} 
		}
		return false;
	}

	@Override
	public boolean deleteByName(String firstname, String lastname) {
		if (firstname != "" && lastname != "") {
			boolean found = false;
			
			if (storage != null) {
				List<Person> persons = storage.getPersons();
				
				if (persons != null) {
					for (int i = 0; i < persons.size(); i++) {
						if (persons.get(i).getFirstname().equals(firstname) && 
								persons.get(i).getLastname().equals(lastname)) {
							found = true;
							persons.remove(i);
							break;
						}
					}
					if (found) {
						storage.setPersons(persons);
						if (jsonStorage.writeStorage(storage)) {
							return true;
						}
					} 
				} 
			} 
		}
		return false;
	}

}
