package com.tipikae.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tipikae.safetynetalerts.model.Person;
import com.tipikae.safetynetalerts.service.IPersonService;

@RestController
public class PersonController {
	
	@Autowired
	private IPersonService service;

	@RequestMapping(value = "/persons", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> listePersons() {
		List<Person> persons = service.getPersons();
		if(persons == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(persons, HttpStatus.OK); 
	}
}
