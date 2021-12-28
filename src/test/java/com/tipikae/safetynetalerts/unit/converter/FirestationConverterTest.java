package com.tipikae.safetynetalerts.unit.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tipikae.safetynetalerts.dto.FirestationDTO;
import com.tipikae.safetynetalerts.dtoconverter.FirestationConverterImpl;
import com.tipikae.safetynetalerts.model.Firestation;

class FirestationConverterTest {

	private static FirestationConverterImpl converter;
	private static Firestation entity;
	private static FirestationDTO dto;
	private static List<Firestation> entities;
	private static List<FirestationDTO> dtos;
	
	@BeforeAll
	private static void setUp() {
		converter = new FirestationConverterImpl();
		entity = new Firestation("route", 1);
		dto = new FirestationDTO("route", 1);
		entities = new ArrayList<>();
		entities.add(entity);
		dtos = new ArrayList<>();
		dtos.add(dto);
	}

	@Test
	void testToDTO() {
		assertEquals(dto.getAddress(), converter.toDTO(entity).getAddress());
	}
	
	@Test
	void testToEntity() {
		assertEquals(entity.getStation(), converter.toEntity(dto).getStation());
	}
	
	@Test
	void testToDTOs() {
		assertEquals(dtos.get(0).getStation(), converter.toDTOs(entities).get(0).getStation());
	}

}
