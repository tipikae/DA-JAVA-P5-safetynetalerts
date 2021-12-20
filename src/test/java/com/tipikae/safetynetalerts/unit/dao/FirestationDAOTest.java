package com.tipikae.safetynetalerts.unit.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.safetynetalerts.dao.FirestationDAOImpl;
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
	private static Firestation emptyFirestation;
	private static Firestation wrongFirestation;
	
	@BeforeAll
	private static void setUp() {
		dao = new FirestationDAOImpl();
		firestation = new Firestation("route", 1);
		updatedFirestation = new Firestation("route", 2);
		emptyFirestation = new Firestation("", 0);
		wrongFirestation = new Firestation("chemin", 2);
	}

	@Test
	void testSave_whenOk() {
		when(jsonStorage.writeStorage(any(Storage.class))).thenReturn(true);
		dao.setJsonStorage(jsonStorage);
		assertEquals(firestation, dao.save(firestation));
	}

	@Test
	void testSave_whenNull() {
		assertNull(dao.save(emptyFirestation));
	}

	@Test
	void testSave_whenError() {
		when(jsonStorage.writeStorage(any(Storage.class))).thenReturn(false);
		dao.setJsonStorage(jsonStorage);
		assertNull(dao.save(firestation));
	}

	@Test
	void testFindByAddress_whenNull() {
		assertNull(dao.findByAddress(""));
	}

	@Test
	void testFindByStation_whenNull() {
		assertNull(dao.findByStation(0));
	}

	@Test
	void testFindAll_whenNull() {
		when(storage.getFirestations()).thenReturn(null);
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
		assertNull(dao.findAll());
	}
	
	@Test
	void testUpdate_whenOk() {
		List<Firestation> firestations = new ArrayList<>();
		firestations.add(firestation);
		when(storage.getFirestations()).thenReturn(firestations);
		when(jsonStorage.writeStorage(any(Storage.class))).thenReturn(true);
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
		assertEquals(true, dao.update("route", updatedFirestation));
	}
	
	@Test
	void testUpdate_whenNull() {
		assertEquals(false, dao.update("route", emptyFirestation));
	}
	
	@Test
	void testUpdate_whenNotFound() {
		List<Firestation> firestations = new ArrayList<>();
		firestations.add(firestation);
		when(storage.getFirestations()).thenReturn(firestations);
		dao.setStorage(storage);
		assertEquals(false, dao.update("chemin", wrongFirestation));
	}
	
	@Test
	void testDeleteByAddress_whenOk() {
		List<Firestation> firestations = new ArrayList<>();
		firestations.add(firestation);
		when(storage.getFirestations()).thenReturn(firestations);
		when(jsonStorage.writeStorage(any(Storage.class))).thenReturn(true);
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
		assertEquals(true, dao.deleteByAddress("route"));
	}
	
	@Test
	void testDeleteByAddress_whenNull() {
		assertEquals(false, dao.deleteByAddress(""));
	}
	
	@Test
	void testDeleteByAddress_whenNotFound() {
		List<Firestation> firestations = new ArrayList<>();
		firestations.add(firestation);
		when(storage.getFirestations()).thenReturn(firestations);
		dao.setStorage(storage);
		assertEquals(false, dao.deleteByAddress("chemin"));
	}
	
	@Test
	void testDeleteByStation_whenOk() {
		List<Firestation> firestations = new ArrayList<>();
		firestations.add(firestation);
		when(storage.getFirestations()).thenReturn(firestations);
		when(jsonStorage.writeStorage(any(Storage.class))).thenReturn(true);
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
		assertEquals(true, dao.deleteByStation(1));
	}
	
	@Test
	void testDeleteByStation_whenNull() {
		assertEquals(false, dao.deleteByStation(0));
	}
	
	@Test
	void testDeleteByStation_whenNotFound() {
		List<Firestation> firestations = new ArrayList<>();
		firestations.add(firestation);
		when(storage.getFirestations()).thenReturn(firestations);
		dao.setStorage(storage);
		assertEquals(false, dao.deleteByStation(3));
	}
}
