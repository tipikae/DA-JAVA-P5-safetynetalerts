package com.tipikae.safetynetalerts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tipikae.safetynetalerts.models.Firestation;
import com.tipikae.safetynetalerts.models.MedicalRecord;
import com.tipikae.safetynetalerts.models.Person;
import com.tipikae.safetynetalerts.services.IUpdateDataService;

@RestController
public class UpdateDataController {
	
	@Autowired
	private IUpdateDataService service;

	@RequestMapping(value = "/firestations", method = RequestMethod.GET)
    public ResponseEntity<List<Firestation>> listeFirestations() {
		List<Firestation> firestations = service.getFirestations();
		if(firestations == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(firestations, HttpStatus.OK); 
	}

	@RequestMapping(value = "/persons", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> listePersons() {
		List<Person> persons = service.getPersons();
		if(persons == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(persons, HttpStatus.OK); 
	}

	@RequestMapping(value = "/medicalrecords", method = RequestMethod.GET)
    public ResponseEntity<List<MedicalRecord>> listeMedicalRecords() {
		List<MedicalRecord> medicalRecords = service.getMedicalRecords();
		if(medicalRecords == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(medicalRecords, HttpStatus.OK); 
	}
}
