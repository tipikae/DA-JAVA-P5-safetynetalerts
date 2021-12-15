package com.tipikae.safetynetalerts.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
class FirestationControllerIT {

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
        //.andExpect(jsonPath("$[0].firstName", is("Laurent")));
	}
	
	@Test
	void testAllFirestations_whenOk() throws Exception {
		testAddFirestationMapping_whenOk();
		mockMvc.perform(get("/firestations"))
        .andExpect(status().isOk());
	}

	@Test
	void testAddFirestationMapping_whenOk() throws Exception {
		mockMvc.perform(post("/firestations")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{ \"address\":\"3200 chemin de Pâle\", \"station\":\"3\" }"))
		.andExpect(status().isOk())
        .andExpect(jsonPath("$.firestations", is("{ \"address\":\"3200 chemin de Pâle\", \"station\":\"3\" }")));
			
	}
}
