package com.tipikae.safetynetalerts.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.model.Person;
import com.tipikae.safetynetalerts.storage.JsonStorage;
import com.tipikae.safetynetalerts.storage.Storage;

@SpringBootTest(properties= {"storage.file=storage/data-test.json"})
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class InformationIT {

	@Autowired
    private MockMvc mockMvc;
	
	@BeforeAll
    private static void setUp() throws StorageException {
		JsonStorage jsonStorage = new JsonStorage();
		Storage storage = jsonStorage.readStorage();
    	storage.setFirestations(new ArrayList<Firestation>());
    	storage.setPersons(new ArrayList<Person>());
    	storage.setMedicalRecords(new ArrayList<MedicalRecord>());
        jsonStorage.writeStorage(storage);
    }
	
	@Test
	@Order(1)
	void testAddFirestationMapping1() throws Exception {
		mockMvc.perform(post("/firestations")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"route\", \"station\":\"1\" }"))
			.andExpect(status().isOk());	
	}
	
	@Test
	@Order(2)
	void testAddFirestationMapping2() throws Exception {
		mockMvc.perform(post("/firestations")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"chemin\", \"station\":\"1\" }"))
			.andExpect(status().isOk());	
	}

	@Test
	@Order(3)
	void testAddPerson1() throws Exception {
		mockMvc.perform(post("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstname\":\"Bob\", \"lastname\":\"BOB\", \"address\":\"route\", \"city\":\"Paris\", \"zip\":\"75000\", \"phone\":\"123456\", \"email\":\"bob@bob.com\" }"))
			.andExpect(status().isOk());	
	}

	@Test
	@Order(4)
	void testAddPerson2() throws Exception {
		mockMvc.perform(post("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstname\":\"Alice\", \"lastname\":\"BOB\", \"address\":\"route\", \"city\":\"Paris\", \"zip\":\"75000\", \"phone\":\"123456\", \"email\":\"bob@bob.com\" }"))
			.andExpect(status().isOk());	
	}

	@Test
	@Order(5)
	void testAddPerson3() throws Exception {
		mockMvc.perform(post("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstname\":\"Prout\", \"lastname\":\"Pouet\", \"address\":\"chemin\", \"city\":\"Paris\", \"zip\":\"75000\", \"phone\":\"123456\", \"email\":\"bob@bob.com\" }"))
			.andExpect(status().isOk());	
	}

	@Test
    @Order(6)
	void testAddMedicalRecord1() throws Exception {
		mockMvc.perform(post("/medicalrecords")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstname\":\"Bob\", \"lastname\":\"BOB\", \"birthdate\":\"1973-09-05\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }"))
			.andExpect(status().isOk());	
	}

	@Test
    @Order(7)
	void testAddMedicalRecord2() throws Exception {
		mockMvc.perform(post("/medicalrecords")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstname\":\"Alice\", \"lastname\":\"BOB\", \"birthdate\":\"2005-09-05\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[] }"))
			.andExpect(status().isOk());	
	}

	@Test
    @Order(8)
	void testAddMedicalRecord3() throws Exception {
		mockMvc.perform(post("/medicalrecords")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstname\":\"Prout\", \"lastname\":\"Pouet\", \"birthdate\":\"1985-09-05\", \"medications\":[], \"allergies\":[] }"))
			.andExpect(status().isOk());	
	}

	@Test
    @Order(9)
	void testResidentsByStation() throws Exception {
		mockMvc.perform(get("/firestation?stationNumber=1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.adults", is(2)))
			.andExpect(jsonPath("$.children", is(1)));	
	}

	@Test
    @Order(10)
	void testChildrenByAddress() throws Exception {
		mockMvc.perform(get("/childAlert?address=route"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.children[0].age", is(16)));	
	}

	@Test
    @Order(11)
	void testPhoneNumbersByStation() throws Exception {
		mockMvc.perform(get("/phoneAlert?firestation=1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.phones[0].phone", is("123456")));	
	}

	@Test
    @Order(12)
	void testMembersByAddress() throws Exception {
		mockMvc.perform(get("/fire?address=route"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.station", is(1)));	
	}

	@Test
    @Order(13)
	void testResidentsByStations() throws Exception {
		mockMvc.perform(get("/flood/stations?stations=1,2"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].adresses[0].address", is("route")));	
	}

	@Test
    @Order(14)
	void testPersonInfoByLastname() throws Exception {
		mockMvc.perform(get("/personInfo?firstname=Bob&lastname=BOB"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.persons[1].firstname", is("Alice")));	
	}

	@Test
    @Order(15)
	void testEmailsByCity() throws Exception {
		mockMvc.perform(get("/communityEmail?city=Paris"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.emails[0].email", is("bob@bob.com")));	
	}
}
