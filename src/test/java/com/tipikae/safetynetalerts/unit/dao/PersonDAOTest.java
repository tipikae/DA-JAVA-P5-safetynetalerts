package com.tipikae.safetynetalerts.unit.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.safetynetalerts.dao.PersonDAOImpl;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Person;
import com.tipikae.safetynetalerts.storage.JsonStorage;
import com.tipikae.safetynetalerts.storage.Storage;

@ExtendWith(MockitoExtension.class)
class PersonDAOTest {
	
	@InjectMocks
	private PersonDAOImpl dao;

	@Mock
	private JsonStorage jsonStorage;
	@Mock
	private Storage storage;
	
	private static Person person;
	private static Person updatedPerson;
	
	@BeforeAll
	private static void setUp() {
		person = new Person("Bob", "BOB", "route de la soie", "Paris", "75000", "841-874-6512", "bob@bob.com");
		updatedPerson = new Person("Bob", "BOB", "avenue", "Paris", "75000", "841-874-6512", "bob@bob.com");
	}

	@Test
	void testSave_whenOk() throws StorageException {
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getPersons()).thenReturn(persons);
		assertEquals(person, dao.save(person));
	}

	@Test
	void testSave_whenException() throws StorageException {
		List<Person> persons = new ArrayList<>();
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getPersons()).thenReturn(persons);
		doThrow(StorageException.class).when(jsonStorage).writeStorage(any(Storage.class));
		assertThrows(StorageException.class, () -> dao.save(person));
	}

	@Test
	void testFindAll_whenOk() throws StorageException {
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getPersons()).thenReturn(persons);
		assertEquals(1, dao.findAll().size());
	}

	@Test
	void testFindAll_whenException() throws StorageException {
		doThrow(StorageException.class).when(jsonStorage).readStorage();
		assertThrows(StorageException.class, () -> dao.findAll());
	}

	@Test
	void testFindByFirstnameLastname_whenOk() throws StorageException {
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getPersons()).thenReturn(persons);
		assertEquals(person.getAddress(), dao.findByFirstnameLastname("Bob", "BOB").get().getAddress());
	}

	@Test
	void testFindByFirstnameLastname_whenNotFound() throws StorageException {
		List<Person> persons = new ArrayList<>();
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getPersons()).thenReturn(persons);
		assertFalse(dao.findByFirstnameLastname("bob", "BOB").isPresent());
	}

	@Test
	void testFindByFirstnameLastname_whenException() throws StorageException {
		doThrow(StorageException.class).when(jsonStorage).readStorage();
		assertThrows(StorageException.class, () -> dao.findByFirstnameLastname("bob", "BOB"));
	}

	@Test
	void testFindByCity_whenOk() throws StorageException {
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getPersons()).thenReturn(persons);
		assertEquals(person.getAddress(), dao.findByCity("Paris").get(0).getAddress());
	}

	@Test
	void testFindByCity_whenEmpty() throws StorageException {
		List<Person> persons = new ArrayList<>();
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getPersons()).thenReturn(persons);
		assertNull(dao.findByCity("Paris"));
	}

	@Test
	void testFindByCity_whenException() throws StorageException {
		doThrow(StorageException.class).when(jsonStorage).readStorage();
		assertThrows(StorageException.class, () -> dao.findByCity("Paris"));
	}

	@Test
	void testFindByAddress_whenOk() throws StorageException {
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getPersons()).thenReturn(persons);
		assertEquals(person.getAddress(), dao.findByAddress("route de la soie").get(0).getAddress());
	}

	@Test
	void testFindByAddress_whenEmpty() throws StorageException {
		List<Person> persons = new ArrayList<>();
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getPersons()).thenReturn(persons);
		assertNull(dao.findByAddress("route de pale"));
	}

	@Test
	void testFindByAddress_whenException() throws StorageException {
		doThrow(StorageException.class).when(jsonStorage).readStorage();
		assertThrows(StorageException.class, () -> dao.findByAddress("route de pale"));
	}
	
	@Test
	void testUpdate_whenOk() throws StorageException {
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getPersons()).thenReturn(persons);
		Person result = dao.update(updatedPerson);
		assertEquals(updatedPerson.getFirstName(),result.getFirstName());
		assertEquals(updatedPerson.getLastName(),result.getLastName());
	}
	
	@Test
	void testUpdate_whenException() throws StorageException {
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getPersons()).thenReturn(persons);
		doThrow(StorageException.class).when(jsonStorage).writeStorage(any(Storage.class));
		assertThrows(StorageException.class, () -> dao.update(person));
	}
	
	@Test
	void testDelete_whenOk() throws StorageException {
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getPersons()).thenReturn(persons);
		dao.delete(person);
		verify(jsonStorage, Mockito.times(1)).writeStorage(storage);
	}
	
	@Test
	void testDelete_whenException() throws StorageException {
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getPersons()).thenReturn(persons);
		doThrow(StorageException.class).when(jsonStorage).writeStorage(any(Storage.class));
		assertThrows(StorageException.class, () -> dao.delete(person));
	}
}
