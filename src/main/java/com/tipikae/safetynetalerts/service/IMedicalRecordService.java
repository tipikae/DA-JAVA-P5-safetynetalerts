package com.tipikae.safetynetalerts.service;

import java.util.List;

import com.tipikae.safetynetalerts.model.MedicalRecord;

public interface IMedicalRecordService {

	MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);
	List<MedicalRecord> getMedicalRecords();
	MedicalRecord getMedicalRecordByName(String firstname, String lastname);
	boolean updateMedicalRecord(MedicalRecord medicalRecord);
	boolean deleteMedicalRecord(String firstname, String lastname);
}
