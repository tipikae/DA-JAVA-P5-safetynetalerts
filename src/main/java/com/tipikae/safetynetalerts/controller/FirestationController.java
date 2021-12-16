package com.tipikae.safetynetalerts.controller;

import java.util.List;

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
	
	@Autowired
	private IFirestationService service;

	@GetMapping("/firestations")
    public ResponseEntity<List<Firestation>> allFirestations() {
		List<Firestation> firestations = service.getFirestations();
		if(firestations != null && !firestations.isEmpty()) {
			return new ResponseEntity<>(firestations, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/firestations/{station}")
    public ResponseEntity<List<Firestation>> firestationsByStation(@PathVariable int station) {
		if (station != 0) {
			List<Firestation> firestations = service.getFirestationsByStation(station);
			if (firestations != null && !firestations.isEmpty()) {
				return new ResponseEntity<>(firestations, HttpStatus.OK);
			} 
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// /firestation?address={address}
	@GetMapping("/firestation")
    public ResponseEntity<Firestation> firestationByAddress(@RequestParam String address) {
		if (address != null) {
			Firestation firestation;
			firestation = service.getFirestationByAddress(address);
			if (firestation != null) {
				return new ResponseEntity<>(firestation, HttpStatus.OK); 
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
	
	@PutMapping(value="/firestations", consumes={"application/json"})
	public ResponseEntity<Firestation> updateFirestationMapping(@RequestBody Firestation firestation) {
		if(firestation.getAddress() != null && firestation.getStation() != 0) {
			if (service.updateFirestationMapping(firestation)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
	
	@DeleteMapping("/firestations/{station}")
	public ResponseEntity<Firestation> deleteFirestationsByStation(@PathVariable int station) {
		if(station != 0) {
			if (service.deleteFirestationsByStation(station)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	// /firestation?address={address}
	@DeleteMapping("/firestation")
	public ResponseEntity<Firestation> deleteFirestationByAddress(@RequestParam String address) {
		if(!address.isEmpty()) {
			if (service.deleteFirestationByAddress(address)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
}
