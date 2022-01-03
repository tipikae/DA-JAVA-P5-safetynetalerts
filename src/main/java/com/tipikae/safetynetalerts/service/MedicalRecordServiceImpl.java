package com.tipikae.safetynetalerts.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.safetynetalerts.dao.IMedicalRecordDAO;
import com.tipikae.safetynetalerts.dto.MedicalRecordDTO;
import com.tipikae.safetynetalerts.dtoconverter.IMedicalRecordConverter;
import com.tipikae.safetynetalerts.exception.ConverterException;
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

	private static final Logger LOGGER = LogManager.getLogger("MedicalRecordServiceImpl");

	/**
	 * The DAO.
	 */
	@Autowired
	private IMedicalRecordDAO dao;
	
	/**
	 * The DTO converter.
	 */
	@Autowired
	private IMedicalRecordConverter converter;

	/**
	 * {@inheritDoc}
	 * @param medicalRecord {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws {@inheritDoc}
	 * @throws {@inheritDoc}
	 * @throws {@inheritDoc}
	 */
	@Override
	public MedicalRecordDTO addMedicalRecord(MedicalRecordDTO medicalRecordDTO) 
			throws ServiceException, StorageException, ConverterException {
		MedicalRecord medicalRecord = converter.toEntity(medicalRecordDTO);
		Optional<MedicalRecord> optional = dao.findByFirstnameLastname(medicalRecord.getFirstName(), 
				medicalRecord.getLastName());
		if(!optional.isPresent()) {
			return converter.toDTO(dao.save(medicalRecord).get());
		} else {
			LOGGER.error("addMedicalRecord: medical record with firstname: " + medicalRecord.getFirstName()
					+ " and lastname: " + medicalRecord.getLastName() + " already exists.");
			throw new ServiceException("Medical record with firstname: " + medicalRecord.getFirstName()
					+ " and lastname: " + medicalRecord.getLastName() 
					+ " already exists.");
		}
		
	}

	/**
	 * {@inheritDoc}
	 * @param firstname {@inheritDoc}
	 * @param lastname {@inheritDoc}
	 * @param medicalRecord {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws {@inheritDoc}
	 * @throws {@inheritDoc}
	 * @throws {@inheritDoc}
	 */
	@Override
	public MedicalRecordDTO updateMedicalRecord(String firstname, String lastname, MedicalRecordDTO medicalRecordDTO) 
			throws ServiceException, StorageException, ConverterException {
		if (firstname.equals(medicalRecordDTO.getFirstName()) && lastname.equals(medicalRecordDTO.getLastName())) {
			MedicalRecord medicalRecord = converter.toEntity(medicalRecordDTO);
			Optional<MedicalRecord> optional = dao.findByFirstnameLastname(firstname, lastname);
			if (optional.isPresent()) {
				return converter.toDTO(dao.update(medicalRecord).get());
			} else {
				LOGGER.error("updateMedicalRecord: Firstname: " + firstname + " and lastname:"
						+ lastname + " not found in MedicalRecord.");
				throw new ServiceException("Firstname: " + firstname + " and lastname:"
						+ lastname + " not found in MedicalRecord.");
			} 
		} else {
			LOGGER.error("updateMedicalRecord: Firstname: " + firstname + " and lastname:"
					+ lastname + " are different from Person firstname: " + medicalRecordDTO.getFirstName() 
					+ " lastname: " + medicalRecordDTO.getLastName());
			throw new ServiceException("Firstname: " + firstname + " and lastname:"
					+ lastname + " are different from Person firstname: " + medicalRecordDTO.getFirstName()
					+ "	lastname: " + medicalRecordDTO.getLastName());
		}
	}

	/**
	 * {@inheritDoc}
	 * @param firstname {@inheritDoc}
	 * @param lastname {@inheritDoc}
	 * @throws {@inheritDoc}
	 * @throws {@inheritDoc}
	 */
	@Override
	public void deleteMedicalRecord(String firstname, String lastname) throws ServiceException, StorageException {
		Optional<MedicalRecord> optional = dao.findByFirstnameLastname(firstname, lastname);
		if(optional.isPresent()) {
			MedicalRecord medicalRecord = optional.get();
			dao.delete(medicalRecord);
		} else {
			LOGGER.error("deleteMedicalRecord: Firstname: " + firstname + " and lastname:"
					+ lastname + " not found in MedicalRecord.");
			throw new ServiceException("Firstname: " + firstname + " and lastname:"
					+ lastname + " not found in MedicalRecord.");
		}
	}

}
