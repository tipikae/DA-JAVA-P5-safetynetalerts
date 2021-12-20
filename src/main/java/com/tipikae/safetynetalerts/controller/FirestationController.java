package com.tipikae.safetynetalerts.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tipikae.safetynetalerts.exception.ControllerException;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.service.IFirestationService;

@Validated
@RestController
public class FirestationController {
	
	@Autowired
	private IFirestationService service;

	@GetMapping("/firestations")
    public ResponseEntity<List<Firestation>> allFirestations() {
		List<Firestation> firestations = service.getFirestations();
		return new ResponseEntity<>(firestations, HttpStatus.OK);
	}

	@GetMapping("/firestations/{address}")
    public ResponseEntity<Object> firestationByAddress(@PathVariable @NotBlank String address) {
		try {
			Firestation firestation = service.getFirestationByAddress(address);
			return new ResponseEntity<>(firestation, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(new ControllerException(e.getMessage(), new Date()), 
					HttpStatus.NOT_FOUND);
		}
	}

	// /firestations?station={station}
	@GetMapping(value="/firestations", params="station")
    public ResponseEntity<Object> firestationsByStation(@RequestParam @Positive int station) {
		try {
			List<Firestation> firestations = service.getFirestationsByStation(station);
			return new ResponseEntity<>(firestations, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>(new ControllerException(e.getMessage(), new Date()), 
					HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/firestations", consumes={"application/json"})
	public ResponseEntity<Object> addFirestationMapping(@Valid @RequestBody Firestation firestation) {
		try {
			Firestation added = service.addFirestationMapping(firestation);
			return new ResponseEntity<>(added, HttpStatus.OK);
		} catch (StorageException e) {
			return new ResponseEntity<>(new ControllerException(e.getMessage(), new Date()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		}
	}
	
	@PutMapping(value="/firestations/{address}", consumes={"application/json"})
	public ResponseEntity<Object> updateFirestationMapping(
			@PathVariable @NotBlank String address, 
			@Valid @RequestBody Firestation firestation) {
		try {
			Firestation updated = service.updateFirestationMapping(address, firestation);
			return new ResponseEntity<>(updated, HttpStatus.OK);
		} catch(StorageException e) {
			return new ResponseEntity<>(new ControllerException(e.getMessage(), new Date()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		} catch(ServiceException e) {
			return new ResponseEntity<>(new ControllerException(e.getMessage(), new Date()), 
					HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/firestations/{address}")
	public ResponseEntity<Object> deleteFirestationsByAddress(@PathVariable @NotBlank String address ) {
		try {
			service.deleteFirestationByAddress(address);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(StorageException e) {
			return new ResponseEntity<>(new ControllerException(e.getMessage(), new Date()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		} catch(ServiceException e) {
			return new ResponseEntity<>(new ControllerException(e.getMessage(), new Date()), 
					HttpStatus.NOT_FOUND);
		}
	}

	// /firestations?station={station}
	@DeleteMapping("/firestations")
	public ResponseEntity<Object> deleteFirestationByStation(@RequestParam @Positive int station) {
		try {
			service.deleteFirestationsByStation(station);
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
