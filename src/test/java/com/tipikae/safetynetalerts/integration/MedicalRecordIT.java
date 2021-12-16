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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.model.Storage;
import com.tipikae.safetynetalerts.util.JsonStorage;

@SpringBootTest(properties= {"storage.file=storage/data-test.json"})
@AutoConfigureMockMvc
class MedicalRecordIT {

	@Autowired
    private MockMvc mockMvc;
	
	private static JsonStorage jsonStorage;
	
	@BeforeAll
    private static void setUp() {
		jsonStorage = new JsonStorage();
    }

    @BeforeEach
    private void setUpPerTest() {
        Storage storage = jsonStorage.readStorage();
        storage.setMedicalRecords(new ArrayList<MedicalRecord>());
        jsonStorage.writeStorage(storage);
    }
    
    @Test
	void testAllMedicalRecords_whenEmpty() throws Exception {
		mockMvc.perform(get("/medicalrecords"))
        	.andExpect(status().is(204));
	}
	
	@Test
	void testAllMedicalRecords_whenOk() throws Exception {
		testAddMedicalRecord_whenOk();
		mockMvc.perform(get("/medicalrecords"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$[0].firstname", is("John")));
	}
	
	@Test
	void testMedicalRecordByName_whenOk() throws Exception {
		testAddMedicalRecord_whenOk();
		mockMvc.perform(get("/medicalrecords?firstname=John&lastname=Boyd"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$.firstname", is("John")));
	}
	
	@Test
	void testMedicalRecordByName_whenNull() throws Exception {
		testAddMedicalRecord_whenOk();
		mockMvc.perform(get("/medicalrecords?firstname=Bob&lastname=BOB"))
        	.andExpect(status().is(204));
	}

	@Test
	void testAddMedicalRecord_whenOk() throws Exception {
		mockMvc.perform(post("/medicalrecords")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }"))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.firstname", is("John")));	
	}

	@Test
	void testAddMedicalRecord_whenNull() throws Exception {
		mockMvc.perform(post("/medicalrecords")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
			.andExpect(status().is(204));	
	}
	
	@Test
	void testUpdateMedicalRecord_whenOk() throws Exception {
		testAddMedicalRecord_whenOk();
		mockMvc.perform(post("/medicalrecords")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:400mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }"))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.station", is(1)));
	}
	
	@Test
	void testUpdateMedicalRecord_whenNull() throws Exception {
		testAddMedicalRecord_whenOk();
		mockMvc.perform(put("/medicalrecords")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"Bob\", \"lastName\":\"BOB\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }"))
			.andExpect(status().is(304));
	}
	
	@Test
	void testDeleteMedicalRecord_whenOk() throws Exception {
		testAddMedicalRecord_whenOk();
		mockMvc.perform(delete("/medicalrecords?firstname=John&lastname=Boyd"))
			.andExpect(status().isOk());
	}
	
	@Test
	void testDeleteMedicalRecord_whenNull() throws Exception {
		testAddMedicalRecord_whenOk();
		mockMvc.perform(delete("/medicalrecords?firstname=Bob&lastname=BOB"))
			.andExpect(status().is(304));
	}
}
