package com.tipikae.safetynetalerts.dao;

import com.tipikae.safetynetalerts.storage.JsonStorage;
import com.tipikae.safetynetalerts.storage.Storage;

public abstract class AbstractDAOImpl {

	protected JsonStorage jsonStorage;
	protected Storage storage;
	
	public JsonStorage getJsonStorage() {
		return jsonStorage;
	}
	public void setJsonStorage(JsonStorage jsonStorage) {
		this.jsonStorage = jsonStorage;
	}
	public Storage getStorage() {
		return storage;
	}
	public void setStorage(Storage storage) {
		this.storage = storage;
	}
}
