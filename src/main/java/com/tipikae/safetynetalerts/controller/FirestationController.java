package com.tipikae.safetynetalerts.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tipikae.safetynetalerts.dto.FirestationDTO;
import com.tipikae.safetynetalerts.exception.ConverterException;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.service.IFirestationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * A Firestation controller.
 * @author tipikae
 * @version 1.0
 *
 */
@Validated
@RestController
public class FirestationController {
	
	@Autowired
	private IFirestationService service;

	/**
	 * Add a firestation mapping.
	 * @param firestationDTO a FirestationDTO object.
	 * @return ResponseEntity
	 * @throws StorageException
	 * @throws ServiceException
	 * @throws ConverterException
	 */
	@ApiOperation(value="Adding a firestation-address mapping")
	@ApiResponses(value = {  
		      @ApiResponse(code = 200, message = "Operation succeed!", response = FirestationDTO.class),
		      @ApiResponse(code = 400, message = "Invalid parameters"),
		      @ApiResponse(code = 404, message = "Mapping already exists"),
		      @ApiResponse(code = 409, message = "Persistence problem, try later")})
	@PostMapping(value="/firestation", consumes={"application/json"})
	public ResponseEntity<Object> addFirestationMapping(@Valid @RequestBody FirestationDTO firestationDTO)
			throws ServiceException, StorageException, ConverterException {
		FirestationDTO added = service.addFirestationMapping(firestationDTO);
		return new ResponseEntity<>(added, HttpStatus.OK);
	}

	/**
	 * Update a firestation mapping.
	 * @param address a String address.
	 * @param firestationDTO a FirestationDTO object.
	 * @return ResponseEntity
	 * @throws StorageException
	 * @throws ServiceException
	 * @throws ConverterException
	 */
	@ApiOperation(value="Updating a firestation-address mapping")
	@ApiResponses(value = {  
		      @ApiResponse(code = 200, message = "Operation succeed!"),
		      @ApiResponse(code = 400, message = "Invalid parameters"),
		      @ApiResponse(code = 404, message = "Mapping not found"),
		      @ApiResponse(code = 409, message = "Persistence problem, try later")})
	@PutMapping(value="/firestation/{address}", consumes={"application/json"})
	public ResponseEntity<Object> updateFirestationMapping(
			@PathVariable @NotBlank String address, 
			@Valid @RequestBody FirestationDTO firestationDTO)
					throws ServiceException, StorageException, ConverterException {
		FirestationDTO updated = service.updateFirestationMapping(address, firestationDTO);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	/**
	 * Delete firestations by address.
	 * @param address a String address.
	 * @return ResponseEntity
	 * @throws StorageException
	 * @throws ServiceException
	 */
	@ApiOperation(value="Deleting a firestation-address mapping")
	@ApiResponses(value = {  
		      @ApiResponse(code = 200, message = "Operation succeed!"),
		      @ApiResponse(code = 400, message = "Invalid parameters"),
		      @ApiResponse(code = 404, message = "Mapping not found"),
		      @ApiResponse(code = 409, message = "Persistence problem, try later")})
	@DeleteMapping("/firestation/{address}")
	public ResponseEntity<Object> deleteFirestationsByAddress(@PathVariable @NotBlank String address )
			throws ServiceException, StorageException {
		service.deleteFirestationByAddress(address);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Delete a firestation by station number.
	 * @param station an integer station number.
	 * @return ResponseEntity
	 * @throws StorageException
	 * @throws ServiceException
	 */
	@ApiOperation(value="Deleting a firestation-address mapping")
	@ApiResponses(value = {  
		      @ApiResponse(code = 200, message = "Operation succeed!"),
		      @ApiResponse(code = 400, message = "Invalid parameters"),
		      @ApiResponse(code = 404, message = "Mapping not found"),
		      @ApiResponse(code = 409, message = "Persistence problem, try later")})
	// /firestation?station={station}
	@DeleteMapping("/firestation")
	public ResponseEntity<Object> deleteFirestationByStation(@RequestParam @Positive @NotNull int station)
			throws ServiceException, StorageException {
		service.deleteFirestationsByStation(station);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}