package com.tipikae.safetynetalerts.dao;

import java.util.List;

import com.tipikae.safetynetalerts.models.Firestation;

public interface IFirestationDAO {

	Firestation save(String address, int station);
	List<Firestation> findAll();
	List<Firestation> findByAddress(String address);
	List<Firestation> findByStation(int station);
	void update(String address, int station);
	void deleteByAddress(String address);
	void deleteByStation(int station);
}
