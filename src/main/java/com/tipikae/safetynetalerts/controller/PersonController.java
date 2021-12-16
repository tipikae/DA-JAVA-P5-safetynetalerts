package com.tipikae.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tipikae.safetynetalerts.model.Person;
import com.tipikae.safetynetalerts.service.IPersonService;

@RestController
public class PersonController {
	
	@Autowired
	private IPersonService service;

	@GetMapping("/persons")
    public ResponseEntity<List<Person>> allPersons() {
		List<Person> persons = service.getPersons();
		if(persons != null && !persons.isEmpty()) {
			return new ResponseEntity<>(persons, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// /persons?address={address}
	@GetMapping("/persons")
    public ResponseEntity<List<Person>> personsByAddress(@RequestParam String address) {
		if (address != "") {
			List<Person> persons = service.getPersonsByAddress(address);
			if (persons != null) {
				return new ResponseEntity<>(persons, HttpStatus.OK); 
			}
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// /persons?city={city}
	@GetMapping("/persons")
    public ResponseEntity<List<Person>> personsByCity(@RequestParam String city) {
		if (city != "") {
			List<Person> persons = service.getPersonsByCity(city);
			if (persons != null) {
				return new ResponseEntity<>(persons, HttpStatus.OK); 
			}
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// /persons?firstname={firstname}&lastname={lastname}
	@GetMapping("/persons")
    public ResponseEntity<Person> personByName(@RequestParam String firstname, @RequestParam String lastname) {
		if (firstname != "" && lastname != "") {
			Person person = service.getPersonByName(firstname, lastname);
			if (person != null) {
				return new ResponseEntity<>(person, HttpStatus.OK); 
			}
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(value="/persons", consumes={"application/json"})
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		if(!person.getFirstname().equals("") && !person.getLastname().equals("") && 
				!person.getAddress().equals("") && !person.getCity().equals("") && !person.getZip().equals("") && 
				!person.getPhone().equals("") && !person.getEmail().equals("")) {
			Person added = service.addPerson(person);
			if(added != null) {
				return new ResponseEntity<>(person, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(value="/persons", consumes={"application/json"})
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
		if(!person.getFirstname().equals("") && !person.getLastname().equals("") && 
				!person.getAddress().equals("") && !person.getCity().equals("") && !person.getZip().equals("") && 
				!person.getPhone().equals("") && !person.getEmail().equals("")) {
			
			if (service.updatePerson(person)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	// /person?firstname={firstname}&lastname={lastname}
	@DeleteMapping("/person")
	public ResponseEntity<Person> deletePerson(@RequestParam String firstname, @RequestParam String lastname) {
		if(firstname != "" && lastname != "") {
			if (service.deletePerson(firstname, lastname)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
}
