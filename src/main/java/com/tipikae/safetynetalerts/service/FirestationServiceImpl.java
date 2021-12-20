package com.tipikae.safetynetalerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.safetynetalerts.dao.IFirestationDAO;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;

@Service
public class FirestationServiceImpl implements IFirestationService {
	
	private static final Logger LOGGER = LogManager.getLogger("JsonStorage");

	@Autowired
	private IFirestationDAO firestationDao;

	public void setFirestationDao(IFirestationDAO firestationDao) {
		this.firestationDao = firestationDao;
	}

	@Override
	public Firestation addFirestationMapping(Firestation firestation) throws StorageException {
		return firestationDao.save(firestation);
	}
	
	@Override
	public List<Firestation> getFirestations() throws StorageException {
		return firestationDao.findAll();
	}

	@Override
	public Firestation getFirestationByAddress(String address) throws ServiceException, StorageException {
		Firestation firestation = firestationDao.findByAddress(address);
		if (firestation != null) {
			return firestation;
		} else {
			LOGGER.error("getFirestationByAddress: Address: " + address + " not found in Firestation.");
			throw new ServiceException("Address: " + address + " not found in Firestation.");
		}
	}

	@Override
	public List<Firestation> getFirestationsByStation(int station) throws ServiceException, StorageException {
		List<Firestation> firestations = firestationDao.findAll();
		boolean exist = false;
		
		for(Firestation firestation: firestations) {
			if(firestation.getStation() == station) {
				exist = true;
				break;
			}
		}
		
		if(exist) {
			return firestationDao.findByStation(station);
		} else {
			LOGGER.error("getFirestationsByStation: Station: " + station + " not found in Firestation.");
			throw new ServiceException("Station: " + station + " not found in Firestation.");
		}
	}

	@Override
	public Firestation updateFirestationMapping(String address, Firestation newFirestation) 
			throws ServiceException, StorageException {
		Firestation oldFirestation = firestationDao.findByAddress(address);
		if(oldFirestation != null) {
			return firestationDao.update(oldFirestation, newFirestation);
		} else {
			LOGGER.error("updateFirestationMapping: Address: " + address + " not found in Firestation.");
			throw new ServiceException("Address: " + address + " not found in Firestation.");
		}
	}

	@Override
	public void deleteFirestationByAddress(String address) throws StorageException, ServiceException {
		Firestation firestation = firestationDao.findByAddress(address);
		if(firestation != null) {
			firestationDao.delete(firestation);
		} else {
			LOGGER.error("deleteFirestationByAddress: Address: " + address + " not found in Firestation.");
			throw new ServiceException("Address: " + address + " not found in Firestation.");
		}
	}

	@Override
	public void deleteFirestationsByStation(int station) throws ServiceException, StorageException {
		List<Firestation> firestations = firestationDao.findByStation(station);
		if(firestations != null) {
			firestationDao.deleteFirestations(firestations);
		} else {
			LOGGER.error("deleteFirestationsByStation: Station: " + station + " not found in Firestation.");
			throw new ServiceException("Station: " + station + " not found in Firestation.");
		}
	}

}
