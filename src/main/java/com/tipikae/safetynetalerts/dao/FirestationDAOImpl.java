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
	public boolean deleteByAddress(String address) {
		if (!address.equals("")) {
			boolean found = false;
			
			if (storage != null) {
				List<Firestation> firestations = storage.getFirestations();
				
				if (firestations != null) {
					for (int i = 0; i < firestations.size(); i++) {
						if (firestations.get(i).getAddress().equals(address)) {
							found = true;
							firestations.remove(i);
							break;
						}
					}
					if (found) {
						storage.setFirestations(firestations);
						if (jsonStorage.writeStorage(storage)) {
							return true;
						}
					} 
				} 
			} 
		}
		return false;
	}

	/**
	 * Delete all maps with station number
	 */
	@Override
	public boolean deleteByStation(int station) {
		if (station != 0) {
			boolean found = false;
			
			if (storage != null) {
				List<Firestation> firestations = storage.getFirestations();
				
				if (firestations != null) {
					for (int i = 0; i < firestations.size(); i++) {
						if (firestations.get(i).getStation() == station) {
							found = true;
							firestations.remove(i);
						}
					}
					if (found) {
						storage.setFirestations(firestations);
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
