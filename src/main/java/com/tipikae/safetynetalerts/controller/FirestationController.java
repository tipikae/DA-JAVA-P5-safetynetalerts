package com.tipikae.safetynetalerts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.service.IFirestationService;

@RestController
public class FirestationController {
	
	private static final Logger LOGGER = LogManager.getLogger("FirestationController");
	
	@Autowired
	private IFirestationService service;

	@GetMapping("/firestations")
    public ResponseEntity<List<Firestation>> allFirestations() {
		List<Firestation> firestations = service.getFirestations();
		if(firestations != null && !firestations.isEmpty()) {
			LOGGER.info("allFirestations: OK");
			return new ResponseEntity<>(firestations, HttpStatus.OK);
		}

		LOGGER.info("allFirestations: NOK");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/firestations/{address}")
    public ResponseEntity<Firestation> firestationByAddress(@PathVariable String address) {
		if (address != null) {
			Firestation firestation = service.getFirestationByAddress(address);
			if (firestation != null) {
				return new ResponseEntity<>(firestation, HttpStatus.OK);
			} 
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// /firestations?station={station}
	@GetMapping(value="/firestations", params="station")
    public ResponseEntity<List<Firestation>> firestationsByStation(@RequestParam int station) {
		if (station != 0) {
			List<Firestation> firestations = service.getFirestationsByStation(station);
			if (firestations != null && !firestations.isEmpty()) {
				return new ResponseEntity<>(firestations, HttpStatus.OK); 
			}
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(value="/firestations", consumes={"application/json"})
	public ResponseEntity<Firestation> addFirestationMapping(@RequestBody Firestation firestation) {
		if(firestation.getAddress() != null && firestation.getStation() != 0) {
			Firestation added = service.addFirestationMapping(firestation);
			if(added != null) {
				return new ResponseEntity<>(firestation, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(value="/firestations/{address}", consumes={"application/json"})
	public ResponseEntity<Firestation> updateFirestationMapping(
			@PathVariable String address, 
			@RequestBody Firestation firestation) {
		if(address != null && firestation.getAddress() != null && firestation.getStation() != 0) {
			if (service.updateFirestationMapping(address, firestation)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
	
	@DeleteMapping("/firestations/{address}")
	public ResponseEntity<Firestation> deleteFirestationsByAddress(@PathVariable String address ) {
		if(address != null) {
			if (service.deleteFirestationByAddress(address)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	// /firestations?station={station}
	@DeleteMapping("/firestations")
	public ResponseEntity<Firestation> deleteFirestationByStation(@RequestParam int station) {
		if(station != 0) {
			if (service.deleteFirestationsByStation(station)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
}
