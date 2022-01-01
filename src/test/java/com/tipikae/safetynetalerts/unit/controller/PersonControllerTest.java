package com.tipikae.safetynetalerts.unit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.safetynetalerts.controller.PersonController;
import com.tipikae.safetynetalerts.dto.PersonDTO;
import com.tipikae.safetynetalerts.dtoconverter.IpersonConverter;
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
	private IpersonConverter converter;
	
	@MockBean
	private IPersonService service;
	
	@Test
	void testAddPerson_whenOk() throws Exception {
		when(converter.toEntity(any(PersonDTO.class))).thenReturn(new Person());
		when(service.addPerson(any(Person.class)))
			.thenReturn(new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"));
		mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(BODY_REQ))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testAddPerson_whenException() throws Exception {
		when(converter.toEntity(any(PersonDTO.class))).thenReturn(new Person());
		doThrow(StorageException.class).when(service).addPerson(any(Person.class));
		mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(BODY_REQ))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testAddPerson_whenInvalid() throws Exception {
		mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testUpdatePerson_whenOk() throws Exception {
		when(converter.toEntity(any(PersonDTO.class))).thenReturn(new Person());
		when(service.updatePerson(anyString(), anyString(), any(Person.class))).thenReturn(new Person(null, null, null, null, null, null, null));
		mockMvc.perform(put("/person?firstName=Bob&lastName=BOB")
				.contentType(MediaType.APPLICATION_JSON)
				.content(BODY_REQ))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testUpdatePerson_whenInvalid() throws Exception {
		when(service.updatePerson(anyString(), anyString(), any(Person.class))).thenReturn(new Person(null, null, null, null, null, null, null));
		mockMvc.perform(put("/person?firstName=Bob&lastName=BOB")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testUpdatePerson_whenStorageException() throws Exception {
		when(converter.toEntity(any(PersonDTO.class))).thenReturn(new Person());
		doThrow(StorageException.class).when(service).updatePerson(anyString(), anyString(), any(Person.class));
		mockMvc.perform(put("/person?firstName=Bob&lastName=BOB")
				.contentType(MediaType.APPLICATION_JSON)
				.content(BODY_REQ))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testUpdatePerson_whenServiceException() throws Exception {
		when(converter.toEntity(any(PersonDTO.class))).thenReturn(new Person());
		doThrow(ServiceException.class).when(service).updatePerson(anyString(), anyString(), any(Person.class));
		mockMvc.perform(put("/person?firstName=Bob&lastName=BOB")
				.contentType(MediaType.APPLICATION_JSON)
				.content(BODY_REQ))
        	.andExpect(status().is(404));
	}
	
	@Test
	void testDeletePerson_whenOk() throws Exception {
		mockMvc.perform(delete("/person?firstName=John&lastName=Boyd"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testDeletePerson_whenInvalid() throws Exception {
		mockMvc.perform(delete("/person?firstName=John&lastName="))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testDeletePerson_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).deletePerson("Bob", "BOB");
		mockMvc.perform(delete("/person?firstName=Bob&lastName=BOB"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testDeletePerson_whenServiceException() throws Exception {
		doThrow(ServiceException.class).when(service).deletePerson("Bob", "BOB");
		mockMvc.perform(delete("/person?firstName=Bob&lastName=BOB"))
        	.andExpect(status().is(404));
	}
}
