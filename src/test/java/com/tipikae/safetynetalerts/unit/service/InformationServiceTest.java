package com.tipikae.safetynetalerts.unit.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.safetynetalerts.dao.IFirestationDAO;
import com.tipikae.safetynetalerts.dao.IMedicalRecordDAO;
import com.tipikae.safetynetalerts.dao.IPersonDAO;
import com.tipikae.safetynetalerts.dto.ChildAlertDTO;
import com.tipikae.safetynetalerts.dto.CommunityEmailDTO;
import com.tipikae.safetynetalerts.dto.DTOResponse;
import com.tipikae.safetynetalerts.dto.FireDTO;
import com.tipikae.safetynetalerts.dto.FirestationInfoDTO;
import com.tipikae.safetynetalerts.dto.FloodDTO;
import com.tipikae.safetynetalerts.dto.FloodMaster;
import com.tipikae.safetynetalerts.dto.PersonInfoDTO;
import com.tipikae.safetynetalerts.dto.PhoneAlertDTO;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.model.Person;
import com.tipikae.safetynetalerts.service.InformationServiceImpl;

@ExtendWith(MockitoExtension.class)
class InformationServiceTest {
	
	@Mock
	private IFirestationDAO firestationDao;	
	@Mock
	private IPersonDAO personDao;	
	@Mock
	private IMedicalRecordDAO medicalRecordDao;
	
	@InjectMocks
	private static InformationServiceImpl service;
	
	private static List<Firestation> firestations;
	private static List<Person> persons;
	private static List<Person> personsByAddress1;
	private static List<Person> personsByAddress2;
	private static List<MedicalRecord> medicalRecords;
	private static Firestation fs1;
	private static MedicalRecord md1;
	private static MedicalRecord md2;
	private static MedicalRecord md3;
	
	@BeforeAll
	private static void setUp() {
		service = new InformationServiceImpl();
		fs1 = new Firestation("route", 1);
		Firestation fs2 = new Firestation("chemin", 1);
		firestations = new ArrayList<>();
		firestations.add(fs1);
		firestations.add(fs2);
		Person ps1 = new Person("Bob", "BOB", "route", "Paris", "75000", "123456789", "bob@bob.com");
		Person ps2 = new Person("Alice", "BOB", "route", "Paris", "75000", "123456789", "bob@bob.com");
		Person ps3 = new Person("Prout", "Pouet", "chemin", "Paris", "75000", "123456789", "bob@bob.com");
		persons = new ArrayList<>();
		persons.add(ps1);
		persons.add(ps2);
		persons.add(ps3);
		personsByAddress1 = new ArrayList<>();
		personsByAddress1.add(ps1);
		personsByAddress1.add(ps2);
		personsByAddress2 = new ArrayList<>();
		personsByAddress2.add(ps3);
		List<String> med1 = new ArrayList<>();
		med1.add("aspro:200mg");
		List<String> all1 = new ArrayList<>();
		all1.add("frites");
		md1 = new MedicalRecord("Bob", "BOB", LocalDate.of(1973, 9, 5), med1, all1);
		md2 = new MedicalRecord("Alice", "BOB", LocalDate.of(2005, 9, 5), med1, new ArrayList<String>());
		md3 = new MedicalRecord("Prout", "Pouet", LocalDate.of(1995, 9, 5), new ArrayList<String>(), 
				new ArrayList<String>());
		medicalRecords = new ArrayList<>();
		medicalRecords.add(md1);
		medicalRecords.add(md2);
		medicalRecords.add(md3);
	}

	@Test
	void testGetResidentsByStation_whenOk() throws StorageException, ServiceException {
		when(firestationDao.findByStation(anyInt())).thenReturn(Optional.of(firestations));
		when(personDao.findByAddress(anyString()))
			.thenReturn(Optional.of(personsByAddress1), Optional.of(personsByAddress2));
		when(medicalRecordDao.findByFirstnameLastname(anyString(), anyString()))
			.thenReturn(Optional.of(md1), Optional.of(md2), Optional.of(md3));
		
		FirestationInfoDTO dto = (FirestationInfoDTO) service.getResidentsByStation(1);
		assertEquals(2, dto.getAdults());
		assertEquals(1, dto.getChildren());
		assertEquals(1, dto.getStation());
		assertEquals("Bob", dto.getResidents().get(0).getFirstname());
	}
	
	@Test
	void testGetResidentsByStation_whenEmpty() throws StorageException, ServiceException {
		when(firestationDao.findByStation(anyInt())).thenReturn(Optional.of(firestations));
		when(personDao.findByAddress(anyString())).thenReturn(Optional.empty());
		
		FirestationInfoDTO dto = (FirestationInfoDTO) service.getResidentsByStation(1);
		assertEquals(0, dto.getAdults());
		assertEquals(0, dto.getChildren());
		assertEquals(1, dto.getStation());
		assertEquals(0, dto.getResidents().size());
	}
	
	@Test
	void testGetResidentsByStation_whenNoFoundStation() throws StorageException {
		when(firestationDao.findByStation(anyInt())).thenReturn(Optional.empty());
		assertNotEquals(new FloodDTO(null).getClass(), 
				service.getResidentsByStation(1).getClass());
		assertEquals(new DTOResponse().getClass(), service.getResidentsByStation(1).getClass());
	}
	
	@Test
	void testGetResidentsByStation_whenStorageException() throws StorageException {
		doThrow(StorageException.class).when(firestationDao).findByStation(anyInt());
		assertThrows(StorageException.class, () -> service.getResidentsByStation(1));
	}
	
	@Test
	void testGetChildrenByAddress_whenOk() throws StorageException, ServiceException {
		when(personDao.findByAddress(anyString())).thenReturn(Optional.of(personsByAddress1));
		when(medicalRecordDao.findByFirstnameLastname(anyString(), anyString()))
			.thenReturn(Optional.of(md1), Optional.of(md2));
		
		ChildAlertDTO dto = (ChildAlertDTO) service.getChildrenByAddress("route");
		assertEquals("route", dto.getAddress());
		assertEquals("Alice", dto.getChildren().get(0).getFirstname());
		assertEquals("Bob", dto.getAdults().get(0).getFirstname());
		assertEquals(1, dto.getChildren().size());
		assertEquals(1, dto.getAdults().size());
	}
	
	@Test
	void testGetChildrenByAddress_whenNotFound() throws StorageException {
		when(personDao.findByAddress(anyString())).thenReturn(Optional.empty());
		assertNotEquals(new ChildAlertDTO(null, null, null).getClass(), 
				service.getChildrenByAddress("avenue").getClass());
		assertEquals(new DTOResponse().getClass(), service.getChildrenByAddress("avenue").getClass());
	}
	
	@Test
	void testGetChildrenByAddress_whenStorageException() throws StorageException {
		doThrow(StorageException.class).when(personDao).findByAddress(anyString());
		assertThrows(StorageException.class, () -> service.getChildrenByAddress("avenue"));
	}
	
	@Test
	void testGetPhoneNumbersByStation_whenOk() throws StorageException, ServiceException {
		when(firestationDao.findByStation(anyInt())).thenReturn(Optional.of(firestations));
		when(personDao.findByAddress(anyString()))
			.thenReturn(Optional.of(personsByAddress1), Optional.of(personsByAddress2));
		
		PhoneAlertDTO dto = (PhoneAlertDTO) service.getPhoneNumbersByStation(1);
		assertEquals(3, dto.getPhones().size());
		assertEquals("123456789", dto.getPhones().get(0).getPhone());
	}
	
	@Test
	void testGetPhoneNumbersByStation_whenNotFound() throws StorageException, ServiceException {
		when(firestationDao.findByStation(anyInt())).thenReturn(Optional.empty());
		assertNotEquals(new PhoneAlertDTO(0, null).getClass(), service.getPhoneNumbersByStation(1).getClass());
		assertEquals(new DTOResponse().getClass(), service.getPhoneNumbersByStation(1).getClass());
	}
	
	@Test
	void testGetPhoneNumbersByStation_whenStorageException() throws StorageException {
		doThrow(StorageException.class).when(firestationDao).findByStation(anyInt());
		assertThrows(StorageException.class, () -> service.getPhoneNumbersByStation(1));
	}
	
	@Test
	void testGetMembersByAddress_whenOk() throws StorageException, ServiceException {
		when(personDao.findByAddress(anyString())).thenReturn(Optional.of(personsByAddress1));
		when(firestationDao.findByAddress(anyString())).thenReturn(Optional.of(fs1));
		when(medicalRecordDao.findByFirstnameLastname(anyString(), anyString()))
			.thenReturn(Optional.of(md1), Optional.of(md2));
		
		FireDTO dto = (FireDTO) service.getMembersByAddress("route");
		assertEquals("route", dto.getAddress());
		assertEquals(1, dto.getStation());
		assertEquals(2, dto.getMembers().size());
	}
	
	@Test
	void testGetMembersByAddress_whenNotFound() throws StorageException, ServiceException {
		when(personDao.findByAddress(anyString())).thenReturn(Optional.empty());
		assertNotEquals(new FireDTO(null, 0, null).getClass(), service.getMembersByAddress("route").getClass());
		assertEquals(new DTOResponse().getClass(), service.getMembersByAddress("route").getClass());
	}
	
	@Test
	void testGetMembersByAddress_whenStorageException() throws StorageException, ServiceException {
		doThrow(StorageException.class).when(personDao).findByAddress(anyString());
		assertThrows(StorageException.class, () -> service.getMembersByAddress("route"));
	}
	
	@Test
	void testGetResidentsByStations_whenOk() throws StorageException, ServiceException {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		when(firestationDao.findByStation(anyInt())).thenReturn(Optional.of(firestations));
		when(personDao.findByAddress(anyString()))
			.thenReturn(Optional.of(personsByAddress1), Optional.of(personsByAddress2));
		when(medicalRecordDao.findByFirstnameLastname(anyString(), anyString()))
			.thenReturn(Optional.of(md1), Optional.of(md2), Optional.of(md3));
		
		FloodDTO dtos = (FloodDTO) service.getResidentsByStations(list);
		FloodMaster dto = dtos.getFloods().get(0);
		assertEquals(1, dto.getStation());
		assertEquals("route", dto.getAdresses().get(0).getAddress());
		assertEquals(48, dto.getAdresses().get(0).getMembers().get(0).getAge());
	}
	
	@Test
	void testGetResidentsByStations_whenNotFound() throws StorageException, ServiceException {
		List<Integer> list = new ArrayList<>();
		list.add(10);
		when(firestationDao.findByStation(anyInt())).thenReturn(Optional.empty());
		assertNotEquals(new FirestationInfoDTO(0, 0, 0, null).getClass(), 
				service.getResidentsByStations(list).getClass());
		assertEquals(new DTOResponse().getClass(), service.getResidentsByStations(list).getClass());
	}
	
	@Test
	void testGetResidentsByStations_whenStorageException() throws StorageException, ServiceException {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		doThrow(StorageException.class).when(firestationDao).findByStation(anyInt());
		assertThrows(StorageException.class, () -> service.getResidentsByStations(list));
	}
	
	@Test
	void testGetPersonInfoByLastname_whenOk() throws StorageException, ServiceException {
		when(personDao.findAll()).thenReturn(Optional.of(personsByAddress1));
		when(medicalRecordDao.findByFirstnameLastname(anyString(), anyString()))
			.thenReturn(Optional.of(md1), Optional.of(md2));
		
		PersonInfoDTO dto = (PersonInfoDTO) service.getPersonInfoByLastname("Bob", "BOB");
		assertEquals(2, dto.getPersons().size());
		assertEquals(16, dto.getPersons().get(1).getAge());
	}
	
	@Test
	void testGetPersonInfoByLastname_whenNotFound() throws StorageException, ServiceException {
		when(personDao.findAll()).thenReturn(Optional.empty());
		assertNotEquals(new PersonInfoDTO(null, null).getClass(), 
				service.getPersonInfoByLastname("Bob", "BOB").getClass());
		assertEquals(new DTOResponse().getClass(), service.getPersonInfoByLastname("Bob", "BOB").getClass());
	}
	
	@Test
	void testGetPersonInfoByLastname_whenStorageException() throws StorageException, ServiceException {
		doThrow(StorageException.class).when(personDao).findAll();
		assertThrows(StorageException.class, () -> service.getPersonInfoByLastname("Bob", "BOB"));
	}
	
	@Test
	void testGetEmailsByCity_whenOk() throws StorageException, ServiceException {
		when(personDao.findByCity(anyString())).thenReturn(Optional.of(persons));
		
		CommunityEmailDTO dto = (CommunityEmailDTO) service.getEmailsByCity("Paris");
		assertEquals("bob@bob.com", dto.getEmails().get(0).getEmail());
	}
	
	@Test
	void testGetEmailsByCity_whenNotFound() throws StorageException, ServiceException {
		when(personDao.findByCity(anyString())).thenReturn(Optional.empty());
		assertNotEquals(new CommunityEmailDTO(null, null).getClass(), service.getEmailsByCity("Paris").getClass());
		assertEquals(new DTOResponse().getClass(), service.getEmailsByCity("Paris").getClass());
	}
	
	@Test
	void testGetEmailsByCity_whenStorageException() throws StorageException, ServiceException {
		doThrow(StorageException.class).when(personDao).findByCity(anyString());
		assertThrows(StorageException.class, () -> service.getEmailsByCity("Paris"));
	}

}
