package com.tipikae.safetynetalerts.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tipikae.safetynetalerts.models.MedicalRecord;
import com.tipikae.safetynetalerts.util.JsonStorage;

@Repository
public class MedicalRecordDAOImpl implements IMedicalRecordDAO {

	@Override
	public MedicalRecord save(MedicalRecord medicalRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MedicalRecord> findAll() {
		JsonStorage jsonStorage = new JsonStorage();
		return jsonStorage.readStorage().getMedicalRecords();
	}

	@Override
	public MedicalRecord findByName(String firstname, String lastname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(MedicalRecord medicalRecord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String firstname, String lastname) {
		// TODO Auto-generated method stub
		
	}

}
