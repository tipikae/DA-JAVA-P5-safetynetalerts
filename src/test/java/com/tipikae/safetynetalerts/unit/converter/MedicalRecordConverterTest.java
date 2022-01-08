package com.tipikae.safetynetalerts.unit.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.safetynetalerts.dto.MedicalRecordDTO;
import com.tipikae.safetynetalerts.dtoconverter.IMedicalRecordConverter;
import com.tipikae.safetynetalerts.exception.ConverterException;
import com.tipikae.safetynetalerts.model.MedicalRecord;

@SpringBootTest
public class MedicalRecordConverterTest {

	@Autowired
	private IMedicalRecordConverter converter;
	
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
	void testToDTO() throws ConverterException {
		assertEquals(dto.getFirstName(), converter.toDTO(entity).getFirstName());
	}
	
	@Test
	void testToEntity() throws ConverterException {
		assertEquals(entity.getBirthdate(), converter.toEntity(dto).getBirthdate());
	}
	
	@Test
	void testToDTOs() throws ConverterException {
		assertEquals(dtos.get(0).getAllergies(), converter.toDTOs(entities).get(0).getAllergies());
	}
	
	@Test
	void testToDTOsWhenNull() throws ConverterException {
		assertNull(converter.toDTOs(null));
	}
}
