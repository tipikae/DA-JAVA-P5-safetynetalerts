package com.tipikae.safetynetalerts.dao;

import com.tipikae.safetynetalerts.storage.JsonStorage;
import com.tipikae.safetynetalerts.storage.Storage;

/**
 * An abstract class for DAOs.
 * @author tipikae
 * @version 1.0
 *
 */
public abstract class AbstractDAOImpl {

	/**
	 * The JsonStorage.
	 */
	protected JsonStorage jsonStorage;
	/**
	 * The Storage object.
	 */
	protected Storage storage;

	/**
	 * Get the JsonStorage.
	 * @return JsonStorage
	 */
	public JsonStorage getJsonStorage() {
		return jsonStorage;
	}

	/**
	 * Set the JsonStorage.
	 * @param jsonStorage
	 */
	public void setJsonStorage(JsonStorage jsonStorage) {
		this.jsonStorage = jsonStorage;
	}

	/**
	 * Get the Storage object.
	 * @return Storage
	 */
	public Storage getStorage() {
		return storage;
	}

	/**
	 * Set the Storage.
	 * @param storage
	 */
	public void setStorage(Storage storage) {
		this.storage = storage;
	}
}
