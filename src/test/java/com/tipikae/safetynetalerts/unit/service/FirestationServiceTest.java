package com.tipikae.safetynetalerts.unit.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.safetynetalerts.dao.IFirestationDAO;
import com.tipikae.safetynetalerts.dto.FirestationDTO;
import com.tipikae.safetynetalerts.dtoconverter.IFirestationConverter;
import com.tipikae.safetynetalerts.exception.ConverterException;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.service.FirestationServiceImpl;

@ExtendWith(MockitoExtension.class)
class FirestationServiceTest {
	
	@Mock
	private IFirestationDAO dao;
	@Mock
	private IFirestationConverter converter;
	
	@InjectMocks
	private static FirestationServiceImpl service;
	
	private static FirestationDTO firestationDTO;
	private static Firestation firestation;
	
	@BeforeAll
	private static void setUp() {
		service = new FirestationServiceImpl();
		firestationDTO = new FirestationDTO("route", 1);
		firestation = new Firestation("route", 1);
	}

	@Test
	void testAddFirestation_whenOk() throws StorageException, ConverterException, ServiceException {
		when(converter.toEntity(any(FirestationDTO.class))).thenReturn(firestation);
		when(dao.findByAddress(anyString())).thenReturn(Optional.empty());
		when(dao.save(any(Firestation.class))).thenReturn(firestation);
		when(converter.toDTO(any(Firestation.class))).thenReturn(firestationDTO);
		FirestationDTO result = service.addFirestationMapping(firestationDTO);
		assertEquals(result.getAddress(), firestationDTO.getAddress());
	}

	@Test
	void testAddFirestation_whenServiceException() throws StorageException, ConverterException {
		when(converter.toEntity(firestationDTO)).thenReturn(firestation);
		when(dao.findByAddress(anyString())).thenReturn(Optional.of(firestation));
		assertThrows(ServiceException.class, () -> service.addFirestationMapping(firestationDTO));
	}

	@Test
	void testAddFirestation_whenStorageException() throws StorageException, ConverterException {
		when(converter.toEntity(firestationDTO)).thenReturn(firestation);
		when(dao.findByAddress(anyString())).thenReturn(Optional.empty());
		doThrow(StorageException.class).when(dao).save(firestation);
		assertThrows(StorageException.class, () -> service.addFirestationMapping(firestationDTO));
	}

	@Test
	void testUpdateFirestationMapping_whenOk() throws StorageException, ConverterException, ServiceException {
		when(converter.toEntity(any(FirestationDTO.class))).thenReturn(firestation);
		when(dao.findByAddress(anyString())).thenReturn(Optional.of(firestation));
		when(dao.update(any(Firestation.class))).thenReturn(firestation);
		when(converter.toDTO(any(Firestation.class))).thenReturn(firestationDTO);
		FirestationDTO result = service.updateFirestationMapping("route", firestationDTO);
		assertEquals(result.getAddress(), firestationDTO.getAddress());
	}

	@Test
	void testUpdateFirestationMapping_whenServiceException() throws StorageException, ConverterException {
		when(converter.toEntity(firestationDTO)).thenReturn(firestation);
		when(dao.findByAddress(anyString())).thenReturn(Optional.empty());
		assertThrows(ServiceException.class, () -> service.updateFirestationMapping("route", firestationDTO));
	}

	@Test
	void testUpdateFirestationMapping_whenStorageException() throws StorageException, ConverterException {
		when(converter.toEntity(firestationDTO)).thenReturn(firestation);
		doThrow(StorageException.class).when(dao).findByAddress(anyString());
		assertThrows(StorageException.class, ()-> service.updateFirestationMapping("route", firestationDTO));
	}
	
	@Test
	void testUpdateFirestationMapping_whenNull() throws StorageException, ConverterException {
		when(converter.toEntity(firestationDTO)).thenReturn(firestation);
		when(dao.findByAddress(anyString())).thenReturn(Optional.empty());
		assertThrows(ServiceException.class, ()-> service.updateFirestationMapping("route", firestationDTO));
	}
	
	@Test
	void testUpdateFirestationMapping_whenDifferent() throws StorageException, ConverterException {
		assertThrows(ServiceException.class, ()-> service.updateFirestationMapping("chemin", firestationDTO));
	}
	
	@Test
	void testDeleteFirestationByAddress_whenException() throws StorageException {
		doThrow(StorageException.class).when(dao).findByAddress(anyString());
		assertThrows(StorageException.class, ()-> service.deleteFirestationByAddress("route"));
	}
	
	@Test
	void testDeleteFirestationByAddress_whenNull() throws StorageException {
		when(dao.findByAddress(anyString())).thenReturn(Optional.empty());
		assertThrows(ServiceException.class, ()-> service.deleteFirestationByAddress("route"));
	}
	
	@Test
	void testDeleteFirestationsByStation_whenException() throws StorageException {
		doThrow(StorageException.class).when(dao).findByStation(anyInt());
		assertThrows(StorageException.class, ()-> service.deleteFirestationsByStation(1));
	}
	
	@Test
	void testDeleteFirestationsByStation_whenNull() throws StorageException {
		when(dao.findByStation(anyInt())).thenReturn(null);
		assertThrows(ServiceException.class, ()-> service.deleteFirestationsByStation(1));
	}
}
