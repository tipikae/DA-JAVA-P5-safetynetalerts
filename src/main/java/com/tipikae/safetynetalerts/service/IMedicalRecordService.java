package com.tipikae.safetynetalerts.service;

import java.util.List;

import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.MedicalRecord;

public interface IMedicalRecordService {

	MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) throws StorageException;
	List<MedicalRecord> getMedicalRecords() throws StorageException;
	MedicalRecord getMedicalRecordByFirstnameLastname(String firstname, String lastname) 
			throws ServiceException, StorageException;
	MedicalRecord updateMedicalRecord(String firstname, String lastname, MedicalRecord medicalRecord) 
			throws ServiceException, StorageException;
	void deleteMedicalRecord(String firstname, String lastname) throws ServiceException, StorageException;
}
