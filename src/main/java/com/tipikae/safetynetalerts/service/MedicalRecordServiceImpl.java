package com.tipikae.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.safetynetalerts.dao.IMedicalRecordDAO;
import com.tipikae.safetynetalerts.model.MedicalRecord;

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService {

	@Autowired
	private IMedicalRecordDAO medicalRecordDao;

	@Override
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordDao.save(medicalRecord);
	}
	
	@Override
	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecordDao.findAll();
	}

	@Override
	public MedicalRecord getMedicalRecordByName(String firstname, String lastname) {
		return medicalRecordDao.findByName(firstname, lastname);
	}

	@Override
	public boolean updateMedicalRecord(String firstname, String lastname, MedicalRecord medicalRecord) {
		return medicalRecordDao.update(firstname, lastname, medicalRecord);
	}

	@Override
	public boolean deleteMedicalRecord(String firstname, String lastname) {
		return medicalRecordDao.delete(firstname, lastname);
	}

}
