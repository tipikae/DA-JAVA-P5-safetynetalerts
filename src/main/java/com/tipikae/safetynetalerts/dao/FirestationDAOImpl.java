package com.tipikae.safetynetalerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;

@Repository
public class FirestationDAOImpl extends AbstractDAOImpl implements IFirestationDAO {
	
	public FirestationDAOImpl() throws StorageException {
		super();
	}

	@Override
	public Firestation save(Firestation firestation) throws StorageException {
		List<Firestation> firestations = storage.getFirestations();
		firestations.add(firestation);
		storage.setFirestations(firestations);
		jsonStorage.writeStorage(storage);
		
		return firestation;
	}

	@Override
	public List<Firestation> findAll() {
		return storage.getFirestations();
	}

	@Override
	public Firestation findByAddress(String address) {
		Firestation firestation = null;
		for (Firestation item : storage.getFirestations()) {
			if (item.getAddress().equals(address)) {
				firestation = new Firestation(item.getAddress(), item.getStation());
				break;
			}
		}
		
		return firestation;
	}

	@Override
	public List<Firestation> findByStation(int station) {
		List<Firestation> results = new ArrayList<>();
		for (Firestation item : storage.getFirestations()) {
			if (item.getStation() == station) {
				results.add(item);
			}
		}
		
		return results;
	}

	@Override
	public Firestation update(Firestation oldFirestation, Firestation newFirestation) throws StorageException {
		List<Firestation> firestations = storage.getFirestations();
		int i = firestations.indexOf(oldFirestation);
		firestations.set(i, newFirestation);
		
		storage.setFirestations(firestations);
		jsonStorage.writeStorage(storage);
		
		return newFirestation;
	}

	/**
	 * Delete one map address-station
	 */
	@Override
	public void delete(Firestation firestation) throws StorageException {
		List<Firestation> firestations = storage.getFirestations();
		firestations.remove(firestation);
		
		storage.setFirestations(firestations);
		jsonStorage.writeStorage(storage);
	}

	/**
	 * Delete all maps with station number
	 * @throws StorageException 
	 */
	@Override
	public void deleteFirestations(List<Firestation> firestationsToRemove) throws StorageException {
		List<Firestation> firestations = storage.getFirestations();
		for(Firestation firestationToRemove: firestationsToRemove) {
			firestations.remove(firestationToRemove);
		}
		
		storage.setFirestations(firestations);
		jsonStorage.writeStorage(storage);
	}
}
