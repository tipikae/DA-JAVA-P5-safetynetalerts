package com.tipikae.safetynetalerts.controller;

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

/**
 * A MedicalRecord controller CRUD.
 * @author tipikae
 * @version 1.0
 *
 */
@Validated
@RestController
public class MedicalRecordController {
	
	@Autowired
	private IMedicalRecordService service;

	/**
	 * Get all medical records.
	 * @return ResponseEntity
	 */
	@GetMapping("/medicalrecords")
    public ResponseEntity<Object> allMedicalRecords() {
		try {
			List<MedicalRecord> merdicalRecords = service.getMedicalRecords();
			return new ResponseEntity<>(merdicalRecords, HttpStatus.OK);
		} catch (StorageException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.INSUFFICIENT_STORAGE.value(), e.getMessage()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		} 
	}

	/**
	 * Get a medical record by firstname and lastname.
	 * @param firstName a String firstname.
	 * @param lastName a String lastname.
	 * @return ResponseEntity
	 */
	// /medicalrecords?firstName={firstname}&lastName={lastname}
	@GetMapping(value="/medicalrecords", params={"firstName", "lastName"})
    public ResponseEntity<Object> medicalrecordByFirstnameLastname(
    		@RequestParam @NotBlank String firstName, 
    		@RequestParam @NotBlank String lastName) {
		try {
			MedicalRecord merdicalRecord = service.getMedicalRecordByFirstnameLastname(firstName, lastName);
			return new ResponseEntity<>(merdicalRecord, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.NOT_FOUND.value(), e.getMessage()), 
					HttpStatus.NOT_FOUND);
		} catch (StorageException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.INSUFFICIENT_STORAGE.value(), e.getMessage()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		}
	}

	/**
	 * Add a medical record.
	 * @param medicalRecord a MedicalRecord object.
	 * @return ResponseEntity
	 */
	@PostMapping(value="/medicalrecords", consumes={"application/json"})
	public ResponseEntity<Object> addMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) {
		try {
			MedicalRecord added = service.addMedicalRecord(medicalRecord);
			return new ResponseEntity<>(added, HttpStatus.OK);
		} catch (StorageException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.INSUFFICIENT_STORAGE.value(), e.getMessage()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		}
	}

	/**
	 * Update a medical record.
	 * @param firstName a String firstname.
	 * @param lastName a String lastname.
	 * @param medicalRecord a MedicalRecord object.
	 * @return ResponseEntity
	 */
	// /medicalrecords?firstName={firstname}&lastName={lastname}
	@PutMapping(value="/medicalrecords", consumes={"application/json"})
	public ResponseEntity<Object> updateMedicalRecord(
			@RequestParam @NotBlank String firstName, 
			@RequestParam @NotBlank String lastName,
			@Valid @RequestBody MedicalRecord medicalRecord) {
		try {
			MedicalRecord updated = service.updateMedicalRecord(firstName, lastName, medicalRecord);
			return new ResponseEntity<>(updated, HttpStatus.OK);
		} catch(StorageException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.INSUFFICIENT_STORAGE.value(), e.getMessage()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		} catch(ServiceException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.NOT_FOUND.value(), e.getMessage()), 
					HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Delete a medical record.
	 * @param firstName a String firstname.
	 * @param lastName a String lastname.
	 * @return ResponseEntity
	 */
	// /medicalrecords?firstName={firstname}&lastName={lastname}
	@DeleteMapping("/medicalrecords")
	public ResponseEntity<Object> deleteMedicalRecord(
			@RequestParam @NotBlank String firstName, 
			@RequestParam @NotBlank String lastName) {
		try {
			service.deleteMedicalRecord(firstName, lastName);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(StorageException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.INSUFFICIENT_STORAGE.value(), e.getMessage()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		} catch(ServiceException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.NOT_FOUND.value(), e.getMessage()), 
					HttpStatus.NOT_FOUND);
		}
	}
}
