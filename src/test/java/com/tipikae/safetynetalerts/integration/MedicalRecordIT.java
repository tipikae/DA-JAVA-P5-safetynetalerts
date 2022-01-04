package com.tipikae.safetynetalerts.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.storage.IStorage;
import com.tipikae.safetynetalerts.storage.Storage;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class MedicalRecordIT {

	@Autowired
    private MockMvc mockMvc;
	
	@BeforeAll
	private static void init(@Autowired IStorage jsonStorage) throws StorageException {
		Storage storage = jsonStorage.readStorage();
		storage.setMedicalRecords(new ArrayList<MedicalRecord>());
        jsonStorage.writeStorage(storage);
    }
    
    @Test
    @Order(1)
	void testAddMedicalRecord_whenOk() throws Exception {
		mockMvc.perform(post("/medicalrecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"1984-03-06\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }"))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.firstName", is("John")));	
	}
	
	@Test
    @Order(2)
	void testUpdateMedicalRecord_whenOk() throws Exception {
		mockMvc.perform(put("/medicalrecord?firstName=John&lastName=Boyd")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"1984-03-06\", \"medications\":[\"aznol:400mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }"))
			.andExpect(status().isOk());
	}
	
	@Test
    @Order(3)
	void testUpdateMedicalRecord_whenNull() throws Exception {
		mockMvc.perform(put("/medicalrecord?firstName=Bob&lastName=BOB")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"Bob\", \"lastName\":\"BOB\", \"birthdate\":\"1984-03-06\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }"))
			.andExpect(status().is(404));
	}
	
	@Test
    @Order(4)
	void testAddMedicalRecord_whenNull() throws Exception {
		mockMvc.perform(post("/medicalrecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
			.andExpect(status().is(400));	
	}
	
	@Test
    @Order(5)
	void testDeleteMedicalRecord_whenOk() throws Exception {
		mockMvc.perform(delete("/medicalrecord?firstName=John&lastName=Boyd"))
			.andExpect(status().isOk());
	}
	
	@Test
    @Order(6)
	void testDeleteMedicalRecord_whenNull() throws Exception {
		mockMvc.perform(delete("/medicalrecord?firstName=Alice&lastName=BOB"))
			.andExpect(status().is(404));
	}
}
