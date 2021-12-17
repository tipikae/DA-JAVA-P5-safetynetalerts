package com.tipikae.safetynetalerts.dao;

import java.util.List;

import com.tipikae.safetynetalerts.model.MedicalRecord;

public interface IMedicalRecordDAO {

	MedicalRecord save(MedicalRecord medicalRecord);
	List<MedicalRecord> findAll();
	MedicalRecord findByName(String firstname, String lastname);
	boolean update(String firstname, String lastname, MedicalRecord medicalRecord);
	boolean delete(String firstname, String lastname);
}
