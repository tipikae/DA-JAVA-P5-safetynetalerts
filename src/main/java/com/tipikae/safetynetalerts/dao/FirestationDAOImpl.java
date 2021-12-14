package com.tipikae.safetynetalerts.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tipikae.safetynetalerts.models.Firestation;
import com.tipikae.safetynetalerts.util.JsonStorage;

@Repository
public class FirestationDAOImpl implements IFirestationDAO {

	@Override
	public Firestation save(String address, int station) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Firestation> findAll() {
		JsonStorage jsonStorage = new JsonStorage();
		return jsonStorage.readStorage().getFirestations();
	}

	@Override
	public List<Firestation> findByAddress(String address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Firestation> findByStation(int station) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(String address, int station) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByAddress(String address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByStation(int station) {
		// TODO Auto-generated method stub
		
	}

}
