package com.tipikae.safetynetalerts.service;

import java.util.List;

import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;

/**
 * An interface providing services for FirestationController.
 * @author tipikae
 * @version
 *
 */
public interface IFirestationService {

	/**
	 * Add a firestation mapping.
	 * @param firestation a Firestation object.
	 * @return Firestation
	 * @throws StorageException
	 */
	Firestation addFirestationMapping(Firestation firestation) throws StorageException;
	/**
	 * Get all firestations.
	 * @return List<Firestation>
	 * @throws StorageException
	 */
	List<Firestation> getFirestations() throws StorageException;
	/**
	 * Get a firestation by address.
	 * @param address a String.
	 * @return Firestation
	 * @throws ServiceException
	 * @throws StorageException
	 */
	Firestation getFirestationByAddress(String address) throws ServiceException, StorageException;
	/**
	 * Get firestations by station number.
	 * @param station an int station number.
	 * @return List<Firestation>
	 * @throws ServiceException
	 * @throws StorageException
	 */
	List<Firestation> getFirestationsByStation(int station) throws ServiceException, StorageException;
	/**
	 * Update a firestation mapping.
	 * @param address a String.
	 * @param newFirestation a Firestation object.
	 * @return Firestation
	 * @throws ServiceException
	 * @throws StorageException
	 */
	Firestation updateFirestationMapping(String address, Firestation newFirestation) 
			throws ServiceException, StorageException;
	/**
	 * Delete a firestation by address.
	 * @param address a String.
	 * @throws StorageException
	 * @throws ServiceException
	 */
	void deleteFirestationByAddress(String address) throws StorageException, ServiceException;
	/**
	 * Delete firestations by station number.
	 * @param station an int station number.
	 * @throws ServiceException
	 * @throws StorageException
	 */
	void deleteFirestationsByStation(int station) throws ServiceException, StorageException;
}
