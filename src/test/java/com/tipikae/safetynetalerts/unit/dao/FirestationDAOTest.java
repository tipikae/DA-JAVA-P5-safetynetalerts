package com.tipikae.safetynetalerts.unit.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.safetynetalerts.dao.FirestationDAOImpl;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.storage.JsonStorage;
import com.tipikae.safetynetalerts.storage.Storage;

@ExtendWith(MockitoExtension.class)
class FirestationDAOTest {
	
	@Mock
	private static JsonStorage jsonStorage;
	
	@Mock
	private static Storage storage;
	
	private static FirestationDAOImpl dao;
	private static Firestation firestation;
	private static Firestation updatedFirestation;
	
	@BeforeAll
	private static void setUp() throws Exception {
		dao = new FirestationDAOImpl();
		firestation = new Firestation("route", 1);
		updatedFirestation = new Firestation("route", 2);
	}

	@Test
	void testSave_whenOk() throws StorageException {
		List<Firestation> firestations = new ArrayList<>();
		firestations.add(firestation);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getFirestations()).thenReturn(firestations);
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
		assertEquals(firestation, dao.save(firestation).get());
	}

	@Test
	void testSave_whenException() throws StorageException {
		List<Firestation> firestations = new ArrayList<>();
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getFirestations()).thenReturn(firestations);
		doThrow(StorageException.class).when(jsonStorage).writeStorage(any(Storage.class));
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
		assertThrows(StorageException.class, () -> dao.save(firestation).get());
	}

	@Test
	void testFindAll_whenException() throws StorageException {
		doThrow(StorageException.class).when(jsonStorage).readStorage();
		dao.setJsonStorage(jsonStorage);
		assertThrows(StorageException.class, () -> dao.findAll().get());
	}

	@Test
	void testFindByAddress_whenException() throws StorageException {
		doThrow(StorageException.class).when(jsonStorage).readStorage();
		dao.setJsonStorage(jsonStorage);
		assertThrows(StorageException.class, () -> dao.findByAddress("route").get());
	}

	@Test
	void testFindByStation_whenException() throws StorageException {
		doThrow(StorageException.class).when(jsonStorage).readStorage();
		dao.setJsonStorage(jsonStorage);
		assertThrows(StorageException.class, () -> dao.findByStation(1).get());
	}
	
	@Test
	void testUpdate_whenOk() throws StorageException {
		List<Firestation> firestations = new ArrayList<>();
		firestations.add(firestation);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getFirestations()).thenReturn(firestations);
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
		Firestation result = dao.update(updatedFirestation).get();
		assertEquals(updatedFirestation.getAddress(),result.getAddress());
		assertEquals(updatedFirestation.getStation(),result.getStation());
	}
	
	@Test
	void testUpdate_whenException() throws StorageException {
		List<Firestation> firestations = new ArrayList<>();
		firestations.add(firestation);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getFirestations()).thenReturn(firestations);
		doThrow(StorageException.class).when(jsonStorage).writeStorage(any(Storage.class));
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
		assertThrows(StorageException.class, () -> dao.update(firestation).get());
	}
	
	@Test
	void testDelete_whenOk() throws StorageException {
		List<Firestation> firestations = new ArrayList<>();
		firestations.add(firestation);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getFirestations()).thenReturn(firestations);
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
		dao.delete(firestation);
		verify(jsonStorage, Mockito.times(1)).writeStorage(storage);
	}
	
	@Test
	void testDelete_whenException() throws StorageException {
		List<Firestation> firestations = new ArrayList<>();
		firestations.add(firestation);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getFirestations()).thenReturn(firestations);
		doThrow(StorageException.class).when(jsonStorage).writeStorage(any(Storage.class));
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
		assertThrows(StorageException.class, () -> dao.delete(firestation));
	}
	
	@Test
	void testDeleteFirestations_whenOk() throws StorageException {
		List<Firestation> firestations = new ArrayList<>();
		firestations.add(firestation);
		List<Firestation> firestationsToRemove = new ArrayList<>();
		firestationsToRemove.add(firestation);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getFirestations()).thenReturn(firestations);
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
		dao.deleteFirestations(firestationsToRemove);
		verify(jsonStorage, Mockito.times(1)).writeStorage(storage);
	}
	
	@Test
	void testDeleteFirestations_whenException() throws StorageException {
		List<Firestation> firestations = new ArrayList<>();
		firestations.add(firestation);
		List<Firestation> firestationsToRemove = new ArrayList<>();
		firestationsToRemove.add(firestation);
		when(jsonStorage.readStorage()).thenReturn(storage);
		when(storage.getFirestations()).thenReturn(firestations);
		doThrow(StorageException.class).when(jsonStorage).writeStorage(any(Storage.class));
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
		assertThrows(StorageException.class, () -> dao.deleteFirestations(firestationsToRemove));
	}
}
