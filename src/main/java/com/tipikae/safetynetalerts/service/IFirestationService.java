package com.tipikae.safetynetalerts.service;

import java.util.List;

import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;

public interface IFirestationService {

	Firestation addFirestationMapping(Firestation firestation) throws StorageException;
	List<Firestation> getFirestations() throws StorageException;
	Firestation getFirestationByAddress(String address) throws ServiceException, StorageException;
	List<Firestation> getFirestationsByStation(int station) throws ServiceException, StorageException;
	Firestation updateFirestationMapping(String address, Firestation newFirestation) 
			throws ServiceException, StorageException;
	void deleteFirestationByAddress(String address) throws StorageException, ServiceException;
	void deleteFirestationsByStation(int station) throws ServiceException, StorageException;
}
