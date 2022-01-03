package com.tipikae.safetynetalerts.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tipikae.safetynetalerts.dto.MedicalRecordDTO;
import com.tipikae.safetynetalerts.exception.ConverterException;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.service.IMedicalRecordService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * A MedicalRecord controller CRUD.
 * @author tipikae
 * @version 1.0
 *
 */
@Validated
@RestController
public class MedicalRecordController {
	
	@Autowired
	private IMedicalRecordService service;

	/**
	 * Add a medical record.
	 * @param medicalRecordDTO a MedicalRecordDTO object.
	 * @return ResponseEntity
	 * @throws StorageException
	 * @throws ServiceException
	 * @throws ConverterException
	 */
	@ApiOperation(value="Adding a medical record")
	@ApiResponses(value = {  
		      @ApiResponse(code = 200, message = "Operation succeed!", response = MedicalRecordDTO.class),
		      @ApiResponse(code = 400, message = "Invalid parameters"),
		      @ApiResponse(code = 404, message = "Medical record already exists"),
		      @ApiResponse(code = 409, message = "Persistence problem, try later")})
	@PostMapping(value="/medicalrecord", consumes={"application/json"})
	public ResponseEntity<Object> addMedicalRecord(@Valid @RequestBody MedicalRecordDTO medicalRecordDTO)
			throws StorageException , ServiceException, ConverterException {
		MedicalRecordDTO added = service.addMedicalRecord(medicalRecordDTO);
		return new ResponseEntity<>(added, HttpStatus.OK);
	}

	/**
	 * Update a medical record.
	 * @param firstName a String firstname.
	 * @param lastName a String lastname.
	 * @param medicalRecordDTO a MedicalRecordDTO object.
	 * @return ResponseEntity
	 * @throws StorageException
	 * @throws ServiceException
	 * @throws ConverterException
	 */
	@ApiOperation(value="Updating a medical record")
	@ApiResponses(value = {  
		      @ApiResponse(code = 200, message = "Operation succeed!"),
		      @ApiResponse(code = 400, message = "Invalid parameters"),
		      @ApiResponse(code = 404, message = "Medical record not found"),
		      @ApiResponse(code = 409, message = "Persistence problem, try later")})
	// /medicalrecord?firstName={firstname}&lastName={lastname}
	@PutMapping(value="/medicalrecord", consumes={"application/json"})
	public ResponseEntity<Object> updateMedicalRecord(
			@RequestParam @NotBlank String firstName, 
			@RequestParam @NotBlank String lastName,
			@Valid @RequestBody MedicalRecordDTO medicalRecordDTO)
					throws StorageException , ServiceException, ConverterException {
		MedicalRecordDTO updated = service.updateMedicalRecord(firstName, lastName, medicalRecordDTO);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	/**
	 * Delete a medical record.
	 * @param firstName a String firstname.
	 * @param lastName a String lastname.
	 * @return ResponseEntity
	 * @throws StorageException
	 * @throws ServiceException
	 */
	@ApiOperation(value="Deleting a medical record")
	@ApiResponses(value = {  
		      @ApiResponse(code = 200, message = "Operation succeed!"),
		      @ApiResponse(code = 400, message = "Invalid parameters"),
		      @ApiResponse(code = 404, message = "Medical record not found"),
		      @ApiResponse(code = 409, message = "Persistence problem, try later")})
	// /medicalrecord?firstName={firstname}&lastName={lastname}
	@DeleteMapping("/medicalrecord")
	public ResponseEntity<Object> deleteMedicalRecord(
			@RequestParam @NotBlank String firstName, 
			@RequestParam @NotBlank String lastName)
					throws StorageException , ServiceException {
		service.deleteMedicalRecord(firstName, lastName);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
