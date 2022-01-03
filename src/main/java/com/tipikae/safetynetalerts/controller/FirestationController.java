package com.tipikae.safetynetalerts.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tipikae.safetynetalerts.dto.FirestationDTO;
import com.tipikae.safetynetalerts.exception.ControllerException;
import com.tipikae.safetynetalerts.exception.ConverterException;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.service.IFirestationService;

/**
 * A Firestation controller.
 * @author tipikae
 * @version 1.0
 *
 */
@Validated
@RestController
public class FirestationController {
	
	@Autowired
	private IFirestationService service;

	/**
	 * Add a firestation mapping.
	 * @param firestationDTO a FirestationDTO object.
	 * @return ResponseEntity
	 */
	@PostMapping(value="/firestation", consumes={"application/json"})
	public ResponseEntity<Object> addFirestationMapping(@Valid @RequestBody FirestationDTO firestationDTO) {
		try {
			FirestationDTO added = service.addFirestationMapping(firestationDTO);
			return new ResponseEntity<>(added, HttpStatus.OK);
		} catch (StorageException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.INSUFFICIENT_STORAGE.value(), e.getMessage()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		} catch (ServiceException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.BAD_REQUEST.value(), e.getMessage()), 
					HttpStatus.BAD_REQUEST);
		} catch (ConverterException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.BAD_REQUEST.value(), e.getMessage()), 
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Update a firestation mapping.
	 * @param address a String address.
	 * @param firestationDTO a FirestationDTO object.
	 * @return ResponseEntity
	 */
	@PutMapping(value="/firestation/{address}", consumes={"application/json"})
	public ResponseEntity<Object> updateFirestationMapping(
			@PathVariable @NotBlank String address, 
			@Valid @RequestBody FirestationDTO firestationDTO) {
		try {
			FirestationDTO updated = service.updateFirestationMapping(address, firestationDTO);
			return new ResponseEntity<>(updated, HttpStatus.OK);
		} catch(StorageException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.INSUFFICIENT_STORAGE.value(), e.getMessage()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		} catch(ServiceException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.NOT_FOUND.value(), e.getMessage()), 
					HttpStatus.NOT_FOUND);
		} catch (ConverterException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.BAD_REQUEST.value(), e.getMessage()), 
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Delete firestations by address.
	 * @param address a String address.
	 * @return ResponseEntity
	 */
	@DeleteMapping("/firestation/{address}")
	public ResponseEntity<Object> deleteFirestationsByAddress(@PathVariable @NotBlank String address ) {
		try {
			service.deleteFirestationByAddress(address);
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

	/**
	 * Delete a firestation by station number.
	 * @param station an integer station number.
	 * @return ResponseEntity
	 */
	// /firestation?station={station}
	@DeleteMapping("/firestation")
	public ResponseEntity<Object> deleteFirestationByStation(@RequestParam @Positive @NotNull int station) {
		try {
			service.deleteFirestationsByStation(station);
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