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

/**
 * A Person controller CRUD.
 * @author tipikae
 * @version 1.0
 *
 */
@Validated
@RestController
public class PersonController {
	
	@Autowired
	private IPersonService service;

	/**
	 * Get all persons.
	 * @return ResponseEntity<Object>
	 */
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

	/**
	 * Get persons by address.
	 * @param address a String address.
	 * @return ResponseEntity<Object>
	 */
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

	/**
	 * Get persons by city.
	 * @param city a String city.
	 * @return ResponseEntity<Object>
	 */
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

	/**
	 * Get a person by firstname and lastname.
	 * @param firstname a String firstname.
	 * @param lastname a String lastname.
	 * @return ResponseEntity<Object>
	 */
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

	/**
	 * Add a person.
	 * @param person a Person object.
	 * @return ResponseEntity<Object>
	 */
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

	/**
	 * Update a person.
	 * @param firstname a String firstname.
	 * @param lastname a String lastname.
	 * @param person a Person object.
	 * @return ResponseEntity<Object>
	 */
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

	/**
	 * Delete a person.
	 * @param firstname a String firstname.
	 * @param lastname a String lastname.
	 * @return ResponseEntity<Object>
	 */
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
