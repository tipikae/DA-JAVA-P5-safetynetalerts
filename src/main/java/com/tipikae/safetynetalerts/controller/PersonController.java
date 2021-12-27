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

import com.tipikae.safetynetalerts.dto.PersonDTO;
import com.tipikae.safetynetalerts.dtoconverter.PersonConverter;
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
			return new ResponseEntity<>(PersonConverter.toDTOs(persons), HttpStatus.OK);
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
	// /persons/search?address={address}
	@GetMapping(value="/persons/search", params="address")
    public ResponseEntity<Object> personsByAddress(@RequestParam @NotBlank String address) {
		try {
			List<Person> persons = service.getPersonsByAddress(address);
			return new ResponseEntity<>(PersonConverter.toDTOs(persons), HttpStatus.OK);
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
	// /persons/search?city={city}
	@GetMapping(value="/persons/search", params="city")
    public ResponseEntity<Object> personsByCity(@RequestParam @NotBlank String city) {
		try {
			List<Person> persons = service.getPersonsByCity(city);
			return new ResponseEntity<>(PersonConverter.toDTOs(persons), HttpStatus.OK);
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
	 * @param firstName a String firstname.
	 * @param lastName a String lastname.
	 * @return ResponseEntity<Object>
	 */
	// /persons/search?firstName={firstname}&lastName={lastname}
	@GetMapping(value="/persons/search", params={"firstName", "lastName"})
    public ResponseEntity<Object> personByFirstnameLastname(@RequestParam @NotBlank String firstName, 
    		@RequestParam @NotBlank String lastName) {
		try {
			Person person = service.getPersonByFirstnameLastname(firstName, lastName);
			return new ResponseEntity<>(PersonConverter.toDTO(person), HttpStatus.OK);
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
	 * @param person a PersonDTO object.
	 * @return ResponseEntity<Object>
	 */
	@PostMapping(value="/persons", consumes={"application/json"})
	public ResponseEntity<Object> addPerson(@Valid @RequestBody PersonDTO person) {
		try {
			Person added = service.addPerson(PersonConverter.toEntity(person));
			return new ResponseEntity<>(PersonConverter.toDTO(added), HttpStatus.OK);
		} catch (StorageException e) {
			return new ResponseEntity<>(
					new ControllerException(HttpStatus.INSUFFICIENT_STORAGE.value(), e.getMessage()), 
					HttpStatus.INSUFFICIENT_STORAGE);
		}
	}

	/**
	 * Update a person.
	 * @param firstName a String firstname.
	 * @param lastName a String lastname.
	 * @param person a PersonDTO object.
	 * @return ResponseEntity<Object>
	 */
	// /persons?firstName={firstname}&lastName={lastname}
	@PutMapping(value="/persons", consumes={"application/json"})
	public ResponseEntity<Object> updatePerson(
			@RequestParam @NotBlank String firstName, 
			@RequestParam @NotBlank String lastName, 
			@Valid @RequestBody PersonDTO person) {
		try {
			Person updated = service.updatePerson(firstName, lastName, PersonConverter.toEntity(person));
			return new ResponseEntity<>(PersonConverter.toDTO(updated), HttpStatus.OK);
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
	 * @param firstName a String firstname.
	 * @param lastName a String lastname.
	 * @return ResponseEntity<Object>
	 */
	// /persons?firstName={firstname}&lastName={lastname}
	@DeleteMapping("/persons")
	public ResponseEntity<Object> deletePerson(
			@RequestParam @NotBlank String firstName, 
			@RequestParam @NotBlank String lastName) {
		try {
			service.deletePerson(firstName, lastName);
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
