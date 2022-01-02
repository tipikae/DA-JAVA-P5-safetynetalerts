package com.tipikae.safetynetalerts.service;

import com.tipikae.safetynetalerts.dto.MedicalRecordDTO;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;

/**
 * An interface providing services for MedicalRecordController.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IMedicalRecordService {

	/**
	 * Add a medical record.
	 * @param medicalRecord a MedicalRecordDTO object.
	 * @return MedicalRecordDTO
	 * @throws ServiceException
	 * @throws StorageException
	 */
	MedicalRecordDTO addMedicalRecord(MedicalRecordDTO medicalRecordDTO) throws ServiceException, StorageException;
	/**
	 * Update a medical record.
	 * @param firstname a String.
	 * @param lastname a String.
	 * @param medicalRecord a MedicalRecordDTO object.
	 * @return MedicalRecordDTO
	 * @throws ServiceException
	 * @throws StorageException
	 */
	MedicalRecordDTO updateMedicalRecord(String firstname, String lastname, MedicalRecordDTO medicalRecordDTO) 
			throws ServiceException, StorageException;
	/**
	 * Delete a medical record.
	 * @param firstname a String.
	 * @param lastname a String.
	 * @throws ServiceException
	 * @throws StorageException
	 */
	void deleteMedicalRecord(String firstname, String lastname) throws ServiceException, StorageException;
}
