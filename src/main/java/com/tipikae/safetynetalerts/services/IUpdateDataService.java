package com.tipikae.safetynetalerts.services;

import java.util.List;

import com.tipikae.safetynetalerts.models.Firestation;
import com.tipikae.safetynetalerts.models.MedicalRecord;
import com.tipikae.safetynetalerts.models.Person;

public interface IUpdateDataService {

	/*
	 * Person
	 */
	List<Person> getPersons();
	
	/*
	 * Firestation
	 */
	List<Firestation> getFirestations();
	
	/*
	 * MedicalRecord
	 */
	List<MedicalRecord> getMedicalRecords();
}
