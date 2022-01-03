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

import com.tipikae.safetynetalerts.dto.ChildAlertDTO;
import com.tipikae.safetynetalerts.dto.CommunityEmailDTO;
import com.tipikae.safetynetalerts.dto.DTOResponse;
import com.tipikae.safetynetalerts.dto.FireDTO;
import com.tipikae.safetynetalerts.dto.FirestationInfoDTO;
import com.tipikae.safetynetalerts.dto.FloodDTO;
import com.tipikae.safetynetalerts.dto.PersonInfoDTO;
import com.tipikae.safetynetalerts.dto.PhoneAlertDTO;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.service.IInformationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
	 * @throws StorageException
	 */
	@ApiOperation(value="Getting all residents by station")
	@ApiResponses(value = {  
		      @ApiResponse(code = 200, message = "Operation succeed!", response = FirestationInfoDTO.class),
		      @ApiResponse(code = 409, message = "Persistence problem, try later")})
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
	 * @throws StorageException
	 */
	@ApiOperation(value="Getting all children by address")
	@ApiResponses(value = {  
		      @ApiResponse(code = 200, message = "Operation succeed!", response = ChildAlertDTO.class),
		      @ApiResponse(code = 409, message = "Persistence problem, try later")})
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
	 * @throws StorageException
	 */
	@ApiOperation(value="Getting all phone numbers by station")
	@ApiResponses(value = {  
		      @ApiResponse(code = 200, message = "Operation succeed!", response = PhoneAlertDTO.class),
		      @ApiResponse(code = 409, message = "Persistence problem, try later")})
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
	 * @throws StorageException
	 */
	@ApiOperation(value="Getting all members by address")
	@ApiResponses(value = {  
		      @ApiResponse(code = 200, message = "Operation succeed!", response = FireDTO.class),
		      @ApiResponse(code = 409, message = "Persistence problem, try later")})
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
	 * @throws StorageException
	 */
	@ApiOperation(value="Getting all residents by stations")
	@ApiResponses(value = {  
		      @ApiResponse(code = 200, message = "Operation succeed!", response = FloodDTO.class),
		      @ApiResponse(code = 409, message = "Persistence problem, try later")})
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
	 * @throws StorageException
	 */
	@ApiOperation(value="Getting person information by lastname")
	@ApiResponses(value = {  
		      @ApiResponse(code = 200, message = "Operation succeed!", response = PersonInfoDTO.class),
		      @ApiResponse(code = 409, message = "Persistence problem, try later")})
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
	 * @throws StorageException
	 */
	@ApiOperation(value="Getting all emails by city")
	@ApiResponses(value = {  
		      @ApiResponse(code = 200, message = "Operation succeed!", response = CommunityEmailDTO.class),
		      @ApiResponse(code = 409, message = "Persistence problem, try later")})
	// /communityEmail?city=<city>
	@GetMapping(value="/communityEmail", params="city")
	public ResponseEntity<Object> emailsByCity(@RequestParam @NotBlank String city)
			throws StorageException {
		DTOResponse dto = service.getEmailsByCity(city);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
}
