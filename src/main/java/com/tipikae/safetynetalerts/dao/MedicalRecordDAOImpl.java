package com.tipikae.safetynetalerts.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.storage.JsonStorage;

@Repository
public class MedicalRecordDAOImpl extends AbstractDAOImpl implements IMedicalRecordDAO {
	
	public MedicalRecordDAOImpl() {
		jsonStorage = new JsonStorage();
	}

	@Override
	public MedicalRecord save(MedicalRecord medicalRecord) throws StorageException {
		storage = jsonStorage.readStorage();
		List<MedicalRecord> medicalRecords = storage.getMedicalRecords();
		medicalRecords.add(medicalRecord);
		storage.setMedicalRecords(medicalRecords);
		jsonStorage.writeStorage(storage);
		
		return medicalRecord;
	}

	@Override
	public List<MedicalRecord> findAll() throws StorageException {
		storage = jsonStorage.readStorage();
		return storage.getMedicalRecords();
	}

	@Override
	public MedicalRecord findByFirstnameLastname(String firstname, String lastname) throws StorageException {
		storage = jsonStorage.readStorage();
		MedicalRecord medicalRecord = null;
		for (MedicalRecord item : storage.getMedicalRecords()) {
			if (item.getFirstname().equals(firstname) && item.getLastname().equals(lastname)) {
				medicalRecord = new MedicalRecord(firstname, lastname, item.getBirthdate(), item.getMedications(),
						item.getAllergies());
				break;
			}
		}
		
		return medicalRecord;
	}

	@Override
	public MedicalRecord update(MedicalRecord medicalRecord) throws StorageException {
		storage = jsonStorage.readStorage();
		List<MedicalRecord> medicalRecords = storage.getMedicalRecords();
		int i = -1;
		
		for(int j = 0; j < medicalRecords.size(); j++) {
			if(medicalRecords.get(j).getFirstname().equals(medicalRecord.getFirstname()) && 
					medicalRecords.get(j).getLastname().equals(medicalRecord.getLastname())) {
				i = j;
				break;
			}
		}
		
		if (i != -1) {
			medicalRecords.set(i, medicalRecord);
			storage.setMedicalRecords(medicalRecords);
			jsonStorage.writeStorage(storage);
			return medicalRecord;
		} else {
			return null;
		}
	}

	@Override
	public void delete(MedicalRecord medicalRecord) throws StorageException {
		storage = jsonStorage.readStorage();
		List<MedicalRecord> medicalRecords = storage.getMedicalRecords();
		int i = -1;
		
		for(int j = 0; j < medicalRecords.size(); j++) {
			if(medicalRecords.get(j).getFirstname().equals(medicalRecord.getFirstname()) && 
					medicalRecords.get(j).getLastname().equals(medicalRecord.getLastname())) {
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
