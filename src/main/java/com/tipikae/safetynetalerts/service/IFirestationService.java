package com.tipikae.safetynetalerts.service;

import com.tipikae.safetynetalerts.dto.FirestationDTO;
import com.tipikae.safetynetalerts.exception.ConverterException;
import com.tipikae.safetynetalerts.exception.ServiceException;
import com.tipikae.safetynetalerts.exception.StorageException;

/**
 * An interface providing services for FirestationController.
 * @author tipikae
 * @version
 *
 */
public interface IFirestationService {

	/**
	 * Add a firestation mapping.
	 * @param firestationDTO a FirestationDTO object.
	 * @return FirestationDTO
	 * @throws ServiceException
	 * @throws StorageException
	 * @throws ConverterException
	 */
	FirestationDTO addFirestationMapping(FirestationDTO firestationDTO) 
			throws ServiceException, StorageException, ConverterException;
	/**
	 * Update a firestation mapping.
	 * @param address a String.
	 * @param newFirestationDTO a FirestationDTO object.
	 * @return FirestationDTO
	 * @throws ServiceException
	 * @throws StorageException
	 * @throws ConverterException
	 */
	FirestationDTO updateFirestationMapping(String address, FirestationDTO newFirestationDTO) 
			throws ServiceException, StorageException, ConverterException;
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
