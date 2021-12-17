package com.tipikae.safetynetalerts.dao;

import java.util.List;

import com.tipikae.safetynetalerts.model.Firestation;

public interface IFirestationDAO {

	Firestation save(Firestation firestation);
	List<Firestation> findAll();
	Firestation findByAddress(String address);
	List<Firestation> findByStation(int station);
	boolean update(String address, Firestation firestation);
	boolean deleteByAddress(String address);
	boolean deleteByStation(int station);
}
