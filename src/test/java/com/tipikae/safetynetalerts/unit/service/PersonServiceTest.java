package com.tipikae.safetynetalerts.unit.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.safetynetalerts.dao.IPersonDAO;
import com.tipikae.safetynetalerts.dto.PersonDTO;
import com.tipikae.safetynetalerts.dtoconverter.IPersonConverter;
import com.tipikae.safetynetalerts.exception.ConverterException;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Person;
import com.tipikae.safetynetalerts.service.PersonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	
	@Mock
	private IPersonDAO dao;
	@Mock
	private IPersonConverter converter;
	
	@InjectMocks
	private static PersonServiceImpl service;
	private static Person person;
	private static PersonDTO personDTO;
	
	@BeforeAll
	private static void setUp() {
		service = new PersonServiceImpl();
		person = new Person("Bob", "BOB", "route de pale", "Paris", "75000", "123456789", "bob@bob.com");
		personDTO = new PersonDTO("Bob", "BOB", "route de pale", "Paris", "75000", "123456789", "bob@bob.com");
	}

	@Test
	void testAddPerson_whenException() throws StorageException, ConverterException {
		when(converter.toEntity(personDTO)).thenReturn(person);
		doThrow(StorageException.class).when(dao).save(person);
		assertThrows(StorageException.class, () -> service.addPerson(personDTO));
	}

	@Test
	void testUpdatePerson_whenException() throws StorageException, ConverterException {
		when(converter.toEntity(personDTO)).thenReturn(person);
		doThrow(StorageException.class).when(dao).findByFirstnameLastname(anyString(), anyString());
		assertThrows(StorageException.class, ()-> service.updatePerson("Bob", "BOB", personDTO));
	}
	
	@Test
	void testUpdatePerson_wheNull() throws StorageException, ConverterException {
		when(converter.toEntity(personDTO)).thenReturn(person);
		when(dao.findByFirstnameLastname(anyString(), anyString())).thenReturn(Optional.empty());
		assertThrows(ServiceException.class, ()-> service.updatePerson("Bob", "BOB", personDTO));
	}
	
	@Test
	void testDeletePerson_whenException() throws StorageException {
		doThrow(StorageException.class).when(dao).findByFirstnameLastname(anyString(), anyString());
		assertThrows(StorageException.class, ()-> service.deletePerson("Bob", "BOB"));
	}
	
	@Test
	void testDeletePerson_whenNull() throws StorageException {
		when(dao.findByFirstnameLastname(anyString(), anyString())).thenReturn(Optional.empty());
		assertThrows(ServiceException.class, ()-> service.deletePerson("Bob", "BOB"));
	}
}
