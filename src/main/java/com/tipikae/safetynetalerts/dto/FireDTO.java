package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

public class FireDTO implements Serializable {

	private String address;
	private int station;
	private List<Fire> members;
	
	public FireDTO(String address, int station, List<Fire> members) {
		this.address = address;
		this.station = station;
		this.members = members;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

	public List<Fire> getMembers() {
		return members;
	}

	public void setMembers(List<Fire> members) {
		this.members = members;
	}
	
}
