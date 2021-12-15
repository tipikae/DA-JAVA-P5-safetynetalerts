package com.tipikae.safetynetalerts.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
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
import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.service.IFirestationService;

@WebMvcTest(controllers = FirestationController.class)
class FirestationControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private IFirestationService service;
	
	@Test
	void testAllFirestations_whenOk() throws Exception {
		when(service.getFirestations()).thenReturn(new ArrayList<Firestation>());
		mockMvc.perform(get("/firestations"))
        .andExpect(status().isOk());
	}
	
	@Test
	void testAllFirestations_whenNull() throws Exception {
		when(service.getFirestations()).thenReturn(null);
		mockMvc.perform(get("/firestations"))
        .andExpect(status().is(204));
	}
	
	@Test
	void testFirestationsByStation_whenOk() throws Exception {
		when(service.getFirestationsByStation(1)).thenReturn(new ArrayList<Firestation>());
		mockMvc.perform(get("/firestations/1"))
        .andExpect(status().isOk());
	}
	
	@Test
	void testFirestationsByStation_whenNull() throws Exception {
		when(service.getFirestationsByStation(anyInt())).thenReturn(null);
		mockMvc.perform(get("/firestations/1"))
        .andExpect(status().is(204));
	}
	
	@Test
	void testFirestationByAddress_whenOk() throws Exception {
		when(service.getFirestationByAddress(anyString())).thenReturn(new Firestation("", 0));
		mockMvc.perform(get("/firestation?address=1509%20Culver%20St"))
        .andExpect(status().isOk());
	}
	
	@Test
	void testFirestationByAddress_whenNull() throws Exception {
		when(service.getFirestationByAddress(anyString())).thenReturn(null);
		mockMvc.perform(get("/firestation?address=1509%20Culver%20St"))
        .andExpect(status().is(204));
	}
	
	@Test
	void testAddFirestationMapping_whenOk() throws Exception {
		when(service.addFirestationMapping(any(Firestation.class))).thenReturn(new Firestation("", 0));
		mockMvc.perform(post("/firestations")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        .andExpect(status().isOk());
	}
	
	@Test
	void testAddFirestationMapping_whenNull() throws Exception {
		when(service.addFirestationMapping(any(Firestation.class))).thenReturn(null);
		mockMvc.perform(post("/firestations")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        .andExpect(status().is(204));
	}
	
	@Test
	void testUpdateFirestationMapping_whenOk() throws Exception {
		when(service.updateFirestationMapping(any(Firestation.class))).thenReturn(true);
		mockMvc.perform(put("/firestations")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        .andExpect(status().isOk());
	}
	
	@Test
	void testUpdateFirestationMapping_whenNotOk() throws Exception {
		when(service.updateFirestationMapping(any(Firestation.class))).thenReturn(false);
		mockMvc.perform(put("/firestations")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        .andExpect(status().is(304));
	}
	
	@Test
	void testDeleteFirestationsByStation_whenOk() throws Exception {
		when(service.deleteFirestationsByStation(anyInt())).thenReturn(true);
		mockMvc.perform(delete("/firestations/1"))
        .andExpect(status().isOk());
	}
	
	@Test
	void testDeleteFirestationsByStation_whenNotOk() throws Exception {
		when(service.deleteFirestationsByStation(anyInt())).thenReturn(false);
		mockMvc.perform(delete("/firestations/1"))
        .andExpect(status().is(304));
	}
	
	@Test
	void testDeleteFirestationByAddress_whenOk() throws Exception {
		when(service.deleteFirestationByAddress(anyString())).thenReturn(true);
		mockMvc.perform(delete("/firestation?address=badaboum"))
        .andExpect(status().isOk());
	}
	
	@Test
	void testDeleteFirestationByAddress_whenNotOk() throws Exception {
		when(service.deleteFirestationByAddress(anyString())).thenReturn(false);
		mockMvc.perform(delete("/firestation?address=badaboum"))
        .andExpect(status().is(304));
	}
}
