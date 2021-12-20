package com.tipikae.safetynetalerts.unit.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.safetynetalerts.dao.MedicalRecordDAOImpl;
import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.storage.JsonStorage;
import com.tipikae.safetynetalerts.storage.Storage;

@ExtendWith(MockitoExtension.class)
class MedicalRecordDAOTest {
	
	@Mock
	private JsonStorage jsonStorage;
	
	@Mock
	private Storage storage;
	
	private static MedicalRecordDAOImpl dao;
	private static MedicalRecord medicalRecord;
	private static MedicalRecord updatedMedicalRecord;
	private static MedicalRecord emptyMedicalRecord;
	private static MedicalRecord wrongMedicalRecord;
	
	@BeforeAll
	private static void setUp() {
		dao = new MedicalRecordDAOImpl();
		medicalRecord = new MedicalRecord("Bob", "BOB", new Date(), new ArrayList<String>(), 
				new ArrayList<String>());
		updatedMedicalRecord = new MedicalRecord("Bob", "BOB", new Date(), List.of("Doliprane:500mg"), 
				new ArrayList<String>());
		emptyMedicalRecord = new MedicalRecord("", "", null, null, null);
		wrongMedicalRecord = new MedicalRecord("Alice", "BOB", new Date(), new ArrayList<String>(), 
				new ArrayList<String>());
	}

	@Test
	void testSave_whenOk() {
		when(jsonStorage.writeStorage(any(Storage.class))).thenReturn(true);
		dao.setJsonStorage(jsonStorage);
		assertEquals(medicalRecord, dao.save(medicalRecord));
	}

	@Test
	void testSave_whenNull() {
		assertNull(dao.save(emptyMedicalRecord));
	}

	@Test
	void testSave_whenError() {
		when(jsonStorage.writeStorage(any(Storage.class))).thenReturn(false);
		dao.setJsonStorage(jsonStorage);
		assertNull(dao.save(medicalRecord));
	}

	@Test
	void testFindAll_whenNull() {
		when(storage.getMedicalRecords()).thenReturn(null);
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
		assertNull(dao.findAll());
	}

	@Test
	void testFindByName_whenNull() {
		assertNull(dao.findByName("", ""));
	}
	
	@Test
	void testUpdate_whenOk() {
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(medicalRecord);
		when(storage.getMedicalRecords()).thenReturn(medicalRecords);
		when(jsonStorage.writeStorage(any(Storage.class))).thenReturn(true);
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
		assertEquals(true, dao.update("Bob", "BOB", updatedMedicalRecord));
	}
	
	@Test
	void testUpdate_whenNull() {
		assertEquals(false, dao.update("Bob", "BOB", emptyMedicalRecord));
	}
	
	@Test
	void testUpdate_whenNotFound() {
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(medicalRecord);
		when(storage.getMedicalRecords()).thenReturn(medicalRecords);
		dao.setStorage(storage);
		assertEquals(false, dao.update("Alice", "BOB", wrongMedicalRecord));
	}
}
