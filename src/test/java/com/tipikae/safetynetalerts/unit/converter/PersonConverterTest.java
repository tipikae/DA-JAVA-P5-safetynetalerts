package com.tipikae.safetynetalerts.unit.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tipikae.safetynetalerts.converter.PersonConverter;
import com.tipikae.safetynetalerts.dto.PersonDTO;
import com.tipikae.safetynetalerts.model.Person;

public class PersonConverterTest {

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
	void testToDTO() {
		assertEquals(dto.getFirstName(), PersonConverter.toDTO(entity).getFirstName());
	}
	
	@Test
	void testToEntity() {
		assertEquals(entity.getCity(), PersonConverter.toEntity(dto).getCity());
	}
	
	@Test
	void testToDTOs() {
		assertEquals(dtos.get(0).getEmail(), PersonConverter.toDTOs(entities).get(0).getEmail());
	}
}
