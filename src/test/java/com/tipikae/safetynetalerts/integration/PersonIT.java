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

import com.tipikae.safetynetalerts.model.Person;
import com.tipikae.safetynetalerts.model.Storage;
import com.tipikae.safetynetalerts.util.JsonStorage;

@SpringBootTest(properties= {"storage.file=storage/data-test.json"})
@AutoConfigureMockMvc
class PersonIT {
	
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
        storage.setPersons(new ArrayList<Person>());
        jsonStorage.writeStorage(storage);
    }

	@Test
	void testAllPersons_whenEmpty() throws Exception {
		mockMvc.perform(get("/persons"))
        	.andExpect(status().is(204));
	}
	
	@Test
	void testAllPersons_whenOk() throws Exception {
		testAddPerson_whenOk();
		mockMvc.perform(get("/persons"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$[0].firstname", is("John")));
	}
	
	@Test
	void testPersonsByAddress_whenOk() throws Exception {
		testAddPerson_whenOk();
		mockMvc.perform(get("/persons?address=1509 Culver St"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$.firstname", is("John")));
	}
	
	@Test
	void testPersonsByAddress_whenNull() throws Exception {
		testAddPerson_whenOk();
		mockMvc.perform(get("/persons?address=route de Pâle"))
        	.andExpect(status().is(204));
	}
	
	@Test
	void testPersonsByCity_whenOk() throws Exception {
		testAddPerson_whenOk();
		mockMvc.perform(get("/persons?city=Culver"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$.firstname", is("John")));
	}
	
	@Test
	void testPersonsByCity_whenNull() throws Exception {
		testAddPerson_whenOk();
		mockMvc.perform(get("/persons?city=Paris"))
        	.andExpect(status().is(204));
	}
	
	@Test
	void testPersonByName_whenOk() throws Exception {
		testAddPerson_whenOk();
		mockMvc.perform(get("/persons?firstname=John&lastname=Boyd"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$.firstname", is("John")));
	}
	
	@Test
	void testPersonByName_whenNull() throws Exception {
		testAddPerson_whenOk();
		mockMvc.perform(get("/persons?firstname=Bob&lastname=BOB"))
        	.andExpect(status().is(204));
	}

	@Test
	void testAddPerson_whenOk() throws Exception {
		mockMvc.perform(post("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }"))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.firstname", is("John")));	
	}

	@Test
	void testAddPerson_whenNull() throws Exception {
		mockMvc.perform(post("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
			.andExpect(status().is(204));	
	}
	
	@Test
	void testUpdatePerson_whenOk() throws Exception {
		testAddPerson_whenOk();
		mockMvc.perform(post("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Paris\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }"))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.station", is(1)));
	}
	
	@Test
	void testUpdatePerson_whenNull() throws Exception {
		testAddPerson_whenOk();
		mockMvc.perform(put("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"Bob\", \"lastName\":\"BOB\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }"))
			.andExpect(status().is(304));
	}
	
	@Test
	void testDeletePerson_whenOk() throws Exception {
		testAddPerson_whenOk();
		mockMvc.perform(delete("/persons?firstname=John&lastname=Boyd"))
			.andExpect(status().isOk());
	}
	
	@Test
	void testDeletePerson_whenNull() throws Exception {
		testAddPerson_whenOk();
		mockMvc.perform(delete("/persons?firstname=Bob&lastname=BOB"))
			.andExpect(status().is(304));
	}
}
