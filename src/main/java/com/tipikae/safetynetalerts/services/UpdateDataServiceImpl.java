package com.tipikae.safetynetalerts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.safetynetalerts.dao.IFirestationDAO;
import com.tipikae.safetynetalerts.dao.IMedicalRecordDAO;
import com.tipikae.safetynetalerts.dao.IPersonDAO;
import com.tipikae.safetynetalerts.models.Firestation;
import com.tipikae.safetynetalerts.models.MedicalRecord;
import com.tipikae.safetynetalerts.models.Person;

@Service
public class UpdateDataServiceImpl implements IUpdateDataService {

	@Autowired
	private IPersonDAO personDao;
	@Autowired
	private IFirestationDAO firestationDao;
	@Autowired
	private IMedicalRecordDAO medicalRecordDao;
	
	@Override
	public List<Firestation> getFirestations() {
		return firestationDao.findAll();
	}
	
	@Override
	public List<Person> getPersons() {
		return personDao.findAll();
	}

	@Override
	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecordDao.findAll();
	}
}
