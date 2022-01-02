package com.tipikae.safetynetalerts.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tipikae.safetynetalerts.dto.MedicalRecordDTO;
import com.tipikae.safetynetalerts.exception.ControllerException;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
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
	 * Add a medical record.
	 * @param medicalRecordDTO a MedicalRecordDTO object.
	 * @return ResponseEntity
	 */
	@PostMapping(value="/medicalrecord", consumes={"application/json"})
	public ResponseEntity<Object> addMedicalRecord(@Valid @RequestBody MedicalRecordDTO medicalRecordDTO) {
		try {
			MedicalRecordDTO added = service.addMedicalRecord(medicalRecordDTO);
			return new ResponseEntity<>(added, HttpStatus.OK);
		} catch (StorageException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.INSUFFICIENT_STORAGE.value(), e.getMessage()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.BAD_REQUEST.value(), e.getMessage()), 
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Update a medical record.
	 * @param firstName a String firstname.
	 * @param lastName a String lastname.
	 * @param medicalRecordDTO a MedicalRecordDTO object.
	 * @return ResponseEntity
	 */
	// /medicalrecord?firstName={firstname}&lastName={lastname}
	@PutMapping(value="/medicalrecord", consumes={"application/json"})
	public ResponseEntity<Object> updateMedicalRecord(
			@RequestParam @NotBlank String firstName, 
			@RequestParam @NotBlank String lastName,
			@Valid @RequestBody MedicalRecordDTO medicalRecordDTO) {
		try {
			MedicalRecordDTO updated = service.updateMedicalRecord(firstName, lastName, medicalRecordDTO);
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
	// /medicalrecord?firstName={firstname}&lastName={lastname}
	@DeleteMapping("/medicalrecord")
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
