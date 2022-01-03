package com.tipikae.safetynetalerts.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tipikae.safetynetalerts.dto.DTOResponse;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.service.IInformationService;

/**
 * An information controller.
 * @author tipikae
 * @version 1.0
 *
 */
@Validated
@RestController
public class InformationController {

	@Autowired
	private IInformationService service;

	/**
	 * Get residents by station number.
	 * @param stationNumber an Integer station number.
	 * @return ResponseEntity
	 */
	// /firestation?stationNumber=<station_number>
	@GetMapping(value="/firestation", params="stationNumber")
    public ResponseEntity<Object> residentsByStation(@RequestParam @Positive int stationNumber)
			throws StorageException {
		DTOResponse dto = service.getResidentsByStation(stationNumber);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	/**
	 * Get children by address.
	 * @param address a String address.
	 * @return ResponseEntity
	 */
	// /childAlert?address=<address>
	@GetMapping(value="/childAlert", params="address")
    public ResponseEntity<Object> childrenByAddress(@RequestParam @NotBlank String address)
			throws StorageException {
		DTOResponse dto = service.getChildrenByAddress(address);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	/**
	 * Get phone numbers by station number.
	 * @param firestation an Integer station number.
	 * @return ResponseEntity
	 */
	// /phoneAlert?firestation=<firestation_number>
	@GetMapping(value="/phoneAlert", params="firestation")
    public ResponseEntity<Object> phoneNumbersByStation(@RequestParam @Positive int firestation)
			throws StorageException {
		DTOResponse dto = service.getPhoneNumbersByStation(firestation);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	/**
	 * Get members by address.
	 * @param address a String address.
	 * @return ResponseEntity
	 */
	// /fire?address=<address>
	@GetMapping(value="/fire", params="address")
    public ResponseEntity<Object> membersByAddress(@RequestParam @NotBlank String address)
			throws StorageException {
		DTOResponse dto = service.getMembersByAddress(address);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	/**
	 * Get residents by stations.
	 * @param stations a List of Integer station number.
	 * @return ResponseEntity
	 */
	// /flood/stations?stations=<a list of station_numbers>
	@GetMapping(value="/flood/stations", params="stations")
	public ResponseEntity<Object> residentsByStations(
			@RequestParam @NotEmpty(message = "Station number list cannot be empty.") List<Integer> stations)
					throws StorageException {
		DTOResponse dto = service.getResidentsByStations(stations);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	/**
	 * Get a person information by lastname.
	 * @param firstName a String firstname.
	 * @param lastName a String lastname.
	 * @return ResponseEntity
	 */
	// /personInfo?firstName=<firstname>&lastName=<lastname>
	@GetMapping(value="/personInfo", params={"firstName", "lastName"})
	public ResponseEntity<Object> personInfoByLastname(
			@RequestParam @NotBlank String firstName, 
    		@RequestParam @NotBlank String lastName)
    				throws StorageException {
		DTOResponse dto = service.getPersonInfoByLastname(firstName, lastName);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	/**
	 * Get emails by city.
	 * @param city a String city.
	 * @return ResponseEntity
	 */
	// /communityEmail?city=<city>
	@GetMapping(value="/communityEmail", params="city")
	public ResponseEntity<Object> emailsByCity(@RequestParam @NotBlank String city)
			throws StorageException {
		DTOResponse dto = service.getEmailsByCity(city);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
}
