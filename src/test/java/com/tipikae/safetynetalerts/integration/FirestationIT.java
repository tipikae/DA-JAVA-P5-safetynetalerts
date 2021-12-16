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

import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.model.Storage;
import com.tipikae.safetynetalerts.util.JsonStorage;

@SpringBootTest(properties= {"storage.file=storage/data-test.json"})
@AutoConfigureMockMvc
class FirestationIT {

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
        storage.setFirestations(new ArrayList<Firestation>());
        jsonStorage.writeStorage(storage);
    }
	
	@Test
	void testAllFirestations_whenEmpty() throws Exception {
		mockMvc.perform(get("/firestations"))
        	.andExpect(status().is(204));
	}
	
	@Test
	void testAllFirestations_whenOk() throws Exception {
		testAddFirestationMapping_whenOk();
		mockMvc.perform(get("/firestations"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$[0].station", is(3)));
	}
	
	@Test
	void testFirestationsByStation_whenOk() throws Exception {
		testAddFirestationMapping_whenOk();
		mockMvc.perform(get("/firestations/3"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$[0].station", is(3)));
	}
	
	@Test
	void testFirestationsByStation_whenNull() throws Exception {
		testAddFirestationMapping_whenOk();
		mockMvc.perform(get("/firestations/2"))
        	.andExpect(status().is(204));
	}
	
	@Test
	void testFirestationByAddress_whenOk() throws Exception {
		testAddFirestationMapping_whenOk();
		mockMvc.perform(get("/firestation?address=3200 chemin de Pâle"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$.station", is(3)));
	}
	
	@Test
	void testFirestationByAddress_whenNull() throws Exception {
		testAddFirestationMapping_whenOk();
		mockMvc.perform(get("/firestation?address=route de Pâle"))
        	.andExpect(status().is(204));
	}

	@Test
	void testAddFirestationMapping_whenOk() throws Exception {
		mockMvc.perform(post("/firestations")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"3200 chemin de Pâle\", \"station\":\"3\" }"))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.station", is(3)));	
	}

	@Test
	void testAddFirestationMapping_whenNull() throws Exception {
		mockMvc.perform(post("/firestations")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
			.andExpect(status().is(204));	
	}
	
	@Test
	void testUpdateFirestationMapping_whenOk() throws Exception {
		testAddFirestationMapping_whenOk();
		mockMvc.perform(post("/firestations")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"3200 chemin de Pâle\", \"station\":\"1\" }"))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.station", is(1)));
	}
	
	@Test
	void testUpdateFirestationMapping_whenNull() throws Exception {
		testAddFirestationMapping_whenOk();
		mockMvc.perform(put("/firestations")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"route de Pâle\", \"station\":\"1\" }"))
			.andExpect(status().is(304));
	}
	
	@Test
	void testDeleteFirestationsByStation_whenOk() throws Exception {
		testAddFirestationMapping_whenOk();
		mockMvc.perform(delete("/firestations/3"))
			.andExpect(status().isOk());
	}
	
	@Test
	void testDeleteFirestationsByStation_whenNull() throws Exception {
		testAddFirestationMapping_whenOk();
		mockMvc.perform(delete("/firestations/1"))
			.andExpect(status().is(304));
	}
	
	@Test
	void testDeleteFirestationsByAddress_whenOk() throws Exception {
		testAddFirestationMapping_whenOk();
		mockMvc.perform(delete("/firestation?address=3200 chemin de Pâle"))
			.andExpect(status().isOk());
	}
	
	@Test
	void testDeleteFirestationsByAddress_whenNull() throws Exception {
		testAddFirestationMapping_whenOk();
		mockMvc.perform(delete("/firestation?address=route de Pâle"))
			.andExpect(status().is(304));
	}
}
