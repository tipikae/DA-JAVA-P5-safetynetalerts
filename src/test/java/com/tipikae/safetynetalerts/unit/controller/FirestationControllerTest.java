package com.tipikae.safetynetalerts.unit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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

import com.tipikae.safetynetalerts.controller.FirestationController;
import com.tipikae.safetynetalerts.dto.FirestationDTO;
import com.tipikae.safetynetalerts.dtoconverter.IFirestationConverter;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.service.IFirestationService;

@WebMvcTest(controllers = FirestationController.class)
class FirestationControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private IFirestationConverter converter;
	
	@MockBean
	private IFirestationService service;
	
	@Test
	void testAddFirestationMapping_whenOk() throws Exception {
		when(converter.toEntity(any(FirestationDTO.class))).thenReturn(new Firestation());
		when(service.addFirestationMapping(any(Firestation.class)))
			.thenReturn(new Firestation("3200 chemin de Pâle", 3));
		mockMvc.perform(post("/firestations")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"3200 chemin de Pâle\", \"station\":\"3\" }"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testAddFirestationMapping_whenException() throws Exception {
		when(converter.toEntity(any(FirestationDTO.class))).thenReturn(new Firestation());
		doThrow(StorageException.class).when(service).addFirestationMapping(any(Firestation.class));
		mockMvc.perform(post("/firestations")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"3200 chemin de Pâle\", \"station\":\"3\" }"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testAddFirestationMapping_whenInvalid() throws Exception {
		mockMvc.perform(post("/firestations")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testAllFirestations_whenOk() throws Exception {
		when(service.getFirestations()).thenReturn(new ArrayList<Firestation>());
		mockMvc.perform(get("/firestations"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testAllFirestations_whenException() throws Exception {
		doThrow(StorageException.class).when(service).getFirestations();
		mockMvc.perform(get("/firestations"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testFirestationByAddress_whenOk() throws Exception {
		when(service.getFirestationByAddress(anyString())).thenReturn(new Firestation("", 0));
		mockMvc.perform(get("/firestations/search?address=1509%20Culver%20St"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testFirestationByAddress_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).getFirestationByAddress("route");
		mockMvc.perform(get("/firestations/search?address=route"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testFirestationByAddress_whenServiceException() throws Exception {
		doThrow(ServiceException.class).when(service).getFirestationByAddress("route");
		mockMvc.perform(get("/firestations/search?address=route"))
        	.andExpect(status().is(404));
	}
	
	@Test
	void testFirestationsByStation_whenOk() throws Exception {
		when(service.getFirestationsByStation(anyInt())).thenReturn(new ArrayList<Firestation>());
		mockMvc.perform(get("/firestations/search?station=1"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testFirestationsByStation_whenInvalid() throws Exception {
		mockMvc.perform(get("/firestations/search?station=0"))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testFirestationsByStation_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).getFirestationsByStation(anyInt());
		mockMvc.perform(get("/firestations/search?station=1"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testFirestationsByStation_whenServiceException() throws Exception {
		doThrow(ServiceException.class).when(service).getFirestationsByStation(anyInt());
		mockMvc.perform(get("/firestations/search?station=1"))
        	.andExpect(status().is(404));
	}
	
	@Test
	void testUpdateFirestationMapping_whenOk() throws Exception {
		when(converter.toEntity(any(FirestationDTO.class))).thenReturn(new Firestation());
		when(service.updateFirestationMapping(anyString(), any(Firestation.class))).thenReturn(new Firestation("", 0));
		mockMvc.perform(put("/firestations/3200 chemin de Pâle")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"3200 chemin de Pâle\", \"station\":\"3\" }"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testUpdateFirestationMapping_whenInvalid() throws Exception {
		when(service.updateFirestationMapping(anyString(), any(Firestation.class))).thenReturn(new Firestation("", 0));
		mockMvc.perform(put("/firestations/route")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testUpdateFirestationMapping_whenStorageException() throws Exception {
		when(converter.toEntity(any(FirestationDTO.class))).thenReturn(new Firestation());
		doThrow(StorageException.class).when(service).updateFirestationMapping(anyString(), any(Firestation.class));
		mockMvc.perform(put("/firestations/3200 chemin de Pâle")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"3200 chemin de Pâle\", \"station\":\"1\" }"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testUpdateFirestationMapping_whenServiceException() throws Exception {
		when(converter.toEntity(any(FirestationDTO.class))).thenReturn(new Firestation());
		doThrow(ServiceException.class).when(service).updateFirestationMapping(anyString(), any(Firestation.class));
		mockMvc.perform(put("/firestations/3200 chemin de Pâle")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"3200 chemin de Pâle\", \"station\":\"1\" }"))
        	.andExpect(status().is(404));
	}
	
	@Test
	void testDeleteFirestationByAddress_whenOk() throws Exception {
		mockMvc.perform(delete("/firestations/badaboum"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testDeleteFirestationByAddress_whenInvalid() throws Exception {
		mockMvc.perform(delete("/firestations/"))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testDeleteFirestationByAddress_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).deleteFirestationByAddress(anyString());
		mockMvc.perform(delete("/firestations/route"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testDeleteFirestationByAddress_whenServiceException() throws Exception {
		doThrow(ServiceException.class).when(service).deleteFirestationByAddress(anyString());
		mockMvc.perform(delete("/firestations/route"))
        	.andExpect(status().is(404));
	}
	
	@Test
	void testDeleteFirestationsByStation_whenOk() throws Exception {
		mockMvc.perform(delete("/firestations?station=1"))
			.andExpect(status().isOk());
	}
	
	@Test
	void testDeleteFirestationsByStation_whenInvalid() throws Exception {
		mockMvc.perform(delete("/firestations?station=0"))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testDeleteFirestationsByStation_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).deleteFirestationsByStation(anyInt());
		mockMvc.perform(delete("/firestations?station=1"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testDeleteFirestationsByStation_whenServiceException() throws Exception {
		doThrow(ServiceException.class).when(service).deleteFirestationsByStation(anyInt());
		mockMvc.perform(delete("/firestations?station=1"))
        	.andExpect(status().is(404));
	}
}
