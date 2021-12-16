package com.tipikae.safetynetalerts.unit.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.safetynetalerts.dao.PersonDAOImpl;
import com.tipikae.safetynetalerts.model.Person;
import com.tipikae.safetynetalerts.model.Storage;
import com.tipikae.safetynetalerts.util.JsonStorage;

@ExtendWith(MockitoExtension.class)
class PersonDAOTest {

	@Mock
	private JsonStorage jsonStorage;
	
	@Mock
	private Storage storage;
	
	private static PersonDAOImpl dao;
	private static Person person;
	private static Person updatedPerson;
	private static Person emptyPerson;
	private static Person wrongPerson;
	
	@BeforeAll
	private static void setUp() {
		dao = new PersonDAOImpl();
		person = new Person("Bob", "BOB", "route de la soie", "Paris", "75000", "841-874-6512", "bob@bob.com");
		updatedPerson = new Person("Bob", "BOB", "avenue", "Paris", "75000", "841-874-6512", "bob@bob.com");
		emptyPerson = new Person("", "", "", "", "", "", "");
		wrongPerson = new Person("Alice", "BOB", "route de la soie", "Paris", "75000", 
				"841-874-6512", "bob@bob.com");
	}

	@Test
	void testSave_whenOk() {
		when(jsonStorage.writeStorage(any(Storage.class))).thenReturn(true);
		dao.setJsonStorage(jsonStorage);
		assertEquals(person, dao.save(person));
	}

	@Test
	void testSave_whenNull() {
		assertNull(dao.save(emptyPerson));
	}

	@Test
	void testSave_whenError() {
		when(jsonStorage.writeStorage(any(Storage.class))).thenReturn(false);
		dao.setJsonStorage(jsonStorage);
		assertNull(dao.save(person));
	}

	@Test
	void testFindByAddress_whenNull() {
		assertNull(dao.findByAddress(""));
	}

	@Test
	void testFindByName_whenNull() {
		assertNull(dao.findByName("", ""));
	}

	@Test
	void testFindByCity_whenNull() {
		assertNull(dao.findByCity(""));
	}

	@Test
	void testFindAll_whenNull() {
		when(storage.getPersons()).thenReturn(null);
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
		assertNull(dao.findAll());
	}
	
	@Test
	void testUpdate_whenOk() {
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		when(storage.getPersons()).thenReturn(persons);
		when(jsonStorage.writeStorage(any(Storage.class))).thenReturn(true);
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
		assertEquals(true, dao.update(updatedPerson));
	}
	
	@Test
	void testUpdate_whenNull() {
		assertEquals(false, dao.update(emptyPerson));
	}
	
	@Test
	void testUpdate_whenNotFound() {
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		when(storage.getPersons()).thenReturn(persons);
		dao.setStorage(storage);
		assertEquals(false, dao.update(wrongPerson));
	}
	
	@Test
	void testDeleteByName_whenOk() {
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		when(storage.getPersons()).thenReturn(persons);
		when(jsonStorage.writeStorage(any(Storage.class))).thenReturn(true);
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
		assertEquals(true, dao.deleteByName("Bob", "BOB"));
	}
	
	@Test
	void testDeleteByName_whenNull() {
		assertEquals(false, dao.deleteByName("", ""));
	}
	
	@Test
	void testDeleteByName_whenNotFound() {
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		when(storage.getPersons()).thenReturn(persons);
		dao.setStorage(storage);
		assertEquals(false, dao.deleteByName("Bobby", "BOB"));
	}
}
