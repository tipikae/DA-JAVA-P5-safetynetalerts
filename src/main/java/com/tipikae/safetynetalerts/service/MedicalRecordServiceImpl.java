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
	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecordDao.findAll();
	}

}
