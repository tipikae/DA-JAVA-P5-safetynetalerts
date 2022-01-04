package com.tipikae.safetynetalerts.storage;

import com.tipikae.safetynetalerts.exception.StorageException;

/**
 * Storage interface.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IStorage {

	/**
	 * Read the storage.
	 * @return Storage object.
	 * @throws StorageException
	 */
	Storage readStorage() throws StorageException;
	/**
	 * Write the storage.
	 * @param storage a Storage object.
	 * @throws StorageException
	 */
	void writeStorage(Storage storage) throws StorageException;
}
