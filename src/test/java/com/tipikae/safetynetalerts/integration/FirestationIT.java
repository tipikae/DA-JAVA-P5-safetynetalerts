package com.tipikae.safetynetalerts.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.storage.IStorage;
import com.tipikae.safetynetalerts.storage.Storage;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class FirestationIT {
	
	@Autowired
    private MockMvc mockMvc;
	
	@BeforeAll
    private static void init(@Autowired IStorage jsonStorage) throws StorageException {
		Storage storage = jsonStorage.readStorage();
    	storage.setFirestations(new ArrayList<Firestation>());
        jsonStorage.writeStorage(storage);
    }

	@Test
	@Order(1)
	void testAddFirestationMapping_whenOk() throws Exception {
		mockMvc.perform(post("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"3200 chemin de Pale\", \"station\":\"3\" }"))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.station", is(3)));	
	}
	
	@Test
	@Order(2)
	void testUpdateFirestationMapping_whenOk() throws Exception {
		mockMvc.perform(put("/firestation/3200 chemin de Pale")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"3200 chemin de Pale\", \"station\":\"1\" }"))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.station", is(1)));
	}
	
	@Test
	@Order(3)
	void testUpdateFirestationMapping_whenNull() throws Exception {
		mockMvc.perform(put("/firestation/route de Pale")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"route de Pale\", \"station\":\"1\" }"))
			.andExpect(status().is(404));
	}

	@Test
	@Order(4)
	void testAddFirestationMapping_whenNull() throws Exception {
		mockMvc.perform(post("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
			.andExpect(status().is(400));	
	}
	
	@Test
	@Order(5)
	void testDeleteFirestationsByStation_whenOk() throws Exception {
		mockMvc.perform(delete("/firestation?station=1"))
			.andExpect(status().isOk());
	}
	
	@Test
	@Order(6)
	void testDeleteFirestationsByStation_whenNull() throws Exception {
		mockMvc.perform(delete("/firestation?station=10"))
			.andExpect(status().is(404));
	}
	
	@Test
	@Order(7)
	void testDeleteFirestationsByAddress_whenOk() throws Exception {
		testAddFirestationMapping_whenOk();
		mockMvc.perform(delete("/firestation/3200 chemin de Pale"))
			.andExpect(status().isOk());
	}
	
	@Test
	@Order(8)
	void testDeleteFirestationsByAddress_whenNull() throws Exception {
		mockMvc.perform(delete("/firestation/route de Pale"))
			.andExpect(status().is(404));
	}
}
