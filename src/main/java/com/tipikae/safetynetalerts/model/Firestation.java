package com.tipikae.safetynetalerts.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


public class Firestation implements Serializable {

	@NotBlank(message="Address is mandatory")
	private String address;
	
	@Positive(message="Station must be more than zero")
	private int station;
	
	public Firestation(String address, int station) {
		this.address = address;
		this.station = station;
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
}
