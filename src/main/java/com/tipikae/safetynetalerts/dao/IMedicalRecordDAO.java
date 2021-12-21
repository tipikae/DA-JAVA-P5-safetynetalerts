package com.tipikae.safetynetalerts.dao;

import java.util.List;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.MedicalRecord;

public interface IMedicalRecordDAO {

	MedicalRecord save(MedicalRecord medicalRecord) throws StorageException;
	List<MedicalRecord> findAll() throws StorageException;
	MedicalRecord findByFirstnameLastname(String firstname, String lastname) throws StorageException;
	MedicalRecord update(MedicalRecord medicalRecord) throws StorageException;
	void delete(MedicalRecord medicalRecord) throws StorageException;
}
