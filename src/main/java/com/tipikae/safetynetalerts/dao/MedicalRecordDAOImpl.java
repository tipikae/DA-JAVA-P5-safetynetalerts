package com.tipikae.safetynetalerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tipikae.safetynetalerts.model.MedicalRecord;

@Repository
public class MedicalRecordDAOImpl extends AbstractDAOImpl implements IMedicalRecordDAO {
	
	public MedicalRecordDAOImpl() {
		super();
	}

	@Override
	public MedicalRecord save(MedicalRecord medicalRecord) {
		if (!medicalRecord.getFirstname().equals("") && !medicalRecord.getLastname().equals("") && 
				medicalRecord.getBirthdate() != null && medicalRecord.getMedications() != null && 
				medicalRecord.getAllergies() != null) {
			
			if (storage != null) {
				List<MedicalRecord> medicalRecords = storage.getMedicalRecords();
				if(medicalRecords == null) {
					medicalRecords = new ArrayList<>();
				}
				
				medicalRecords.add(medicalRecord);
				storage.setMedicalRecords(medicalRecords);
				
				if (jsonStorage.writeStorage(storage)) {
					return medicalRecord;
				}
			}
		}
		return null;
	}

	@Override
	public List<MedicalRecord> findAll() {
		List<MedicalRecord> medicalRecords = null;
		
		if(storage != null) {
			medicalRecords = storage.getMedicalRecords();
		}
		
		return medicalRecords;
	}

	@Override
	public MedicalRecord findByName(String firstname, String lastname) {
		MedicalRecord medicalRecord = null;
		if (firstname != "" && lastname != "") {
			List<MedicalRecord> medicalRecords = findAll();
			if (medicalRecords != null && !medicalRecords.isEmpty()) {
				for (MedicalRecord item : medicalRecords) {
					if (item.getFirstname().equals(firstname) && item.getLastname().equals(lastname)) {
						medicalRecord = item;
						break;
					}
				}
			} 
		}
		return medicalRecord;
	}

	@Override
	public boolean update(String firstname, String lastname, MedicalRecord medicalRecord) {
		if (!medicalRecord.getFirstname().equals("") && !medicalRecord.getLastname().equals("") && 
				medicalRecord.getBirthdate() != null && medicalRecord.getMedications() != null && 
				medicalRecord.getAllergies() != null && firstname.equals(medicalRecord.getFirstname()) && 
				lastname.equals(medicalRecord.getLastname())) {
			boolean found = false;
			
			if (storage!= null) {
				List<MedicalRecord> medicalRecords = storage.getMedicalRecords();
				
				if (medicalRecords != null) {
					for (int i = 0; i < medicalRecords.size(); i++) {
						if (medicalRecords.get(i).getFirstname().equals(medicalRecord.getFirstname()) && 
								medicalRecords.get(i).getLastname().equals(medicalRecord.getLastname())) {
							found = true;
							medicalRecords.set(i, medicalRecord);
							break;
						}
					}
					if (found) {
						storage.setMedicalRecords(medicalRecords);
						if (jsonStorage.writeStorage(storage)) {
							return true;
						}
					} 
				} 
			} 
		}
		return false;
	}

	@Override
	public boolean delete(String firstname, String lastname) {
		if (firstname != "" && lastname != "") {
			boolean found = false;
			
			if (storage != null) {
				List<MedicalRecord> medicalRecords = storage.getMedicalRecords();
				
				if (medicalRecords != null) {
					for (int i = 0; i < medicalRecords.size(); i++) {
						if (medicalRecords.get(i).getFirstname().equals(firstname) && 
								medicalRecords.get(i).getLastname().equals(lastname)) {
							found = true;
							medicalRecords.remove(i);
							break;
						}
					}
					if (found) {
						storage.setMedicalRecords(medicalRecords);
						if (jsonStorage.writeStorage(storage)) {
							return true;
						}
					} 
				} 
			} 
		}
		return false;
	}

}
