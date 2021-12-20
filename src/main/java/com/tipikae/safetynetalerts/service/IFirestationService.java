package com.tipikae.safetynetalerts.service;

import java.util.List;

import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;

public interface IFirestationService {

	Firestation addFirestationMapping(Firestation firestation) throws StorageException;
	List<Firestation> getFirestations();
	Firestation getFirestationByAddress(String address) throws ServiceException;
	List<Firestation> getFirestationsByStation(int station);
	Firestation updateFirestationMapping(String address, Firestation newFirestation) 
			throws ServiceException, StorageException;
	boolean deleteFirestationsByStation(int station);
	boolean deleteFirestationByAddress(String address);
}
