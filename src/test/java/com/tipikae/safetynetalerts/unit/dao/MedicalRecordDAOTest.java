package com.tipikae.safetynetalerts.unit.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.safetynetalerts.dao.MedicalRecordDAOImpl;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.storage.JsonStorage;
import com.tipikae.safetynetalerts.storage.Storage;

@ExtendWith(MockitoExtension.class)
class MedicalRecordDAOTest {
	
	@InjectMocks
	private MedicalRecordDAOImpl dao;
	
	@Mock
	private JsonStorage jsonStorage;
	@Mock
	private Storage storage;
	
	private static MedicalRecord medicalRecord;
	private static MedicalRecord updatedMedicalRecord;
	
	@BeforeAll
	private static void setUp() {
		medicalRecord = new MedicalRecord("Bob", "BOB", LocalDate.now(), new ArrayList<String>(), 
				new ArrayList<String>());
		List<String> medications = new ArrayList<>();
		medications.add("Doliprane:500mg");
		updatedMedicalRecord = new MedicalRecord("Bob", "BOB", LocalDate.now(), medications, new ArrayList<String>());
	}

	@Test
	void testSave_whenOk() throws StorageException {
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(medicalRecord);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getMedicalRecords()).thenReturn(medicalRecords);
		assertEquals(medicalRecord, dao.save(medicalRecord));
	}

	@Test
	void testSave_whenException() throws StorageException {
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getMedicalRecords()).thenReturn(medicalRecords);
		doThrow(StorageException.class).when(jsonStorage).writeStorage(any(Storage.class));
		assertThrows(StorageException.class, () -> dao.save(medicalRecord));
	}

	@Test
	void testFindAll_whenException() throws StorageException {
		doThrow(StorageException.class).when(jsonStorage).readStorage();
		assertThrows(StorageException.class, () -> dao.findAll());
	}

	@Test
	void testFindByFirstnameLastname_whenException() throws StorageException {
		doThrow(StorageException.class).when(jsonStorage).readStorage();
		assertThrows(StorageException.class, () -> dao.findByFirstnameLastname("bob", "BOB"));
	}
	
	@Test
	void testUpdate_whenOk() throws StorageException {
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(medicalRecord);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getMedicalRecords()).thenReturn(medicalRecords);
		MedicalRecord result = dao.update(updatedMedicalRecord);
		assertEquals(updatedMedicalRecord, result);
	}
	
	@Test
	void testUpdate_whenException() throws StorageException {
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(medicalRecord);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getMedicalRecords()).thenReturn(medicalRecords);
		doThrow(StorageException.class).when(jsonStorage).writeStorage(any(Storage.class));
		assertThrows(StorageException.class, () -> dao.update(medicalRecord));
	}
	
	@Test
	void testDelete_whenOk() throws StorageException {
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(medicalRecord);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getMedicalRecords()).thenReturn(medicalRecords);
		dao.delete(medicalRecord);
		verify(jsonStorage, Mockito.times(1)).writeStorage(storage);
	}
	
	@Test
	void testDelete_whenException() throws StorageException {
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(medicalRecord);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getMedicalRecords()).thenReturn(medicalRecords);
		doThrow(StorageException.class).when(jsonStorage).writeStorage(any(Storage.class));
		assertThrows(StorageException.class, () -> dao.delete(medicalRecord));
	}
}
