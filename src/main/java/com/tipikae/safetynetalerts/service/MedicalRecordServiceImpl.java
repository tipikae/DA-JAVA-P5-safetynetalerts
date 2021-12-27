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

/**
 * An implementation of IMedicalRecordService.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService {

	private static final Logger LOGGER = LogManager.getLogger("MedicalRecordService");

	/**
	 * The DAO.
	 */
	@Autowired
	private IMedicalRecordDAO medicalRecordDao;

	/**
	 * Set medicalRecordDao.
	 * @param medicalRecordDao a IMedicalRecordDAO interface.
	 */
	public void setMedicalRecordDao(IMedicalRecordDAO medicalRecordDao) {
		this.medicalRecordDao = medicalRecordDao;
	}

	/**
	 * {@inheritDoc}
	 * @param medicalRecord {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) throws StorageException {
		return medicalRecordDao.save(medicalRecord);
	}

	/**
	 * {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public List<MedicalRecord> getMedicalRecords() throws StorageException {
		return medicalRecordDao.findAll();
	}

	/**
	 * {@inheritDoc}
	 * @param firstname {@inheritDoc}
	 * @param lastname {@inheritDoc}
	 * @return {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 * @param firstname {@inheritDoc}
	 * @param lastname {@inheritDoc}
	 * @param medicalRecord {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public MedicalRecord updateMedicalRecord(String firstname, String lastname, MedicalRecord medicalRecord) 
			throws ServiceException, StorageException {
		if (firstname.equals(medicalRecord.getFirstName()) && lastname.equals(medicalRecord.getLastName())) {
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
					+ lastname + " are different from Person firstname: " + medicalRecord.getFirstName() 
					+ " lastname: " + medicalRecord.getLastName());
			throw new ServiceException("Firstname: " + firstname + " and lastname:"
					+ lastname + " are different from Person firstname: " + medicalRecord.getFirstName()
					+ "	lastname: " + medicalRecord.getLastName());
		}
	}

	/**
	 * {@inheritDoc}
	 * @param firstname {@inheritDoc}
	 * @param lastname {@inheritDoc}
	 */
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
