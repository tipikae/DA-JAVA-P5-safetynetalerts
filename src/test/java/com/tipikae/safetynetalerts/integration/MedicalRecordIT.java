package com.tipikae.safetynetalerts.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.storage.JsonStorage;
import com.tipikae.safetynetalerts.storage.Storage;

@SpringBootTest(properties= {"storage.file=storage/data-test.json"})
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class MedicalRecordIT {

	@Autowired
    private MockMvc mockMvc;
	
	@BeforeAll
    private static void setUp() {
		JsonStorage jsonStorage = new JsonStorage();
		Storage storage = jsonStorage.readStorage();
		storage.setMedicalRecords(new ArrayList<MedicalRecord>());
        jsonStorage.writeStorage(storage);
    }
    
    @Test
    @Order(1)
	void testAllMedicalRecords_whenEmpty() throws Exception {
		mockMvc.perform(get("/medicalrecords"))
        	.andExpect(status().is(204));
	}

	@Test
    @Order(2)
	void testAddMedicalRecord_whenOk() throws Exception {
		mockMvc.perform(post("/medicalrecords")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"1984-03-06\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }"))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.firstname", is("John")));	
	}
	
	@Test
    @Order(3)
	void testAllMedicalRecords_whenOk() throws Exception {
		mockMvc.perform(get("/medicalrecords"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$[0].firstname", is("John")));
	}
	
	@Test
    @Order(4)
	void testMedicalRecordByName_whenOk() throws Exception {
		mockMvc.perform(get("/medicalrecords?firstname=John&lastname=Boyd"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$.firstname", is("John")));
	}
	
	@Test
    @Order(5)
	void testUpdateMedicalRecord_whenOk() throws Exception {
		mockMvc.perform(put("/medicalrecords?firstname=John&lastname=Boyd")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"1984-03-06\", \"medications\":[\"aznol:400mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }"))
			.andExpect(status().isOk());
	}
	
	@Test
    @Order(6)
	void testUpdateMedicalRecord_whenNull() throws Exception {
		mockMvc.perform(put("/medicalrecords?firstname=Bob&lastname=BOB")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"Bob\", \"lastName\":\"BOB\", \"birthdate\":\"1984-03-06\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }"))
			.andExpect(status().is(304));
	}
	
	@Test
    @Order(7)
	void testMedicalRecordByName_whenNull() throws Exception {
		mockMvc.perform(get("/medicalrecords?firstname=Bob&lastname=BOB"))
        	.andExpect(status().is(204));
	}

	@Test
    @Order(8)
	void testAddMedicalRecord_whenNull() throws Exception {
		mockMvc.perform(post("/medicalrecords")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
			.andExpect(status().is(204));	
	}
	
	@Test
    @Order(9)
	void testDeleteMedicalRecord_whenOk() throws Exception {
		mockMvc.perform(delete("/medicalrecords?firstname=John&lastname=Boyd"))
			.andExpect(status().isOk());
	}
	
	@Test
    @Order(10)
	void testDeleteMedicalRecord_whenNull() throws Exception {
		mockMvc.perform(delete("/medicalrecords?firstname=Alice&lastname=BOB"))
			.andExpect(status().is(304));
	}
}
