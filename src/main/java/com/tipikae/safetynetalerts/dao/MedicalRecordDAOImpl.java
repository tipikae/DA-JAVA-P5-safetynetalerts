package com.tipikae.safetynetalerts.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.storage.IStorage;

/**
 * An implementation of IMedicalRecordDAO.
 * @author tipikae
 * @version 1.0
 *
 */
@Repository
public class MedicalRecordDAOImpl extends AbstractDAOImpl implements IMedicalRecordDAO {

	@Autowired
	private IStorage jsonStorage;

	/**
	 * {@inheritDoc}
	 * @param medicalRecord {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public MedicalRecord save(MedicalRecord medicalRecord) throws StorageException {
		storage = jsonStorage.readStorage();
		List<MedicalRecord> medicalRecords = storage.getMedicalRecords();
		medicalRecords.add(medicalRecord);
		storage.setMedicalRecords(medicalRecords);
		jsonStorage.writeStorage(storage);
		
		return medicalRecord;
	}

	/**
	 * {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public List<MedicalRecord> findAll() throws StorageException {
		storage = jsonStorage.readStorage();
		return storage.getMedicalRecords();
	}

	/**
	 * {@inheritDoc}
	 * @param firstname {@inheritDoc}
	 * @param lastname {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public Optional<MedicalRecord> findByFirstnameLastname(String firstname, String lastname) throws StorageException {
		storage = jsonStorage.readStorage();
		for (MedicalRecord item : storage.getMedicalRecords()) {
			if (item.getFirstName().equals(firstname) && item.getLastName().equals(lastname)) {
				return Optional.of(item);
			}
		}
		
		return Optional.empty();
	}

	/**
	 * {@inheritDoc}
	 * @param medicalRecord {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public MedicalRecord update(MedicalRecord medicalRecord) throws StorageException {
		storage = jsonStorage.readStorage();
		List<MedicalRecord> medicalRecords = storage.getMedicalRecords();
		int i = -1;
		
		for(int j = 0; j < medicalRecords.size(); j++) {
			if(medicalRecords.get(j).getFirstName().equals(medicalRecord.getFirstName()) && 
					medicalRecords.get(j).getLastName().equals(medicalRecord.getLastName())) {
				i = j;
				break;
			}
		}
		
		medicalRecords.set(i, medicalRecord);
		storage.setMedicalRecords(medicalRecords);
		jsonStorage.writeStorage(storage);
		return medicalRecord;
	}

	/**
	 * {@inheritDoc}
	 * @param medicalRecord {@inheritDoc}
	 */
	@Override
	public void delete(MedicalRecord medicalRecord) throws StorageException {
		storage = jsonStorage.readStorage();
		List<MedicalRecord> medicalRecords = storage.getMedicalRecords();
		int i = -1;
		
		for(int j = 0; j < medicalRecords.size(); j++) {
			if(medicalRecords.get(j).getFirstName().equals(medicalRecord.getFirstName()) && 
					medicalRecords.get(j).getLastName().equals(medicalRecord.getLastName())) {
				i = j;
				break;
			}
		}
		
		if (i != -1) {
			medicalRecords.remove(i);
			storage.setMedicalRecords(medicalRecords);
			jsonStorage.writeStorage(storage);
		}
	}

}
