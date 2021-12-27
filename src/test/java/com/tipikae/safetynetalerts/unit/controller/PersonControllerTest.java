package com.tipikae.safetynetalerts.unit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.safetynetalerts.controller.PersonController;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Person;
import com.tipikae.safetynetalerts.service.IPersonService;

@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {
	
	private static final String BODY_REQ = "{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }";

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private IPersonService service;
	
	@Test
	void testAddPerson_whenOk() throws Exception {
		when(service.addPerson(any(Person.class)))
			.thenReturn(new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"));
		mockMvc.perform(post("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content(BODY_REQ))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testAddPerson_whenException() throws Exception {
		doThrow(StorageException.class).when(service).addPerson(any(Person.class));
		mockMvc.perform(post("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content(BODY_REQ))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testAddPerson_whenInvalid() throws Exception {
		mockMvc.perform(post("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testAllPersons_whenOk() throws Exception {
		when(service.getPersons()).thenReturn(new ArrayList<Person>());
		mockMvc.perform(get("/persons"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testAllPersons_whenException() throws Exception {
		doThrow(StorageException.class).when(service).getPersons();
		mockMvc.perform(get("/persons"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testPersonByFirstnameLastname_whenOk() throws Exception {
		when(service.getPersonByFirstnameLastname(anyString(), anyString())).thenReturn(new Person(null, null, null, null, null, null, null));
		mockMvc.perform(get("/persons/search?firstName=Bob&lastName=BOB"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testPersonByFirstnameLastname_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).getPersonByFirstnameLastname("Bob", "BOB");
		mockMvc.perform(get("/persons/search?firstName=Bob&lastName=BOB"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testPersonByFirstnameLastname_whenServiceException() throws Exception {
		doThrow(ServiceException.class).when(service).getPersonByFirstnameLastname("Bob", "BOB");
		mockMvc.perform(get("/persons/search?firstName=Bob&lastName=BOB"))
        	.andExpect(status().is(404));
	}
	
	@Test
	void testPersonsByAddress_whenOk() throws Exception {
		when(service.getPersonsByAddress(anyString())).thenReturn(new ArrayList<Person>());
		mockMvc.perform(get("/persons/search?address=route"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testPersonsByAddress_whenInvalid() throws Exception {
		mockMvc.perform(get("/persons/search?address="))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testPersonsByAddress_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).getPersonsByAddress(anyString());
		mockMvc.perform(get("/persons/search?address=route"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testPersonsByAddress_whenServiceException() throws Exception {
		doThrow(ServiceException.class).when(service).getPersonsByAddress(anyString());
		mockMvc.perform(get("/persons/search?address=route"))
        	.andExpect(status().is(404));
	}
	
	@Test
	void testPersonsByCity_whenOk() throws Exception {
		when(service.getPersonsByCity(anyString())).thenReturn(new ArrayList<Person>());
		mockMvc.perform(get("/persons/search?city=Paris"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testPersonsByCity_whenInvalid() throws Exception {
		mockMvc.perform(get("/persons/search?city="))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testPersonsByCity_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).getPersonsByCity(anyString());
		mockMvc.perform(get("/persons/search?city=Paris"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testPersonsByCity_whenServiceException() throws Exception {
		doThrow(ServiceException.class).when(service).getPersonsByCity(anyString());
		mockMvc.perform(get("/persons/search?city=Paris"))
        	.andExpect(status().is(404));
	}
	
	@Test
	void testUpdatePerson_whenOk() throws Exception {
		when(service.updatePerson(anyString(), anyString(), any(Person.class))).thenReturn(new Person(null, null, null, null, null, null, null));
		mockMvc.perform(put("/persons?firstName=Bob&lastName=BOB")
				.contentType(MediaType.APPLICATION_JSON)
				.content(BODY_REQ))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testUpdatePerson_whenInvalid() throws Exception {
		when(service.updatePerson(anyString(), anyString(), any(Person.class))).thenReturn(new Person(null, null, null, null, null, null, null));
		mockMvc.perform(put("/persons?firstName=Bob&lastName=BOB")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testUpdatePerson_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).updatePerson(anyString(), anyString(), any(Person.class));
		mockMvc.perform(put("/persons?firstName=Bob&lastName=BOB")
				.contentType(MediaType.APPLICATION_JSON)
				.content(BODY_REQ))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testUpdatePerson_whenServiceException() throws Exception {
		doThrow(ServiceException.class).when(service).updatePerson(anyString(), anyString(), any(Person.class));
		mockMvc.perform(put("/persons?firstName=Bob&lastName=BOB")
				.contentType(MediaType.APPLICATION_JSON)
				.content(BODY_REQ))
        	.andExpect(status().is(404));
	}
	
	@Test
	void testDeletePerson_whenOk() throws Exception {
		mockMvc.perform(delete("/persons?firstName=John&lastName=Boyd"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testDeletePerson_whenInvalid() throws Exception {
		mockMvc.perform(delete("/persons?firstName=John&lastName="))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testDeletePerson_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).deletePerson("Bob", "BOB");
		mockMvc.perform(delete("/persons?firstName=Bob&lastName=BOB"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testDeletePerson_whenServiceException() throws Exception {
		doThrow(ServiceException.class).when(service).deletePerson("Bob", "BOB");
		mockMvc.perform(delete("/persons?firstName=Bob&lastName=BOB"))
        	.andExpect(status().is(404));
	}
}
