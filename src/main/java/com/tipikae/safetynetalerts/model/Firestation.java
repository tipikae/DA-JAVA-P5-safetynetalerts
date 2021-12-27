package com.tipikae.safetynetalerts.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * A class for Firestation entity.
 * @author tipikae
 * @version 1.0
 *
 */
public class Firestation implements Serializable {

	/**
	 * Address.
	 */
	private String address;
	/**
	 * Station number.
	 */
	private int station;

	/**
	 * The default constructor. 
	 */
	public Firestation() {
		super();
	}

	/**
	 * The constructor.
	 * @param address a String.
	 * @param station an Integer station number.
	 */
	public Firestation(String address, int station) {
		this.address = address;
		this.station = station;
	}

	/**
	 * Get address.
	 * @return String
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set address.
	 * @param address a String.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get station number.
	 * @return int
	 */
	public int getStation() {
		return station;
	}

	/**
	 * Set station number.
	 * @param station an int station number.
	 */
	public void setStation(int station) {
		this.station = station;
	}
}
