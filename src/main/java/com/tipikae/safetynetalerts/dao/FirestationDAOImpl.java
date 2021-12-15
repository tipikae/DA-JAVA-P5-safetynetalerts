package com.tipikae.safetynetalerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.tipikae.safetynetalerts.model.Firestation;
import com.tipikae.safetynetalerts.model.Storage;
import com.tipikae.safetynetalerts.util.JsonStorage;

@Repository
public class FirestationDAOImpl implements IFirestationDAO {
	
	private static final Logger LOGGER = LogManager.getLogger("FirestationDAOImpl");

	@Override
	public Firestation save(Firestation firestation) {
		Firestation added = null;
		JsonStorage jsonStorage = new JsonStorage();
		Storage storage = jsonStorage.readStorage();
		List<Firestation> firestations = storage.getFirestations();
		firestations.add(firestation);
		storage.setFirestations(firestations);
		if(jsonStorage.writeStorage(storage)) {
			added = firestation;
		}
		
		return added;
	}

	@Override
	public List<Firestation> findAll() {
		JsonStorage jsonStorage = new JsonStorage();
		return jsonStorage.readStorage().getFirestations();
	}

	@Override
	public Firestation findByAddress(String address) {
		Firestation firestation = null;
		List<Firestation> firestations = findAll();
		
		for(Firestation item: firestations) {
			if(item.getAddress().equals(address)) {
				firestation = new Firestation(item.getAddress(), item.getStation());
				break;
			}
		}
		
		return firestation;
	}

	@Override
	public List<Firestation> findByStation(int station) {
		List<Firestation> results = new ArrayList<>();
		List<Firestation> firestations = findAll();
		
		for(Firestation item: firestations) {
			if(item.getStation() == station) {
				results.add(item);
			}
		}
		
		return results;
	}

	@Override
	public boolean update(Firestation newFirestation) {
		boolean found = false;
		JsonStorage jsonStorage = new JsonStorage();
		Storage storage = jsonStorage.readStorage();
		List<Firestation> firestations = storage.getFirestations();
		
		for(int i = 0; i < firestations.size(); i++) {
			if(firestations.get(i).getAddress().equals(newFirestation.getAddress())) {
				found = true;
				firestations.set(i, newFirestation);
				break;
			}
		}
		
		if(found) {
			storage.setFirestations(firestations);
			if(jsonStorage.writeStorage(storage)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean deleteByAddress(String address) {
		boolean found = false;
		JsonStorage jsonStorage = new JsonStorage();
		Storage storage = jsonStorage.readStorage();
		List<Firestation> firestations = storage.getFirestations();
		
		for(int i = 0; i < firestations.size(); i++) {
			if(firestations.get(i).getAddress().equals(address)) {
				found = true;
				firestations.remove(i);
				break;
			}
		}
		
		if(found) {
			storage.setFirestations(firestations);
			if(jsonStorage.writeStorage(storage)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean deleteByStation(int station) {
		boolean found = false;
		JsonStorage jsonStorage = new JsonStorage();
		Storage storage = jsonStorage.readStorage();
		List<Firestation> firestations = storage.getFirestations();
		
		for(int i = 0; i < firestations.size(); i++) {
			if(firestations.get(i).getStation() == station) {
				found = true;
				firestations.remove(i);
			}
		}
		
		if(found) {
			storage.setFirestations(firestations);
			if(jsonStorage.writeStorage(storage)) {
				return true;
			}
		}
		
		return false;
	}
}
