package com.tipikae.safetynetalerts.storage;

import java.util.List;

import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.model.MedicalRecord;
import com.tipikae.safetynetalerts.model.Person;

/**
 * An object storing data of json file.
 * @author tipikae
 * @version 1.0
 *
 */
public class Storage {

	/**
	 * Persons.
	 */
	private List<Person> persons;
	/**
	 * Firestations.
	 */
	private List<Firestation> firestations;
	/**
	 * MedicalRecords.
	 */
	private List<MedicalRecord> medicalrecords;

	/**
	 * The constructor.
	 * @param persons a List of Person.
	 * @param firestations a List of Firestation.
	 * @param medicalrecords a List of MedicalRecord.
	 */
	public Storage(List<Person> persons, List<Firestation> firestations, List<MedicalRecord> medicalrecords) {
		this.persons = persons;
		this.firestations = firestations;
		this.medicalrecords = medicalrecords;
	}

	/**
	 * Get persons.
	 * @return List<Person>
	 */
	public List<Person> getPersons() {
		return persons;
	}

	/**
	 * Set persons.
	 * @param persons a List of Person.
	 */
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	/**
	 * Get firestations.
	 * @return List<Firestation>
	 */
	public List<Firestation> getFirestations() {
		return firestations;
	}

	/**
	 * Set firestations.
	 * @param firestations a List of Firestation.
	 */
	public void setFirestations(List<Firestation> firestations) {
		this.firestations = firestations;
	}

	/**
	 * Get medicalRecords.
	 * @return List<MedicalRecord>
	 */
	public List<MedicalRecord> getMedicalRecords() {
		return medicalrecords;
	}

	/**
	 * Set medicalRecords.
	 * @param medicalrecords a List of MedicalRecord.
	 */
	public void setMedicalRecords(List<MedicalRecord> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}
}
