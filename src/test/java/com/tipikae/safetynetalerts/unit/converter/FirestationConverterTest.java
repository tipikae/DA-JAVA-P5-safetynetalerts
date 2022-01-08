package com.tipikae.safetynetalerts.unit.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.safetynetalerts.dto.FirestationDTO;
import com.tipikae.safetynetalerts.dtoconverter.IFirestationConverter;
import com.tipikae.safetynetalerts.exception.ConverterException;
import com.tipikae.safetynetalerts.model.Firestation;

@SpringBootTest
class FirestationConverterTest {

	@Autowired
	private IFirestationConverter converter;
	
	private static Firestation entity;
	private static FirestationDTO dto;
	private static List<Firestation> entities;
	private static List<FirestationDTO> dtos;
	
	@BeforeAll
	private static void setUp() {
		entity = new Firestation("route", 1);
		dto = new FirestationDTO("route", 1);
		entities = new ArrayList<>();
		entities.add(entity);
		dtos = new ArrayList<>();
		dtos.add(dto);
	}

	@Test
	void testToDTO() throws ConverterException {
		assertEquals(dto.getAddress(), converter.toDTO(entity).getAddress());
	}
	
	@Test
	void testToEntity() throws ConverterException {
		assertEquals(entity.getStation(), converter.toEntity(dto).getStation());
	}
	
	@Test
	void testToDTOs() throws ConverterException {
		assertEquals(dtos.get(0).getStation(), converter.toDTOs(entities).get(0).getStation());
	}
	
	@Test
	void testToDTOsWhenNull() throws ConverterException {
		assertNull(converter.toDTOs(null));
	}

}
