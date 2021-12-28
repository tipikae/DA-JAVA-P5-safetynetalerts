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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Person;
import com.tipikae.safetynetalerts.storage.JsonStorage;
import com.tipikae.safetynetalerts.storage.Storage;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class PersonIT {
	
	@Autowired
    private MockMvc mockMvc;
	
	@BeforeAll
    private static void setUp() throws StorageException {
		JsonStorage jsonStorage = new JsonStorage();
		Storage storage = jsonStorage.readStorage();
    	storage.setPersons(new ArrayList<Person>());
        jsonStorage.writeStorage(storage);
    }

	@Test
	@Order(1)
	void testAllPersons_whenEmpty() throws Exception {
		mockMvc.perform(get("/persons"))
        	.andExpect(status().isOk());
	}

	@Test
	@Order(2)
	void testAddPerson_whenOk() throws Exception {
		mockMvc.perform(post("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }"))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.firstName", is("John")));	
	}
	
	@Test
	@Order(3)
	void testAllPersons_whenOk() throws Exception {
		mockMvc.perform(get("/persons"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$[0].firstName", is("John")));
	}
	
	@Test
	@Order(4)
	void testPersonsByAddress_whenOk() throws Exception {
		mockMvc.perform(get("/persons/search?address=1509 Culver St"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$[0].firstName", is("John")));
	}
	
	@Test
	@Order(5)
	void testPersonsByAddress_whenNull() throws Exception {
		mockMvc.perform(get("/persons/search?address=route de Pâle"))
        	.andExpect(status().is(404));
	}
	
	@Test
	@Order(6)
	void testPersonsByCity_whenOk() throws Exception {
		mockMvc.perform(get("/persons/search?city=Culver"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$[0].firstName", is("John")));
	}
	
	@Test
	@Order(7)
	void testPersonsByCity_whenNull() throws Exception {
		mockMvc.perform(get("/persons/search?city=Chamonix"))
        	.andExpect(status().is(404));
	}
	
	@Test
	@Order(8)
	void testPersonByFirstnameLastname_whenOk() throws Exception {
		mockMvc.perform(get("/persons/search?firstName=John&lastName=Boyd"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$.firstName", is("John")));
	}
	
	@Test
	@Order(9)
	void testPersonByFirstnameLastname_whenNull() throws Exception {
		mockMvc.perform(get("/persons/search?firstName=Bob&lastName=BOB"))
        	.andExpect(status().is(404));
	}

	@Test
	@Order(10)
	void testAddPerson_whenNull() throws Exception {
		mockMvc.perform(post("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
			.andExpect(status().is(400));	
	}
	
	@Test
	@Order(11)
	void testUpdatePerson_whenOk() throws Exception {
		mockMvc.perform(put("/persons?firstName=John&lastName=Boyd")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Paris\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }"))
			.andExpect(status().isOk());
	}
	
	@Test
	@Order(12)
	void testUpdatePerson_whenNull() throws Exception {
		mockMvc.perform(put("/persons?firstName=Bob&lastName=BOB")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"Bob\", \"lastName\":\"BOB\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }"))
			.andExpect(status().is(404));
	}
	
	@Test
	@Order(13)
	void testDeletePerson_whenOk() throws Exception {
		mockMvc.perform(delete("/persons?firstName=John&lastName=Boyd"))
			.andExpect(status().isOk());
	}
	
	@Test
	@Order(14)
	void testDeletePerson_whenNull() throws Exception {
		mockMvc.perform(delete("/persons?firstName=Bob&lastName=BOB"))
			.andExpect(status().is(404));
	}
}
