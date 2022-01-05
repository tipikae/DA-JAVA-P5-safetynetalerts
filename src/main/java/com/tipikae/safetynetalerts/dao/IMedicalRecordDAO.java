package com.tipikae.safetynetalerts.dao;

import java.util.List;
import java.util.Optional;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.MedicalRecord;

/**
 * An interface for CRUD operations on MedicalRecord.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IMedicalRecordDAO {

	/**
	 * Save a MedicalRecord object.
	 * @param medicalRecord a MedicalRecord object.
	 * @return MedicalRecord
	 * @throws StorageException
	 */
	MedicalRecord save(MedicalRecord medicalRecord) throws StorageException;

	/**
	 * Find all medical records.
	 * @return List<MedicalRecord>
	 * @throws StorageException
	 */
	List<MedicalRecord> findAll() throws StorageException;

	/**
	 * Find a medical record by firstname and lastname.
	 * @param firstname a String firstname.
	 * @param lastname a String lastname.
	 * @return Optional<MedicalRecord>
	 * @throws StorageException
	 */
	Optional<MedicalRecord> findByFirstnameLastname(String firstname, String lastname) throws StorageException;

	/**
	 * Update a medical record.
	 * @param medicalRecord a MedicalRecord object.
	 * @return MedicalRecord
	 * @throws StorageException
	 */
	MedicalRecord update(MedicalRecord medicalRecord) throws StorageException;

	/**
	 * Delete a medical record.
	 * @param medicalRecord a MedicalRecord object.
	 * @throws StorageException
	 */
	void delete(MedicalRecord medicalRecord) throws StorageException;
}
