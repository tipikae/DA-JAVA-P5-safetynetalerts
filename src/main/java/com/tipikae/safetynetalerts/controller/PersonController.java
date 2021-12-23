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
import com.tipikae.safetynetalerts.model.Person;
import com.tipikae.safetynetalerts.service.IPersonService;

@Validated
@RestController
public class PersonController {
	
	@Autowired
	private IPersonService service;

	@GetMapping("/persons")
    public ResponseEntity<Object> allPersons() {
		try {
			List<Person> persons = service.getPersons();
			return new ResponseEntity<>(persons, HttpStatus.OK);
		} catch (StorageException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.INSUFFICIENT_STORAGE.value(), e.getMessage()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		}
	}
	
	// /persons?address={address}
	@GetMapping(value="/persons", params="address")
    public ResponseEntity<Object> personsByAddress(@RequestParam @NotBlank String address) {
		try {
			List<Person> persons = service.getPersonsByAddress(address);
			return new ResponseEntity<>(persons, HttpStatus.OK);
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
	
	// /persons?city={city}
	@GetMapping(value="/persons", params="city")
    public ResponseEntity<Object> personsByCity(@RequestParam @NotBlank String city) {
		try {
			List<Person> persons = service.getPersonsByCity(city);
			return new ResponseEntity<>(persons, HttpStatus.OK);
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
	
	// /persons?firstname={firstname}&lastname={lastname}
	@GetMapping(value="/persons", params={"firstname", "lastname"})
    public ResponseEntity<Object> personByFirstnameLastname(@RequestParam @NotBlank String firstname, 
    		@RequestParam @NotBlank String lastname) {
		try {
			Person person = service.getPersonByFirstnameLastname(firstname, lastname);
			return new ResponseEntity<>(person, HttpStatus.OK);
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
	
	@PostMapping(value="/persons", consumes={"application/json"})
	public ResponseEntity<Object> addPerson(@Valid @RequestBody Person person) {
		try {
			Person added = service.addPerson(person);
			return new ResponseEntity<>(added, HttpStatus.OK);
		} catch (StorageException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.INSUFFICIENT_STORAGE.value(), e.getMessage()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		}
	}
	
	// /persons?firstname={firstname}&lastname={lastname}
	@PutMapping(value="/persons", consumes={"application/json"})
	public ResponseEntity<Object> updatePerson(
			@RequestParam @NotBlank String firstname, 
			@RequestParam @NotBlank String lastname, 
			@Valid @RequestBody Person person) {
		try {
			Person updated = service.updatePerson(firstname, lastname, person);
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

	// /persons?firstname={firstname}&lastname={lastname}
	@DeleteMapping("/persons")
	public ResponseEntity<Object> deletePerson(
			@RequestParam @NotBlank String firstname, 
			@RequestParam @NotBlank String lastname) {
		try {
			service.deletePerson(firstname, lastname);
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
