package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

public class FloodAddress implements Serializable {

	private String address;
	private List<Flood> members;
	
	public FloodAddress(String address, List<Flood> members) {
		this.address = address;
		this.members = members;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Flood> getMembers() {
		return members;
	}

	public void setMembers(List<Flood> members) {
		this.members = members;
	}
	
}
