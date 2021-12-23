package com.tipikae.safetynetalerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.storage.JsonStorage;

@Repository
public class FirestationDAOImpl extends AbstractDAOImpl implements IFirestationDAO {
	
	public FirestationDAOImpl() {
		jsonStorage = new JsonStorage();
	}

	@Override
	public Firestation save(Firestation firestation) throws StorageException {
		storage = jsonStorage.readStorage();
		List<Firestation> firestations = storage.getFirestations();
		firestations.add(firestation);
		storage.setFirestations(firestations);
		jsonStorage.writeStorage(storage);
		
		return firestation;
	}

	@Override
	public List<Firestation> findAll() throws StorageException {
		storage = jsonStorage.readStorage();
		return storage.getFirestations();
	}

	@Override
	public Firestation findByAddress(String address) throws StorageException {
		storage = jsonStorage.readStorage();
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
	public List<Firestation> findByStation(int station) throws StorageException {
		storage = jsonStorage.readStorage();
		List<Firestation> results = new ArrayList<>();
		for (Firestation item : storage.getFirestations()) {
			if (item.getStation() == station) {
				results.add(item);
			}
		}
		
		return results;
	}

	@Override
	public Firestation update(Firestation firestation) throws StorageException {
		storage = jsonStorage.readStorage();
		List<Firestation> firestations = storage.getFirestations();
		int i = -1;
		
		for(int j = 0; j < firestations.size(); j++) {
			if(firestations.get(j).getAddress().equals(firestation.getAddress())) {
				i = j;
				break;
			}
		}
		
		if (i != -1) {
			firestations.set(i, firestation);
			storage.setFirestations(firestations);
			jsonStorage.writeStorage(storage);
			return firestation;
		} else {
			return null;
		}
	}

	/**
	 * Delete one map address-station
	 */
	@Override
	public void delete(Firestation firestation) throws StorageException {
		storage = jsonStorage.readStorage();
		List<Firestation> firestations = storage.getFirestations();
		int i = -1;
		
		for(int j = 0; j < firestations.size(); j++) {
			if(firestations.get(j).getAddress().equals(firestation.getAddress())) {
				i = j;
				break;
			}
		}
		
		if (i != -1) {
			firestations.remove(i);
			storage.setFirestations(firestations);
			jsonStorage.writeStorage(storage);
		}
	}

	/**
	 * Delete all maps with station number
	 * @throws StorageException 
	 */
	@Override
	public void deleteFirestations(List<Firestation> firestationsToRemove) throws StorageException {
		storage = jsonStorage.readStorage();
		List<Firestation> firestations = storage.getFirestations();
		boolean found = false;
		
		for(Firestation firestationToRemove: firestationsToRemove) {
			for(int i = 0; i < firestations.size(); i++) {
				if(firestations.get(i).getAddress().equals(firestationToRemove.getAddress())) {
					found = true;
					firestations.remove(i);
				}
			}
		}
		
		if (found) {
			storage.setFirestations(firestations);
			jsonStorage.writeStorage(storage);
		}
	}
}
