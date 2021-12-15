package com.tipikae.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.safetynetalerts.dao.IFirestationDAO;
import com.tipikae.safetynetalerts.model.Firestation;

@Service
public class FirestationServiceImpl implements IFirestationService {

	@Autowired
	private IFirestationDAO firestationDao;
	
	@Override
	public List<Firestation> getFirestations() {
		return firestationDao.findAll();
	}

	@Override
	public Firestation getFirestationByAddress(String address) {
		return firestationDao.findByAddress(address);
	}

	@Override
	public List<Firestation> getFirestationsByStation(int station) {
		return firestationDao.findByStation(station);
	}

	@Override
	public Firestation addFirestationMapping(Firestation firestation) {
		return firestationDao.save(firestation);
	}

	@Override
	public boolean updateFirestationMapping(Firestation firestation) {
		return firestationDao.update(firestation);
	}

	@Override
	public boolean deleteFirestationsByStation(int station) {
		return firestationDao.deleteByStation(station);
	}

	@Override
	public boolean deleteFirestationByAddress(String address) {
		return firestationDao.deleteByAddress(address);
	}

}
