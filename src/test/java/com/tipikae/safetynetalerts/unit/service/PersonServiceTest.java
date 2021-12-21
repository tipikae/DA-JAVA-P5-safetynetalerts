package com.tipikae.safetynetalerts.unit.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.safetynetalerts.dao.FirestationDAOImpl;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.service.FirestationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	
	@Mock
	private FirestationDAOImpl dao;
	
	private static FirestationServiceImpl service;
	private static Firestation firestation;
	
	@BeforeAll
	private static void setUp() {
		service = new FirestationServiceImpl();
		firestation = new Firestation("route", 1);
	}

	@Test
	void testAddFirestation_whenException() throws StorageException {
		doThrow(StorageException.class).when(dao).save(firestation);
		service.setFirestationDao(dao);
		assertThrows(StorageException.class, () -> service.addFirestationMapping(firestation));
	}

	@Test
	void testGetFirestations_whenException() throws StorageException {
		doThrow(StorageException.class).when(dao).findAll();
		service.setFirestationDao(dao);
		assertThrows(StorageException.class, () -> service.getFirestations());
	}

	@Test
	void testGetFirestationByAddress_whenException() throws StorageException {
		doThrow(StorageException.class).when(dao).findByAddress(anyString());
		service.setFirestationDao(dao);
		assertThrows(StorageException.class, () -> service.getFirestationByAddress("route"));
	}

	@Test
	void testGetFirestationByAddress_whenNull() throws StorageException {
		when(dao.findByAddress(anyString())).thenReturn(null);
		service.setFirestationDao(dao);
		assertThrows(ServiceException.class, () -> service.getFirestationByAddress("route"));
	}

	@Test
	void testGetFirestationByStation_whenException() throws StorageException {
		doThrow(StorageException.class).when(dao).findAll();
		service.setFirestationDao(dao);
		assertThrows(StorageException.class, () -> service.getFirestationsByStation(1));
	}

	@Test
	void testGetFirestationByStation_whenNull() throws StorageException {
		when(dao.findAll()).thenReturn(new ArrayList<Firestation>());
		service.setFirestationDao(dao);
		assertThrows(ServiceException.class, () -> service.getFirestationsByStation(1));
	}
	
	@Test
	void testUpdateFirestationMapping_whenException() throws StorageException {
		doThrow(StorageException.class).when(dao).findByAddress(anyString());
		service.setFirestationDao(dao);
		assertThrows(StorageException.class, ()-> service.updateFirestationMapping("route", firestation));
	}
	
	@Test
	void testUpdateFirestationMapping_wheNull() throws StorageException {
		when(dao.findByAddress(anyString())).thenReturn(null);
		service.setFirestationDao(dao);
		assertThrows(ServiceException.class, ()-> service.updateFirestationMapping("route", firestation));
	}
	
	@Test
	void testDeleteFirestationByAddress_whenException() throws StorageException {
		doThrow(StorageException.class).when(dao).findByAddress(anyString());
		service.setFirestationDao(dao);
		assertThrows(StorageException.class, ()-> service.deleteFirestationByAddress("route"));
	}
	
	@Test
	void testDeleteFirestationByAddress_whenNull() throws StorageException {
		when(dao.findByAddress(anyString())).thenReturn(null);
		service.setFirestationDao(dao);
		assertThrows(ServiceException.class, ()-> service.deleteFirestationByAddress("route"));
	}
	
	@Test
	void testDeleteFirestationsByStation_whenException() throws StorageException {
		doThrow(StorageException.class).when(dao).findByStation(anyInt());
		service.setFirestationDao(dao);
		assertThrows(StorageException.class, ()-> service.deleteFirestationsByStation(1));
	}
	
	@Test
	void testDeleteFirestationsByStation_whenNull() throws StorageException {
		when(dao.findByStation(anyInt())).thenReturn(new ArrayList<Firestation>());
		service.setFirestationDao(dao);
		assertThrows(ServiceException.class, ()-> service.deleteFirestationsByStation(1));
	}
}
