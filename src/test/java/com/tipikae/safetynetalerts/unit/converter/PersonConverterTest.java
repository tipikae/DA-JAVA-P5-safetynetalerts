package com.tipikae.safetynetalerts.unit.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.safetynetalerts.dto.PersonDTO;
import com.tipikae.safetynetalerts.dtoconverter.IPersonConverter;
import com.tipikae.safetynetalerts.exception.ConverterException;
import com.tipikae.safetynetalerts.model.Person;

@SpringBootTest
public class PersonConverterTest {

	@Autowired
	private IPersonConverter converter;
	
	private static Person entity;
	private static PersonDTO dto;
	private static List<Person> entities;
	private static List<PersonDTO> dtos;
	
	@BeforeAll
	private static void setUp() {
		entity = new Person("Bob", "BOB", "route", "Paris", "75000", "123456", "bob@bob.com");
		dto = new PersonDTO("Bob", "BOB", "route", "Paris", "75000", "123456", "bob@bob.com");
		entities = new ArrayList<>();
		entities.add(entity);
		dtos = new ArrayList<>();
		dtos.add(dto);
	}

	@Test
	void testToDTO() throws ConverterException {
		assertEquals(dto.getFirstName(), converter.toDTO(entity).getFirstName());
	}
	
	@Test
	void testToEntity() throws ConverterException {
		assertEquals(entity.getCity(), converter.toEntity(dto).getCity());
	}
	
	@Test
	void testToDTOs() throws ConverterException {
		assertEquals(dtos.get(0).getEmail(), converter.toDTOs(entities).get(0).getEmail());
	}
}
