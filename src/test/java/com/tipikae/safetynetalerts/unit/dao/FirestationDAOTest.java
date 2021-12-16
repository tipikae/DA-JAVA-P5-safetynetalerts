package com.tipikae.safetynetalerts.unit.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import com.tipikae.safetynetalerts.model.Storage;
import com.tipikae.safetynetalerts.util.JsonStorage;

@ExtendWith(MockitoExtension.class)
class FirestationDAOTest {
	
	@Mock
	private static JsonStorage jsonStorage;
	
	@Mock
	private static Storage storage;
	
	private static FirestationDAOImpl dao;
	
	@BeforeAll
	private static void setUp() {
		dao = new FirestationDAOImpl();
		dao.setJsonStorage(jsonStorage);
		dao.setStorage(storage);
	}

	@Test
	void testSave_whenOk() {
		Firestation firestation = new Firestation("route", 1);
		assertEquals(firestation, dao.save(firestation));
	}

	@Test
	void testSave_whenNull() {
		Firestation firestation = new Firestation("", 0);
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
	void testUpdate_whenOk() {
		testSave_whenOk();
		Firestation firestation = new Firestation("route", 2);
		assertEquals(true, dao.update(firestation));
	}
	
	@Test
	void testUpdate_whenNull() {
		Firestation firestation = new Firestation("", 0);
		assertEquals(false, dao.update(firestation));
	}
	
	@Test
	void testUpdate_whenNotFound() {
		testSave_whenOk();
		Firestation firestation = new Firestation("chemin", 2);
		assertEquals(false, dao.update(firestation));
	}
	
	@Test
	void testDeleteByAddress_whenOk() {
		testSave_whenOk();
		assertEquals(true, dao.deleteByAddress("route"));
	}
	
	@Test
	void testDeleteByAddress_whenNull() {
		assertEquals(false, dao.deleteByAddress(""));
	}
	
	@Test
	void testDeleteByAddress_whenNotFound() {
		testSave_whenOk();
		assertEquals(false, dao.deleteByAddress("chemin"));
	}
}
