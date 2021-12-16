package com.tipikae.safetynetalerts.dao;

import com.tipikae.safetynetalerts.model.Storage;
import com.tipikae.safetynetalerts.util.JsonStorage;

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