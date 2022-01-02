package com.tipikae.safetynetalerts.unit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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

import com.tipikae.safetynetalerts.controller.FirestationController;
import com.tipikae.safetynetalerts.dto.FirestationDTO;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.service.IFirestationService;

@WebMvcTest(controllers = FirestationController.class)
class FirestationControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private IFirestationService service;
	
	@Test
	void testAddFirestationMapping_whenOk() throws Exception {
		when(service.addFirestationMapping(any(FirestationDTO.class)))
			.thenReturn(new FirestationDTO("3200 chemin de Pâle", 3));
		mockMvc.perform(post("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"3200 chemin de Pâle\", \"station\":\"3\" }"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testAddFirestationMapping_whenException() throws Exception {
		doThrow(StorageException.class).when(service).addFirestationMapping(any(FirestationDTO.class));
		mockMvc.perform(post("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"3200 chemin de Pâle\", \"station\":\"3\" }"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testAddFirestationMapping_whenInvalid() throws Exception {
		mockMvc.perform(post("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testUpdateFirestationMapping_whenOk() throws Exception {
		when(service.updateFirestationMapping(anyString(), any(FirestationDTO.class)))
			.thenReturn(new FirestationDTO("", 0));
		mockMvc.perform(put("/firestation/3200 chemin de Pâle")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"3200 chemin de Pâle\", \"station\":\"3\" }"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testUpdateFirestationMapping_whenInvalid() throws Exception {
		when(service.updateFirestationMapping(anyString(), any(FirestationDTO.class)))
			.thenReturn(new FirestationDTO("", 0));
		mockMvc.perform(put("/firestation/route")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testUpdateFirestationMapping_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).updateFirestationMapping(anyString(), any(FirestationDTO.class));
		mockMvc.perform(put("/firestation/3200 chemin de Pâle")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"3200 chemin de Pâle\", \"station\":\"1\" }"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testUpdateFirestationMapping_whenServiceException() throws Exception {
		doThrow(ServiceException.class).when(service).updateFirestationMapping(anyString(), any(FirestationDTO.class));
		mockMvc.perform(put("/firestation/3200 chemin de Pâle")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"address\":\"3200 chemin de Pâle\", \"station\":\"1\" }"))
        	.andExpect(status().is(404));
	}
	
	@Test
	void testDeleteFirestationByAddress_whenOk() throws Exception {
		mockMvc.perform(delete("/firestation/badaboum"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testDeleteFirestationByAddress_whenInvalid() throws Exception {
		mockMvc.perform(delete("/firestation/"))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testDeleteFirestationByAddress_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).deleteFirestationByAddress(anyString());
		mockMvc.perform(delete("/firestation/route"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testDeleteFirestationByAddress_whenServiceException() throws Exception {
		doThrow(ServiceException.class).when(service).deleteFirestationByAddress(anyString());
		mockMvc.perform(delete("/firestation/route"))
        	.andExpect(status().is(404));
	}
	
	@Test
	void testDeleteFirestationsByStation_whenOk() throws Exception {
		mockMvc.perform(delete("/firestation?station=1"))
			.andExpect(status().isOk());
	}
	
	@Test
	void testDeleteFirestationsByStation_whenInvalid() throws Exception {
		mockMvc.perform(delete("/firestation?station=0"))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testDeleteFirestationsByStation_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).deleteFirestationsByStation(anyInt());
		mockMvc.perform(delete("/firestation?station=1"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testDeleteFirestationsByStation_whenServiceException() throws Exception {
		doThrow(ServiceException.class).when(service).deleteFirestationsByStation(anyInt());
		mockMvc.perform(delete("/firestation?station=1"))
        	.andExpect(status().is(404));
	}
}
