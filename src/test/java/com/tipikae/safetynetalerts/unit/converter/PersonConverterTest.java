package com.tipikae.safetynetalerts.unit.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tipikae.safetynetalerts.dto.PersonDTO;
import com.tipikae.safetynetalerts.dtoconverter.PersonConverterImpl;
import com.tipikae.safetynetalerts.model.Person;

public class PersonConverterTest {

	private static PersonConverterImpl converter;
	private static Person entity;
	private static PersonDTO dto;
	private static List<Person> entities;
	private static List<PersonDTO> dtos;
	
	@BeforeAll
	private static void setUp() {
		converter = new PersonConverterImpl();
		entity = new Person("Bob", "BOB", "route", "Paris", "75000", "123456", "bob@bob.com");
		dto = new PersonDTO("Bob", "BOB", "route", "Paris", "75000", "123456", "bob@bob.com");
		entities = new ArrayList<>();
		entities.add(entity);
		dtos = new ArrayList<>();
		dtos.add(dto);
	}

	@Test
	void testToDTO() {
		assertEquals(dto.getFirstName(), converter.toDTO(entity).getFirstName());
	}
	
	@Test
	void testToEntity() {
		assertEquals(entity.getCity(), converter.toEntity(dto).getCity());
	}
	
	@Test
	void testToDTOs() {
		assertEquals(dtos.get(0).getEmail(), converter.toDTOs(entities).get(0).getEmail());
	}
}
