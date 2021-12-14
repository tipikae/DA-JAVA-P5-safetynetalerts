package com.tipikae.safetynetalerts.models;

import java.util.List;

public class Storage {

	private List<Person> persons;
	private List<Firestation> firestations;
	private List<MedicalRecord> medicalrecords;
	
	public Storage(List<Person> persons, List<Firestation> firestations, List<MedicalRecord> medicalrecords) {
		this.persons = persons;
		this.firestations = firestations;
		this.medicalrecords = medicalrecords;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<Firestation> getFirestations() {
		return firestations;
	}

	public void setFirestations(List<Firestation> firestations) {
		this.firestations = firestations;
	}

	public List<MedicalRecord> getMedicalRecords() {
		return medicalrecords;
	}

	public void setMedicalRecords(List<MedicalRecord> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}
}
