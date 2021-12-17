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

import com.tipikae.safetynetalerts.model.Person;
import com.tipikae.safetynetalerts.model.Storage;
import com.tipikae.safetynetalerts.util.JsonStorage;

@SpringBootTest(properties= {"storage.file=storage/data-test.json"})
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class PersonIT {
	
	@Autowired
    private MockMvc mockMvc;
	
	@BeforeAll
    private static void setUp() {
		JsonStorage jsonStorage = new JsonStorage();
		Storage storage = jsonStorage.readStorage();
        storage.setPersons(new ArrayList<Person>());
        jsonStorage.writeStorage(storage);
    }

	@Test
	@Order(1)
	void testAllPersons_whenEmpty() throws Exception {
		mockMvc.perform(get("/persons"))
        	.andExpect(status().is(204));
	}

	@Test
	@Order(2)
	void testAddPerson_whenOk() throws Exception {
		mockMvc.perform(post("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }"))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.firstname", is("John")));	
	}
	
	@Test
	@Order(3)
	void testAllPersons_whenOk() throws Exception {
		mockMvc.perform(get("/persons"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$[0].firstname", is("John")));
	}
	
	@Test
	@Order(4)
	void testPersonsByAddress_whenOk() throws Exception {
		mockMvc.perform(get("/persons?address=1509 Culver St"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$[0].firstname", is("John")));
	}
	
	@Test
	@Order(5)
	void testPersonsByAddress_whenNull() throws Exception {
		mockMvc.perform(get("/persons?address=route de Pâle"))
        	.andExpect(status().is(204));
	}
	
	@Test
	@Order(6)
	void testPersonsByCity_whenOk() throws Exception {
		mockMvc.perform(get("/persons?city=Culver"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$[0].firstname", is("John")));
	}
	
	@Test
	@Order(7)
	void testPersonsByCity_whenNull() throws Exception {
		mockMvc.perform(get("/persons?city=Chamonix"))
        	.andExpect(status().is(204));
	}
	
	@Test
	@Order(8)
	void testPersonByName_whenOk() throws Exception {
		mockMvc.perform(get("/persons?firstname=John&lastname=Boyd"))
        	.andExpect(status().isOk())
	        .andExpect(jsonPath("$.firstname", is("John")));
	}
	
	@Test
	@Order(9)
	void testPersonByName_whenNull() throws Exception {
		mockMvc.perform(get("/persons?firstname=Bob&lastname=BOB"))
        	.andExpect(status().is(204));
	}

	@Test
	@Order(10)
	void testAddPerson_whenNull() throws Exception {
		mockMvc.perform(post("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
			.andExpect(status().is(204));	
	}
	
	@Test
	@Order(11)
	void testUpdatePerson_whenOk() throws Exception {
		mockMvc.perform(put("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Paris\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }"))
			.andExpect(status().isOk());
	}
	
	@Test
	@Order(12)
	void testUpdatePerson_whenNull() throws Exception {
		mockMvc.perform(put("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"Bob\", \"lastName\":\"BOB\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }"))
			.andExpect(status().is(304));
	}
	
	@Test
	@Order(13)
	void testDeletePerson_whenOk() throws Exception {
		mockMvc.perform(delete("/persons?firstname=John&lastname=Boyd"))
			.andExpect(status().isOk());
	}
	
	@Test
	@Order(14)
	void testDeletePerson_whenNull() throws Exception {
		mockMvc.perform(delete("/persons?firstname=Bob&lastname=BOB"))
			.andExpect(status().is(304));
	}
}
