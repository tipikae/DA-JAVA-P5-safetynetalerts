package com.tipikae.safetynetalerts.unit.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.safetynetalerts.dao.IMedicalRecordDAO;
import com.tipikae.safetynetalerts.dto.MedicalRecordDTO;
import com.tipikae.safetynetalerts.dtoconverter.IMedicalRecordConverter;
import com.tipikae.safetynetalerts.exception.ConverterException;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.service.MedicalRecordServiceImpl;

@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceTest {
	
	@Mock
	private IMedicalRecordDAO dao;
	@Mock
	private IMedicalRecordConverter converter;
	
	@InjectMocks
	private static MedicalRecordServiceImpl service;
	
	private static MedicalRecord medicalRecord;
	private static MedicalRecordDTO medicalRecordDTO;
	
	@BeforeAll
	private static void setUp() {
		service = new MedicalRecordServiceImpl();
		medicalRecord = new MedicalRecord("Bob", "BOB", LocalDate.of(1980, 2, 23), new ArrayList<String>(), 
				new ArrayList<String>());
		medicalRecordDTO = new MedicalRecordDTO("Bob", "BOB", LocalDate.of(1980, 2, 23), new ArrayList<String>(), 
				new ArrayList<String>());
	}

	@Test
	void testAddMedicalRecord_whenException() throws StorageException, ConverterException {
		when(converter.toEntity(medicalRecordDTO)).thenReturn(medicalRecord);
		when(dao.findByFirstnameLastname(anyString(), anyString())).thenReturn(Optional.empty());
		doThrow(StorageException.class).when(dao).save(medicalRecord);
		assertThrows(StorageException.class, () -> service.addMedicalRecord(medicalRecordDTO));
	}

	@Test
	void testUpdateMedicalRecord_whenException() throws StorageException, ConverterException {
		when(converter.toEntity(medicalRecordDTO)).thenReturn(medicalRecord);
		doThrow(StorageException.class).when(dao).findByFirstnameLastname(anyString(), anyString());
		assertThrows(StorageException.class, ()-> service.updateMedicalRecord("Bob", "BOB", medicalRecordDTO));
	}
	
	@Test
	void testUpdateMedicalRecord_wheNull() throws StorageException, ConverterException {
		when(converter.toEntity(medicalRecordDTO)).thenReturn(medicalRecord);
		when(dao.findByFirstnameLastname(anyString(), anyString())).thenReturn(Optional.empty());
		assertThrows(ServiceException.class, ()-> service.updateMedicalRecord("Bob", "BOB", medicalRecordDTO));
	}
	
	@Test
	void testDeleteMedicalRecord_whenException() throws StorageException {
		doThrow(StorageException.class).when(dao).findByFirstnameLastname(anyString(), anyString());
		assertThrows(StorageException.class, ()-> service.deleteMedicalRecord("Bob", "BOB"));
	}
	
	@Test
	void testDeleteMedicalRecord_whenNull() throws StorageException {
		when(dao.findByFirstnameLastname(anyString(), anyString())).thenReturn(Optional.empty());
		assertThrows(ServiceException.class, ()-> service.deleteMedicalRecord("Bob", "BOB"));
	}

}
