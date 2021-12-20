package com.tipikae.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.safetynetalerts.dao.IFirestationDAO;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;

@Service
public class FirestationServiceImpl implements IFirestationService {

	@Autowired
	private IFirestationDAO firestationDao;

	@Override
	public Firestation addFirestationMapping(Firestation firestation) throws StorageException {
		return firestationDao.save(firestation);
	}
	
	@Override
	public List<Firestation> getFirestations() {
		return firestationDao.findAll();
	}

	@Override
	public Firestation getFirestationByAddress(String address) throws ServiceException {
		Firestation firestation = firestationDao.findByAddress(address);
		if (firestation != null) {
			return firestationDao.findByAddress(address);
		} else {
			throw new ServiceException("Address: " + address + " not found in Firestation.");
		}
	}

	@Override
	public List<Firestation> getFirestationsByStation(int station) {
		return firestationDao.findByStation(station);
	}

	@Override
	public Firestation updateFirestationMapping(String address, Firestation newFirestation) 
			throws ServiceException, StorageException {
		Firestation oldFirestation = firestationDao.findByAddress(address);
		if(oldFirestation != null) {
			return firestationDao.update(oldFirestation, newFirestation);
		} else {
			throw new ServiceException("Address: " + address + " not found in Firestation.");
		}
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
