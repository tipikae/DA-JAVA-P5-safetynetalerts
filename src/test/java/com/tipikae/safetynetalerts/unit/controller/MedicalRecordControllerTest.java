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

import com.tipikae.safetynetalerts.controller.MedicalRecordController;
import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.service.IMedicalRecordService;

@WebMvcTest(controllers = MedicalRecordController.class)
class MedicalRecordControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private IMedicalRecordService service;
	
	@Test
	void testAllMedicalRecords_whenOk() throws Exception {
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		MedicalRecord medicalRecord = new MedicalRecord(null, null, null, null, null);
		medicalRecords.add(medicalRecord);
		when(service.getMedicalRecords()).thenReturn(medicalRecords);
		mockMvc.perform(get("/medicalrecords"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testAllMedicalRecords_whenNull() throws Exception {
		when(service.getMedicalRecords()).thenReturn(null);
		mockMvc.perform(get("/medicalrecords"))
        	.andExpect(status().is(204));
	}
	
	@Test
	void testMedicalRecordByName_whenOk() throws Exception {
		when(service.getMedicalRecordByName(anyString(), anyString())).thenReturn(
				new MedicalRecord(null, null, null, null, null));
		mockMvc.perform(get("/medicalrecords?firstname=Bob&lastname=BOB"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testMedicalRecordByName_whenNull() throws Exception {
		when(service.getMedicalRecordByName(anyString(), anyString())).thenReturn(null);
		mockMvc.perform(get("/medicalrecords?firstname=Bob&lastname=BOB"))
        	.andExpect(status().is(204));
	}
	
	@Test
	void testAddMedicalRecord_whenOk() throws Exception {
		when(service.addMedicalRecord(any(MedicalRecord.class))).thenReturn(
				new MedicalRecord(null, null, null, null, null));
		mockMvc.perform(post("/medicalrecords")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"1984-03-06\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testAddMedicalRecord_whenNull() throws Exception {
		when(service.addMedicalRecord(any(MedicalRecord.class))).thenReturn(null);
		mockMvc.perform(post("/medicalrecords")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        	.andExpect(status().is(204));
	}
	
	@Test
	void testUpdateMedicalRecord_whenOk() throws Exception {
		when(service.updateMedicalRecord(any(MedicalRecord.class))).thenReturn(true);
		mockMvc.perform(put("/medicalrecords")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"1984-03-06\", \"medications\":[\"aznol:400mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testUpdateMedicalRecord_whenNotOk() throws Exception {
		when(service.updateMedicalRecord(any(MedicalRecord.class))).thenReturn(false);
		mockMvc.perform(put("/medicalrecords")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        	.andExpect(status().is(304));
	}
	
	@Test
	void testDeleteMedicalRecord_whenOk() throws Exception {
		when(service.deleteMedicalRecord(anyString(), anyString())).thenReturn(true);
		mockMvc.perform(delete("/medicalrecords?firstname=Bob&lastname=BOB"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testDeleteMedicalRecord_whenNotOk() throws Exception {
		when(service.deleteMedicalRecord(anyString(), anyString())).thenReturn(false);
		mockMvc.perform(delete("/medicalrecords?firstname=Bob&lastname=BOB"))
        	.andExpect(status().is(304));
	}
}
