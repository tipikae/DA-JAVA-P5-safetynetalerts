package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

public class FloodDTO implements Serializable {

	private int station;
	private List<FloodAddress> adresses;
	
	public FloodDTO(int station, List<FloodAddress> adresses) {
		this.station = station;
		this.adresses = adresses;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

	public List<FloodAddress> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<FloodAddress> adresses) {
		this.adresses = adresses;
	}
	
}
