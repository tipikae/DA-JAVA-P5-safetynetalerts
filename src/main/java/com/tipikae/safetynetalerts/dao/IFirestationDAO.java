package com.tipikae.safetynetalerts.dao;

import java.util.List;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;

/**
 * An interface for CRUD operations on Firestation.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IFirestationDAO {

	/**
	 * Find all firestations.
	 * @return List<Firestation>
	 * @throws StorageException
	 */
	List<Firestation> findAll() throws StorageException;

	/**
	 * Find a firestation by address.
	 * @param address a String address.
	 * @return Firestation
	 * @throws StorageException
	 */
	Firestation findByAddress(String address) throws StorageException;

	/**
	 * Find firestations by station number.
	 * @param station an Integer station number.
	 * @return List<Firestation>
	 * @throws StorageException
	 */
	List<Firestation> findByStation(int station) throws StorageException;

	/**
	 * Save a Firestation object.
	 * @param firestation a Firestation object.
	 * @return Firestation
	 * @throws StorageException
	 */
	Firestation save(Firestation firestation) throws StorageException;

	/**
	 * Update a Firestation.
	 * @param firestation a Firestation object.
	 * @return Firestation
	 * @throws StorageException
	 */
	Firestation update(Firestation firestation) throws StorageException;

	/**
	 * Delete a Firestation object.
	 * @param firestation a Firestation object.
	 * @throws StorageException
	 */
	void delete(Firestation firestation) throws StorageException;

	/**
	 * Delete firestations by station numbers.
	 * @param firestationsToRemove a Integer List of station numbers.
	 * @throws StorageException
	 */
	void deleteFirestations(List<Firestation> firestationsToRemove) throws StorageException;
}
