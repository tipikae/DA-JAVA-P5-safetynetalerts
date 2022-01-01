package com.tipikae.safetynetalerts.unit.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.safetynetalerts.dao.IPersonDAO;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Person;
import com.tipikae.safetynetalerts.service.PersonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	
	@Mock
	private IPersonDAO dao;
	
	@InjectMocks
	private static PersonServiceImpl service;
	private static Person person;
	
	@BeforeAll
	private static void setUp() {
		service = new PersonServiceImpl();
		person = new Person("Bob", "BOB", "route de pale", "Paris", "75000", "123456789", "bob@bob.com");
	}

	@Test
	void testAddPerson_whenException() throws StorageException {
		doThrow(StorageException.class).when(dao).save(person);
		assertThrows(StorageException.class, () -> service.addPerson(person));
	}

	@Test
	void testUpdatePerson_whenException() throws StorageException {
		doThrow(StorageException.class).when(dao).findByFirstnameLastname(anyString(), anyString());
		assertThrows(StorageException.class, ()-> service.updatePerson("Bob", "BOB", person));
	}
	
	@Test
	void testUpdatePerson_wheNull() throws StorageException {
		when(dao.findByFirstnameLastname(anyString(), anyString())).thenReturn(null);
		assertThrows(ServiceException.class, ()-> service.updatePerson("Bob", "BOB", person));
	}
	
	@Test
	void testDeletePerson_whenException() throws StorageException {
		doThrow(StorageException.class).when(dao).findByFirstnameLastname(anyString(), anyString());
		assertThrows(StorageException.class, ()-> service.deletePerson("Bob", "BOB"));
	}
	
	@Test
	void testDeletePerson_whenNull() throws StorageException {
		when(dao.findByFirstnameLastname(anyString(), anyString())).thenReturn(null);
		assertThrows(ServiceException.class, ()-> service.deletePerson("Bob", "BOB"));
	}
}
