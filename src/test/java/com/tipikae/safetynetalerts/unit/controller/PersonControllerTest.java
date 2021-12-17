package com.tipikae.safetynetalerts.unit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.safetynetalerts.controller.PersonController;
import com.tipikae.safetynetalerts.model.Person;
import com.tipikae.safetynetalerts.service.IPersonService;

@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private IPersonService service;
	
	@Test
	void testAllPersons_whenOk() throws Exception {
		List<Person> persons = new ArrayList<>();
		Person person = new Person(null, null, null, null, null, null, null);
		persons.add(person);
		when(service.getPersons()).thenReturn(persons);
		mockMvc.perform(get("/persons"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testAllPersons_whenNull() throws Exception {
		when(service.getPersons()).thenReturn(null);
		mockMvc.perform(get("/persons"))
        	.andExpect(status().is(204));
	}
	
	@Test
	void testPersonsByAddress_whenOk() throws Exception {
		when(service.getPersonsByAddress(anyString())).thenReturn(
				List.of(new Person(null, null, null, null, null, null, null)));
		mockMvc.perform(get("/persons?address=1509%20Culver%20St"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testPersonsByAddress_whenNull() throws Exception {
		when(service.getPersonsByAddress(anyString())).thenReturn(null);
		mockMvc.perform(get("/persons?address=1509%20Culver%20St"))
        	.andExpect(status().is(204));
	}
	
	@Test
	void testPersonsByCity_whenOk() throws Exception {
		when(service.getPersonsByCity(anyString())).thenReturn(
				List.of(new Person(null, null, null, null, null, null, null)));
		mockMvc.perform(get("/persons?city=Paris"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testPersonsByCity_whenNull() throws Exception {
		when(service.getPersonsByCity(anyString())).thenReturn(null);
		mockMvc.perform(get("/persons?city=Paris"))
        	.andExpect(status().is(204));
	}
	
	@Test
	void testPersonByName_whenOk() throws Exception {
		when(service.getPersonByName(anyString(), anyString())).thenReturn(
				new Person(null, null, null, null, null, null, null));
		mockMvc.perform(get("/persons?firstname=Bob&lastname=BOB"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testPersonByName_whenNull() throws Exception {
		when(service.getPersonByName(anyString(), anyString())).thenReturn(null);
		mockMvc.perform(get("/persons?firstname=Bob&lastname=BOB"))
        	.andExpect(status().is(204));
	}
	
	@Test
	void testAddPerson_whenOk() throws Exception {
		when(service.addPerson(any(Person.class))).thenReturn(
				new Person(null, null, null, null, null, null, null));
		mockMvc.perform(post("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testAddPerson_whenNull() throws Exception {
		when(service.addPerson(any(Person.class))).thenReturn(null);
		mockMvc.perform(post("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        	.andExpect(status().is(204));
	}
	
	@Test
	void testUpdatePerson_whenOk() throws Exception {
		when(service.updatePerson(any(Person.class))).thenReturn(true);
		mockMvc.perform(put("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Paris\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testUpdatePerson_whenNotOk() throws Exception {
		when(service.updatePerson(any(Person.class))).thenReturn(false);
		mockMvc.perform(put("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        	.andExpect(status().is(304));
	}
	
	@Test
	void testDeletePersonByName_whenOk() throws Exception {
		when(service.deletePerson(anyString(), anyString())).thenReturn(true);
		mockMvc.perform(delete("/persons?firstname=Bob&lastname=BOB"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testDeletePersonByName_whenNotOk() throws Exception {
		when(service.deletePerson(anyString(), anyString())).thenReturn(false);
		mockMvc.perform(delete("/persons?firstname=Bob&lastname=BOB"))
        	.andExpect(status().is(304));
	}
}
