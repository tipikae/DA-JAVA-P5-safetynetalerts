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

import com.tipikae.safetynetalerts.controller.MedicalRecordController;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.service.IMedicalRecordService;

@WebMvcTest(controllers = MedicalRecordController.class)
class MedicalRecordControllerTest {
	
	private static final String BODY_REQ = "{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"1984-03-06\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }";

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private IMedicalRecordService service;
	
	@Test
	void testAllMedicalRecords_whenOk() throws Exception {
		when(service.getMedicalRecords()).thenReturn(new ArrayList<MedicalRecord>());
		mockMvc.perform(get("/medicalrecords"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testAllMedicalRecords_whenException() throws Exception {
		doThrow(StorageException.class).when(service).getMedicalRecords();
		mockMvc.perform(get("/medicalrecords"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testMedicalRecordByFirstnameLastname_whenOk() throws Exception {
		when(service.getMedicalRecordByFirstnameLastname(anyString(), anyString())).thenReturn(
				new MedicalRecord(null, null, null, null, null));
		mockMvc.perform(get("/medicalrecords?firstName=Bob&lastName=BOB"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testMedicalRecordByFirstnameLastname_whenInvalid() throws Exception {
		when(service.getMedicalRecordByFirstnameLastname(anyString(), anyString())).thenReturn(
				new MedicalRecord(null, null, null, null, null));
		mockMvc.perform(get("/medicalrecords?firstName=Bob&lastName="))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testMedicalRecordByFirstnameLastname_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).getMedicalRecordByFirstnameLastname(anyString(), anyString());
		mockMvc.perform(get("/medicalrecords?firstName=Bob&lastName=BOB"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testMedicalRecordByFirstnameLastname_whenServiceException() throws Exception {
		doThrow(ServiceException.class).when(service).getMedicalRecordByFirstnameLastname(anyString(), anyString());
		mockMvc.perform(get("/medicalrecords?firstName=Bob&lastName=BOB"))
        	.andExpect(status().is(404));
	}
	
	@Test
	void testAddMedicalRecord_whenOk() throws Exception {
		when(service.addMedicalRecord(any(MedicalRecord.class))).thenReturn(
				new MedicalRecord(null, null, null, null, null));
		mockMvc.perform(post("/medicalrecords")
				.contentType(MediaType.APPLICATION_JSON)
				.content(BODY_REQ))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testAddMedicalRecord_whenException() throws Exception {
		doThrow(StorageException.class).when(service).addMedicalRecord(any(MedicalRecord.class));
		mockMvc.perform(post("/medicalrecords")
				.contentType(MediaType.APPLICATION_JSON)
				.content(BODY_REQ))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testAddMedicalRecord_whenInvalid() throws Exception {
		mockMvc.perform(post("/medicalrecords")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testUpdateMedicalRecord_whenOk() throws Exception {
		when(service.updateMedicalRecord(anyString(), anyString(), any(MedicalRecord.class))).thenReturn(
				new MedicalRecord(null, null, null, null, null));
		mockMvc.perform(put("/medicalrecords?firstName=John&lastName=Boyd")
				.contentType(MediaType.APPLICATION_JSON)
				.content(BODY_REQ))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testUpdateMedicalRecord_whenInvalid() throws Exception {
		when(service.updateMedicalRecord(anyString(), anyString(), any(MedicalRecord.class))).thenReturn(
				new MedicalRecord(null, null, null, null, null));
		mockMvc.perform(put("/medicalrecords?firstName=Bob&lastName=BOB")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testUpdateMedicalRecord_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).updateMedicalRecord(anyString(), anyString(), 
				any(MedicalRecord.class));
		mockMvc.perform(put("/medicalrecords?firstName=Bob&lastName=BOB")
				.contentType(MediaType.APPLICATION_JSON)
				.content(BODY_REQ))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testUpdateMedicalRecord_whenServiceException() throws Exception {
		doThrow(ServiceException.class).when(service).updateMedicalRecord(anyString(), anyString(), 
				any(MedicalRecord.class));
		mockMvc.perform(put("/medicalrecords?firstName=Bob&lastName=BOB")
				.contentType(MediaType.APPLICATION_JSON)
				.content(BODY_REQ))
        	.andExpect(status().is(404));
	}
	
	@Test
	void testDeleteMedicalRecord_whenOk() throws Exception {
		mockMvc.perform(delete("/medicalrecords?firstName=John&lastName=Boyd"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testDeleteMedicalRecord_whenInvalid() throws Exception {
		mockMvc.perform(delete("/medicalrecords?firstName=John&lastName="))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testDeleteMedicalRecord_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).deleteMedicalRecord("Bob", "BOB");
		mockMvc.perform(delete("/medicalrecords?firstName=Bob&lastName=BOB"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testDeleteMedicalRecord_whenServiceException() throws Exception {
		doThrow(ServiceException.class).when(service).deleteMedicalRecord("Bob", "BOB");
		mockMvc.perform(delete("/medicalrecords?firstName=Bob&lastName=BOB"))
        	.andExpect(status().is(404));
	}
}
