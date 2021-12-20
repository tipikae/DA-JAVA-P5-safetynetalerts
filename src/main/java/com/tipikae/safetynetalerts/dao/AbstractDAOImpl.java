package com.tipikae.safetynetalerts.dao;

import com.tipikae.safetynetalerts.storage.JsonStorage;
import com.tipikae.safetynetalerts.storage.Storage;

public abstract class AbstractDAOImpl {

	protected JsonStorage jsonStorage;
	protected Storage storage;
	
	public AbstractDAOImpl() {
		this.jsonStorage = new JsonStorage();
		this.storage = this.jsonStorage.readStorage();
	}

	public void setJsonStorage(JsonStorage jsonStorage) {
		this.jsonStorage = jsonStorage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}
}
