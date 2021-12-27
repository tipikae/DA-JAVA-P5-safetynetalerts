package com.tipikae.safetynetalerts.unit.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tipikae.safetynetalerts.converter.FirestationConverter;
import com.tipikae.safetynetalerts.dto.FirestationDTO;
import com.tipikae.safetynetalerts.model.Firestation;

class FirestationConverterTest {
	
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
	void testToDTO() {
		assertEquals(dto.getAddress(), FirestationConverter.toDTO(entity).getAddress());
	}
	
	@Test
	void testToEntity() {
		assertEquals(entity.getStation(), FirestationConverter.toEntity(dto).getStation());
	}
	
	@Test
	void testToDTOs() {
		assertEquals(dtos.get(0).getStation(), FirestationConverter.toDTOs(entities).get(0).getStation());
	}

}
