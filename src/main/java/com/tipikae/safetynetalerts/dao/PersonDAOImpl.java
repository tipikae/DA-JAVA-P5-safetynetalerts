package com.tipikae.safetynetalerts.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tipikae.safetynetalerts.models.Person;
import com.tipikae.safetynetalerts.util.JsonStorage;

@Repository
public class PersonDAOImpl implements IPersonDAO {

	@Override
	public Person save(Person person) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Person findByName(String firstname, String lastname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findByAddress(String address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findAll() {
		JsonStorage jsonStorage = new JsonStorage();
		return jsonStorage.readStorage().getPersons();
	}

	@Override
	public void update(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByName(String firstname, String lastname) {
		// TODO Auto-generated method stub
		
	}

}
