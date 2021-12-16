package com.tipikae.safetynetalerts.unit.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.safetynetalerts.controller.FirestationController;
import com.tipikae.safetynetalerts.controller.PersonController;
import com.tipikae.safetynetalerts.service.IFirestationService;

@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private IFirestationService service;
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
