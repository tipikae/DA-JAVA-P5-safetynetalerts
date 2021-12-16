package com.tipikae.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.service.IMedicalRecordService;

@RestController
public class MedicalRecordController {
	
	@Autowired
	private IMedicalRecordService service;

	@GetMapping("/medicalrecords")
    public ResponseEntity<List<MedicalRecord>> allMedicalRecords() {
		List<MedicalRecord> medicalRecords = service.getMedicalRecords();
		if(medicalRecords != null && !medicalRecords.isEmpty()) {
			return new ResponseEntity<>(medicalRecords, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
	}
	
	// /medicalrecords?firstname={firstname}&lastname={lastname}
	@GetMapping("/medicalrecords")
    public ResponseEntity<MedicalRecord> medicalrecordByName(@RequestParam String firstname, @RequestParam String lastname) {
		if (firstname != "" && lastname != "") {
			MedicalRecord medicalrecord = service.getMedicalRecordByName(firstname, lastname);
			if (medicalrecord != null) {
				return new ResponseEntity<>(medicalrecord, HttpStatus.OK); 
			}
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(value="/medicalrecords", consumes={"application/json"})
	public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		if(!medicalRecord.getFirstname().equals("") && !medicalRecord.getLastname().equals("") && 
				medicalRecord.getBirthdate() != null && medicalRecord.getMedications() != null && 
				medicalRecord.getAllergies() != null) {
			MedicalRecord added = service.addMedicalRecord(medicalRecord);
			if(added != null) {
				return new ResponseEntity<>(medicalRecord, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(value="/medicalrecords", consumes={"application/json"})
	public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		if(!medicalRecord.getFirstname().equals("") && !medicalRecord.getLastname().equals("") && 
				medicalRecord.getBirthdate() != null && medicalRecord.getMedications() != null && 
				medicalRecord.getAllergies() != null) {
			
			if (service.updateMedicalRecord(medicalRecord)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	// /medicalrecord?firstname={firstname}&lastname={lastname}
	@DeleteMapping("/medicalrecord")
	public ResponseEntity<MedicalRecord> deleteMedicalRecord(@RequestParam String firstname, @RequestParam String lastname) {
		if(firstname != "" && lastname != "") {
			if (service.deleteMedicalRecord(firstname, lastname)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
}
