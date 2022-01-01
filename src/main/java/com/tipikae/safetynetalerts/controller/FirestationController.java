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
import com.tipikae.safetynetalerts.dtoconverter.IFirestationConverter;
import com.tipikae.safetynetalerts.exception.ControllerException;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;
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
	private IFirestationConverter converter;
	
	@Autowired
	private IFirestationService service;

	/**
	 * Add a firestation mapping.
	 * @param firestation a FirestationDTO object.
	 * @return ResponseEntity
	 */
	@PostMapping(value="/firestation", consumes={"application/json"})
	public ResponseEntity<Object> addFirestationMapping(@Valid @RequestBody FirestationDTO firestation) {
		try {
			Firestation added = service.addFirestationMapping(converter.toEntity(firestation));
			return new ResponseEntity<>(converter.toDTO(added), HttpStatus.OK);
		} catch (StorageException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.INSUFFICIENT_STORAGE.value(), e.getMessage()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		}
	}

	/**
	 * Update a firestation mapping.
	 * @param address a String address.
	 * @param firestation a FirestationDTO object.
	 * @return ResponseEntity
	 */
	@PutMapping(value="/firestation/{address}", consumes={"application/json"})
	public ResponseEntity<Object> updateFirestationMapping(
			@PathVariable @NotBlank String address, 
			@Valid @RequestBody FirestationDTO firestation) {
		try {
			Firestation updated = service.updateFirestationMapping(address, 
					converter.toEntity(firestation));
			return new ResponseEntity<>(converter.toDTO(updated), HttpStatus.OK);
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