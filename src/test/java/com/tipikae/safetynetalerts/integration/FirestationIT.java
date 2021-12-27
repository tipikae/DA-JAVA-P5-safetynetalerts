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
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.storage.JsonStorage;
import com.tipikae.safetynetalerts.storage.Storage;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class FirestationIT {
	
	@Autowired
    private MockMvc mockMvc;
	
	@BeforeAll
    private static void setUp() throws StorageException {
		JsonStorage jsonStorage = new JsonStorage();
		Storage storage = jsonStorage.readStorage();
    	storage.setFirestations(new ArrayList<Firestation>());
        jsonStorage.writeStorage(storage);
    }
	
	@Test
    @Order(1)
	void testAllFirestations_whenEmpty() throws Exception {
		mockMvc.perform(get("/firestations"))
        	.andExpect(status().isOk());
	}

	@Test
	@Order(2)
	void testAddFirestationMapping_whenOk() throws Exception {
		mockMvc.perform(post("/firestations")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"3200 chemin de Pale\", \"station\":\"3\" }"))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.station", is(3)));	
	}
	
	@Test
    @Order(3)
	void testAllFirestations_whenOk() throws Exception {
		mockMvc.perform(get("/firestations"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$[0].station", is(3)));
	}
	
	@Test
	@Order(4)
	void testUpdateFirestationMapping_whenOk() throws Exception {
		mockMvc.perform(put("/firestations/3200 chemin de Pale")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"3200 chemin de Pale\", \"station\":\"1\" }"))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.station", is(1)));
	}
	
	@Test
	@Order(5)
	void testUpdateFirestationMapping_whenNull() throws Exception {
		mockMvc.perform(put("/firestations/route de Pale")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"route de Pale\", \"station\":\"1\" }"))
			.andExpect(status().is(404));
	}
	
	@Test
	@Order(6)
	void testFirestationsByStation_whenOk() throws Exception {
		mockMvc.perform(get("/firestations/search?station=1"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$[0].station", is(1)));
	}
	
	@Test
	@Order(7)
	void testFirestationsByStation_whenNull() throws Exception {
		mockMvc.perform(get("/firestations/search?station=2"))
        	.andExpect(status().is(404));
	}
	
	@Test
	@Order(8)
	void testFirestationByAddress_whenOk() throws Exception {
		mockMvc.perform(get("/firestations/search?address=3200 chemin de Pale"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$.station", is(1)));
	}
	
	@Test
	@Order(9)
	void testFirestationByAddress_whenNull() throws Exception {
		mockMvc.perform(get("/firestations/search?address=route de Pale"))
        	.andExpect(status().is(404));
	}

	@Test
	@Order(10)
	void testAddFirestationMapping_whenNull() throws Exception {
		mockMvc.perform(post("/firestations")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
			.andExpect(status().is(400));	
	}
	
	@Test
	@Order(11)
	void testDeleteFirestationsByStation_whenOk() throws Exception {
		mockMvc.perform(delete("/firestations?station=1"))
			.andExpect(status().isOk());
	}
	
	@Test
	@Order(12)
	void testDeleteFirestationsByStation_whenNull() throws Exception {
		mockMvc.perform(delete("/firestations?station=10"))
			.andExpect(status().is(404));
	}
	
	@Test
	@Order(13)
	void testDeleteFirestationsByAddress_whenOk() throws Exception {
		testAddFirestationMapping_whenOk();
		mockMvc.perform(delete("/firestations/3200 chemin de Pale"))
			.andExpect(status().isOk());
	}
	
	@Test
	@Order(14)
	void testDeleteFirestationsByAddress_whenNull() throws Exception {
		mockMvc.perform(delete("/firestations/route de Pale"))
			.andExpect(status().is(404));
	}
}
