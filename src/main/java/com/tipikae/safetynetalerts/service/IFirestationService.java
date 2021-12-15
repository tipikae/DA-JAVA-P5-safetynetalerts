package com.tipikae.safetynetalerts.service;

import java.util.List;

import com.tipikae.safetynetalerts.model.Firestation;

public interface IFirestationService {

	List<Firestation> getFirestations();
	Firestation getFirestationByAddress(String address);
	List<Firestation> getFirestationsByStation(int station);
	Firestation addFirestationMapping(Firestation firestation);
	boolean updateFirestationMapping(Firestation firestation);
	boolean deleteFirestationsByStation(int station);
	boolean deleteFirestationByAddress(String address);
}
