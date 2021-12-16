package com.tipikae.safetynetalerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.tipikae.safetynetalerts.model.Firestation;

@Repository
public class FirestationDAOImpl extends AbstractDAOImpl implements IFirestationDAO {
	
	public FirestationDAOImpl() {
		super();
	}
	
	private static final Logger LOGGER = LogManager.getLogger("FirestationDAOImpl");

	@Override
	public Firestation save(Firestation firestation) {
		if (!firestation.getAddress().equals("") && firestation.getStation() != 0) {
			
			if (storage != null) {
				List<Firestation> firestations = storage.getFirestations();
				if(firestations == null) {
					firestations = new ArrayList<>();
				}
				
				firestations.add(firestation);
				storage.setFirestations(firestations);
				
				if (jsonStorage.writeStorage(storage)) {
					return firestation;
				}
			}
		}
		return null;
	}

	@Override
	public List<Firestation> findAll() {
		List<Firestation> firestations = null;
		
		if(storage != null) {
			firestations = storage.getFirestations();
		}
		
		return firestations;
	}

	@Override
	public Firestation findByAddress(String address) {
		Firestation firestation = null;
		if (address != "") {
			List<Firestation> firestations = findAll();
			if (firestations != null && !firestations.isEmpty()) {
				for (Firestation item : firestations) {
					if (item.getAddress().equals(address)) {
						firestation = new Firestation(item.getAddress(), item.getStation());
						break;
					}
				}
			} 
		}
		return firestation;
	}

	@Override
	public List<Firestation> findByStation(int station) {
		List<Firestation> results = null;
		if (station != 0) {
			List<Firestation> firestations = findAll();
			if (firestations != null && !firestations.isEmpty()) {
				results = new ArrayList<>();
				for (Firestation item : firestations) {
					if (item.getStation() == station) {
						results.add(item);
					}
				}
			} 
		}
		return results;
	}

	@Override
	public boolean update(Firestation newFirestation) {
		if (!newFirestation.getAddress().equals("") && newFirestation.getStation() != 0) {
			boolean found = false;
			
			if (storage!= null) {
				List<Firestation> firestations = storage.getFirestations();
				
				if (firestations != null) {
					for (int i = 0; i < firestations.size(); i++) {
						if (firestations.get(i).getAddress().equals(newFirestation.getAddress())) {
							found = true;
							firestations.set(i, newFirestation);
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
