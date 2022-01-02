package com.tipikae.safetynetalerts.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.storage.JsonStorage;

/**
 * An IFirestationDAO implementation.
 * @author tipikae
 * @version 1.0
 *
 */
@Repository
public class FirestationDAOImpl extends AbstractDAOImpl implements IFirestationDAO {

	/**
	 * The constructor.
	 */
	public FirestationDAOImpl() {
		jsonStorage = new JsonStorage();
	}

	/**
	 * {@inheritDoc}
	 * @param firestation {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public Optional<Firestation> save(Firestation firestation) throws StorageException {
		storage = jsonStorage.readStorage();
		List<Firestation> firestations = storage.getFirestations();
		firestations.add(firestation);
		storage.setFirestations(firestations);
		jsonStorage.writeStorage(storage);
		
		return Optional.of(firestation);
	}

	/**
	 * {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public Optional<List<Firestation>> findAll() throws StorageException {
		storage = jsonStorage.readStorage();
		return Optional.ofNullable(storage.getFirestations());
	}

	/**
	 * {@inheritDoc}
	 * @param address {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public Optional<Firestation> findByAddress(String address) throws StorageException {
		storage = jsonStorage.readStorage();
		Firestation firestation = null;
		for (Firestation item : storage.getFirestations()) {
			if (item.getAddress().equals(address)) {
				firestation = new Firestation(item.getAddress(), item.getStation());
				break;
			}
		}
		
		return Optional.ofNullable(firestation);
	}

	/**
	 * {@inheritDoc}
	 * @param station {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public Optional<List<Firestation>> findByStation(int station) throws StorageException {
		storage = jsonStorage.readStorage();
		List<Firestation> results = null;
		for (Firestation item : storage.getFirestations()) {
			if (item.getStation() == station) {
				if(results == null) {
					results = new ArrayList<>();
				}
				results.add(item);
			}
		}
		
		return Optional.ofNullable(results);
	}

	/**
	 * {@inheritDoc}
	 * @param firestation {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public Optional<Firestation> update(Firestation firestation) throws StorageException {
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
			return Optional.of(firestation);
		} else {
			return Optional.empty();
		}
	}

	/**
	 * {@inheritDoc}
	 * @param firestation {@inheritDoc}
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
	 * {@inheritDoc}
	 * @param firestationsToRemove {@inheritDoc}
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
