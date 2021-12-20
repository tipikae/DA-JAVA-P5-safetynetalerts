package com.tipikae.safetynetalerts.dao;

import java.util.List;

import com.tipikae.safetynetalerts.exception.StorageException;
import com.tipikae.safetynetalerts.model.Firestation;

public interface IFirestationDAO {

	List<Firestation> findAll();
	Firestation findByAddress(String address);
	List<Firestation> findByStation(int station);
	Firestation save(Firestation firestation) throws StorageException;
	Firestation update(Firestation oldFirestation, Firestation firestation) throws StorageException;
	boolean deleteByAddress(String address);
	boolean deleteByStation(int station);
}
