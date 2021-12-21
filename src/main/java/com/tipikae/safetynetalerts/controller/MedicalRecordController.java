package com.tipikae.safetynetalerts.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tipikae.safetynetalerts.exception.ControllerException;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.service.IMedicalRecordService;

@Validated
@RestController
public class MedicalRecordController {
	
	@Autowired
	private IMedicalRecordService service;

	@GetMapping("/medicalrecords")
    public ResponseEntity<Object> allMedicalRecords() {
		try {
			List<MedicalRecord> merdicalRecords = service.getMedicalRecords();
			return new ResponseEntity<>(merdicalRecords, HttpStatus.OK);
		} catch (StorageException e) {
			return new ResponseEntity<>(new ControllerException(e.getMessage(), new Date()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		} 
	}
	
	// /medicalrecords?firstname={firstname}&lastname={lastname}
	@GetMapping(value="/medicalrecords", params={"firstname", "lastname"})
    public ResponseEntity<Object> medicalrecordByFirstnameLastname(
    		@RequestParam @NotBlank String firstname, 
    		@RequestParam @NotBlank String lastname) {
		try {
			MedicalRecord merdicalRecord = service.getMedicalRecordByFirstnameLastname(firstname, lastname);
			return new ResponseEntity<>(merdicalRecord, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(new ControllerException(e.getMessage(), new Date()), 
					HttpStatus.NOT_FOUND);
		} catch (StorageException e) {
			return new ResponseEntity<>(new ControllerException(e.getMessage(), new Date()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		}
	}
	
	@PostMapping(value="/medicalrecords", consumes={"application/json"})
	public ResponseEntity<Object> addMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) {
		try {
			MedicalRecord added = service.addMedicalRecord(medicalRecord);
			return new ResponseEntity<>(added, HttpStatus.OK);
		} catch (StorageException e) {
			return new ResponseEntity<>(new ControllerException(e.getMessage(), new Date()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		}
	}
	

	// /medicalrecords?firstname={firstname}&lastname={lastname}
	@PutMapping(value="/medicalrecords", consumes={"application/json"})
	public ResponseEntity<Object> updateMedicalRecord(
			@RequestParam @NotBlank String firstname, 
			@RequestParam @NotBlank String lastname,
			@Valid @RequestBody MedicalRecord medicalRecord) {
		try {
			MedicalRecord updated = service.updateMedicalRecord(firstname, lastname, medicalRecord);
			return new ResponseEntity<>(updated, HttpStatus.OK);
		} catch(StorageException e) {
			return new ResponseEntity<>(new ControllerException(e.getMessage(), new Date()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		} catch(ServiceException e) {
			return new ResponseEntity<>(new ControllerException(e.getMessage(), new Date()), 
					HttpStatus.NOT_FOUND);
		}
	}

	// /medicalrecords?firstname={firstname}&lastname={lastname}
	@DeleteMapping("/medicalrecords")
	public ResponseEntity<Object> deleteMedicalRecord(
			@RequestParam @NotBlank String firstname, 
			@RequestParam @NotBlank String lastname) {
		try {
			service.deleteMedicalRecord(firstname, lastname);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(StorageException e) {
			return new ResponseEntity<>(new ControllerException(e.getMessage(), new Date()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		} catch(ServiceException e) {
			return new ResponseEntity<>(new ControllerException(e.getMessage(), new Date()), 
					HttpStatus.NOT_FOUND);
		}
	}
}
