package com.tipikae.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.service.IMedicalRecordService;

@RestController
public class MedicalRecordController {
	
	@Autowired
	private IMedicalRecordService service;

	@RequestMapping(value = "/medicalrecords", method = RequestMethod.GET)
    public ResponseEntity<List<MedicalRecord>> listeMedicalRecords() {
		List<MedicalRecord> medicalRecords = service.getMedicalRecords();
		if(medicalRecords == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(medicalRecords, HttpStatus.OK); 
	}
}
