package com.tipikae.safetynetalerts.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
	void testAddPerson_whenOk() throws Exception {
		mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }"))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.firstName", is("John")));	
	}
	
	@Test
	@Order(2)
	void testAddPerson_whenNull() throws Exception {
		mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
			.andExpect(status().is(400));	
	}
	
	@Test
	@Order(3)
	void testUpdatePerson_whenOk() throws Exception {
		mockMvc.perform(put("/person?firstName=John&lastName=Boyd")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Paris\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }"))
			.andExpect(status().isOk());
	}
	
	@Test
	@Order(4)
	void testUpdatePerson_whenNull() throws Exception {
		mockMvc.perform(put("/person?firstName=Bob&lastName=BOB")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"Bob\", \"lastName\":\"BOB\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }"))
			.andExpect(status().is(404));
	}
	
	@Test
	@Order(5)
	void testDeletePerson_whenOk() throws Exception {
		mockMvc.perform(delete("/person?firstName=John&lastName=Boyd"))
			.andExpect(status().isOk());
	}
	
	@Test
	@Order(6)
	void testDeletePerson_whenNull() throws Exception {
		mockMvc.perform(delete("/person?firstName=Bob&lastName=BOB"))
			.andExpect(status().is(404));
	}
}
