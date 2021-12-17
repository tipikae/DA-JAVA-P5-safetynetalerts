package com.tipikae.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.safetynetalerts.dao.IPersonDAO;
import com.tipikae.safetynetalerts.model.Person;

@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private IPersonDAO personDao;

	@Override
	public Person addPerson(Person person) {
		return personDao.save(person);
	}
	
	@Override
	public List<Person> getPersons() {
		return personDao.findAll();
	}

	@Override
	public List<Person> getPersonsByAddress(String address) {
		return personDao.findByAddress(address);
	}

	@Override
	public List<Person> getPersonsByCity(String city) {
		return personDao.findByCity(city);
	}

	@Override
	public Person getPersonByName(String firstname, String lastname) {
		return personDao.findByName(firstname, lastname);
	}

	@Override
	public boolean updatePerson(String firstname, String lastname, Person person) {
		return personDao.update(firstname, lastname, person);
	}

	@Override
	public boolean deletePerson(String firstname, String lastname) {
		return personDao.deleteByName(firstname, lastname);
	}

}
