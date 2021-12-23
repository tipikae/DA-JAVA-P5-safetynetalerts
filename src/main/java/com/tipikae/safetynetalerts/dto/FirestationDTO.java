package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

public class FirestationDTO implements Serializable {

	private int station;
	private int adults;
	private int children;
	private List<FirestationInfo> residents;
	
	public FirestationDTO(int station, int adults, int children, List<FirestationInfo> residents) {
		this.station = station;
		this.adults = adults;
		this.children = children;
		this.residents = residents;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public List<FirestationInfo> getResidents() {
		return residents;
	}

	public void setResidents(List<FirestationInfo> residents) {
		this.residents = residents;
	}
}
