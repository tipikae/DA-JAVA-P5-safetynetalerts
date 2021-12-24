package com.tipikae.safetynetalerts.service;

import java.util.List;

import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.MedicalRecord;

/**
 * An interface providing services for MedicalRecordController.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IMedicalRecordService {

	/**
	 * Add a medical record.
	 * @param medicalRecord a MedicalRecord object.
	 * @return MedicalRecord
	 * @throws StorageException
	 */
	MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) throws StorageException;
	/**
	 * Get all medical records.
	 * @return List<MedicalRecord>
	 * @throws StorageException
	 */
	List<MedicalRecord> getMedicalRecords() throws StorageException;
	/**
	 * Get a medical record by firstname and lastname.
	 * @param firstname a String.
	 * @param lastname a String.
	 * @return MedicalRecord
	 * @throws ServiceException
	 * @throws StorageException
	 */
	MedicalRecord getMedicalRecordByFirstnameLastname(String firstname, String lastname) 
			throws ServiceException, StorageException;
	/**
	 * Update a medical record.
	 * @param firstname a String.
	 * @param lastname a String.
	 * @param medicalRecord a MedicalRecord object.
	 * @return MedicalRecord
	 * @throws ServiceException
	 * @throws StorageException
	 */
	MedicalRecord updateMedicalRecord(String firstname, String lastname, MedicalRecord medicalRecord) 
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
