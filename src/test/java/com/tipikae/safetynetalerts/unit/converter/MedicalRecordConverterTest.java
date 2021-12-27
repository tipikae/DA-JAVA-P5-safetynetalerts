package com.tipikae.safetynetalerts.unit.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tipikae.safetynetalerts.dto.MedicalRecordDTO;
import com.tipikae.safetynetalerts.dtoconverter.MedicalRecordConverter;
import com.tipikae.safetynetalerts.model.MedicalRecord;

public class MedicalRecordConverterTest {

	private static MedicalRecord entity;
	private static MedicalRecordDTO dto;
	private static List<MedicalRecord> entities;
	private static List<MedicalRecordDTO> dtos;
	
	@BeforeAll
	private static void setUp() {
		
		entity = new MedicalRecord("Bob", "BOB", LocalDate.of(1973, 9, 5), new ArrayList<String>(),
				new ArrayList<String>());
		dto = new MedicalRecordDTO("Bob", "BOB", LocalDate.of(1973, 9, 5), new ArrayList<String>(),
				new ArrayList<String>());
		entities = new ArrayList<>();
		entities.add(entity);
		dtos = new ArrayList<>();
		dtos.add(dto);
	}

	@Test
	void testToDTO() {
		assertEquals(dto.getFirstName(), MedicalRecordConverter.toDTO(entity).getFirstName());
	}
	
	@Test
	void testToEntity() {
		assertEquals(entity.getBirthdate(), MedicalRecordConverter.toEntity(dto).getBirthdate());
	}
	
	@Test
	void testToDTOs() {
		assertEquals(dtos.get(0).getAllergies(), MedicalRecordConverter.toDTOs(entities).get(0).getAllergies());
	}
}
