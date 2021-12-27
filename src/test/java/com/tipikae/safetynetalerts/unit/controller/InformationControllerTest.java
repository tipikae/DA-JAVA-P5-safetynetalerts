package com.tipikae.safetynetalerts.unit.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.safetynetalerts.controller.InformationController;
import com.tipikae.safetynetalerts.dto.ChildAlertDTO;
import com.tipikae.safetynetalerts.dto.CommunityEmailDTO;
import com.tipikae.safetynetalerts.dto.FireDTO;
import com.tipikae.safetynetalerts.dto.FirestationInfoDTO;
import com.tipikae.safetynetalerts.dto.FloodDTO;
import com.tipikae.safetynetalerts.dto.PersonInfoDTO;
import com.tipikae.safetynetalerts.dto.PhoneAlertDTO;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.service.IInformationService;

@WebMvcTest(controllers = InformationController.class)
class InformationControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private IInformationService service;
	
	@Test
	void testResidentsByStation_whenOk() throws Exception {
		when(service.getResidentsByStation(anyInt())).thenReturn(new FirestationInfoDTO(0, 0, 0, null));
		mockMvc.perform(get("/firestation?stationNumber=1"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testResidentsByStation_whenInvalid() throws Exception {
		mockMvc.perform(get("/firestation?stationNumber=0"))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testResidentsByStation_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).getResidentsByStation(anyInt());
		mockMvc.perform(get("/firestation?stationNumber=1"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testChildrenByAddress_whenOk() throws Exception {
		when(service.getChildrenByAddress(anyString())).thenReturn(new ChildAlertDTO(null, null, null));
		mockMvc.perform(get("/childAlert?address=route"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testChildrenByAddress_whenInvalid() throws Exception {
		mockMvc.perform(get("/childAlert?address="))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testChildrenByAddress_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).getChildrenByAddress(anyString());
		mockMvc.perform(get("/childAlert?address=route"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testPhoneNumbersByStation_whenOk() throws Exception {
		when(service.getPhoneNumbersByStation(anyInt())).thenReturn(new PhoneAlertDTO(0, null));
		mockMvc.perform(get("/phoneAlert?firestation=1"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testPhoneNumbersByStation_whenInvalid() throws Exception {
		mockMvc.perform(get("/phoneAlert?firestation="))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testPhoneNumbersByStation_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).getPhoneNumbersByStation(anyInt());
		mockMvc.perform(get("/phoneAlert?firestation=1"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testMembersByAddress_whenOk() throws Exception {
		when(service.getMembersByAddress(anyString())).thenReturn(new FireDTO(null, 0, null));
		mockMvc.perform(get("/fire?address=route"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testMembersByAddress_whenInvalid() throws Exception {
		mockMvc.perform(get("/fire?address="))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testMembersByAddress_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).getMembersByAddress(anyString());
		mockMvc.perform(get("/fire?address=route"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testResidentsByStations_whenOk() throws Exception {
		when(service.getResidentsByStations(anyList())).thenReturn(new ArrayList<FloodDTO>());
		mockMvc.perform(get("/flood/stations?stations=1,2"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testResidentsByStations_whenInvalid() throws Exception {
		mockMvc.perform(get("/flood/stations?stations="))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testResidentsByStations_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).getResidentsByStations(anyList());
		mockMvc.perform(get("/flood/stations?stations=1,2"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testPersonInfoByLastname_whenOk() throws Exception {
		when(service.getPersonInfoByLastname(anyString(), anyString())).thenReturn(new PersonInfoDTO(null, null));
		mockMvc.perform(get("/personInfo?firstName=bob&lastName=BOB"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testPersonInfoByLastname_whenInvalid() throws Exception {
		mockMvc.perform(get("/personInfo?firstName=bob&lastName="))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testPersonInfoByLastname_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).getPersonInfoByLastname(anyString(), anyString());
		mockMvc.perform(get("/personInfo?firstName=bob&lastName=BOB"))
        	.andExpect(status().is(507));
	}
	
	@Test
	void testEmailsByCity_whenOk() throws Exception {
		when(service.getEmailsByCity(anyString())).thenReturn(new CommunityEmailDTO(null, null));
		mockMvc.perform(get("/communityEmail?city=Paris"))
        	.andExpect(status().isOk());
	}
	
	@Test
	void testEmailsByCity_whenInvalid() throws Exception {
		mockMvc.perform(get("/communityEmail?city="))
        	.andExpect(status().is(400));
	}
	
	@Test
	void testEmailsByCity_whenStorageException() throws Exception {
		doThrow(StorageException.class).when(service).getEmailsByCity(anyString());
		mockMvc.perform(get("/communityEmail?city=Paris"))
        	.andExpect(status().is(507));
	}

}
