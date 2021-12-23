package com.tipikae.safetynetalerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.safetynetalerts.dao.IMedicalRecordDAO;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.MedicalRecord;

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService {

	private static final Logger LOGGER = LogManager.getLogger("MedicalRecordService");

	@Autowired
	private IMedicalRecordDAO medicalRecordDao;

	public void setMedicalRecordDao(IMedicalRecordDAO medicalRecordDao) {
		this.medicalRecordDao = medicalRecordDao;
	}

	@Override
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) throws StorageException {
		return medicalRecordDao.save(medicalRecord);
	}
	
	@Override
	public List<MedicalRecord> getMedicalRecords() throws StorageException {
		return medicalRecordDao.findAll();
	}

	@Override
	public MedicalRecord getMedicalRecordByFirstnameLastname(String firstname, String lastname) 
			throws StorageException, ServiceException {
		MedicalRecord medicalRecord = medicalRecordDao.findByFirstnameLastname(firstname, lastname);
		if (medicalRecord != null) {
			return medicalRecord;
		} else {
			LOGGER.error("getMedicalRecordByFirstnameLastname: Firstname: " + firstname + " and lastname:" 
					+ lastname + " not found in MedicalRecord.");
			throw new ServiceException("Firstname: " + firstname + " and lastname:" + lastname 
					+ " not found in MedicalRecord.");
		}
	}

	@Override
	public MedicalRecord updateMedicalRecord(String firstname, String lastname, MedicalRecord medicalRecord) 
			throws ServiceException, StorageException {
		if (firstname.equals(medicalRecord.getFirstname()) && lastname.equals(medicalRecord.getLastname())) {
			if (medicalRecordDao.findByFirstnameLastname(firstname, lastname) != null) {
				return medicalRecordDao.update(medicalRecord);
			} else {
				LOGGER.error("updateMedicalRecord: Firstname: " + firstname + " and lastname:"
						+ lastname + " not found in MedicalRecord.");
				throw new ServiceException("Firstname: " + firstname + " and lastname:"
						+ lastname + " not found in MedicalRecord.");
			} 
		} else {
			LOGGER.error("updateMedicalRecord: Firstname: " + firstname + " and lastname:"
					+ lastname + " are different from Person firstname: " + medicalRecord.getFirstname() 
					+ " lastname: " + medicalRecord.getLastname());
			throw new ServiceException("Firstname: " + firstname + " and lastname:"
					+ lastname + " are different from Person firstname: " + medicalRecord.getFirstname()
					+ "	lastname: " + medicalRecord.getLastname());
		}
	}

	@Override
	public void deleteMedicalRecord(String firstname, String lastname) throws ServiceException, StorageException {
		MedicalRecord medicalRecord = medicalRecordDao.findByFirstnameLastname(firstname, lastname);
		if(medicalRecord != null) {
			medicalRecordDao.delete(medicalRecord);
		} else {
			LOGGER.error("deleteMedicalRecord: Firstname: " + firstname + " and lastname:"
					+ lastname + " not found in MedicalRecord.");
			throw new ServiceException("Firstname: " + firstname + " and lastname:"
					+ lastname + " not found in MedicalRecord.");
		}
	}

}
