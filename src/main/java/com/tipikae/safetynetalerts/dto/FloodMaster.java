package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

/**
 * A Flood master object for wrapping response.
 * @author tipikae
 * @version 1.0
 *
 */
public class FloodMaster implements Serializable {

	/**
	 * Station number.
	 */
	private int station;
	/**
	 * Addresses.
	 */
	private List<FloodAddress> adresses;

	/**
	 * The constructor.
	 * @param station an Integer station number.
	 * @param adresses a List of addresses.
	 */
	public FloodMaster(int station, List<FloodAddress> adresses) {
		this.station = station;
		this.adresses = adresses;
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
	 * @param station an Integer station number.
	 */
	public void setStation(int station) {
		this.station = station;
	}

	/**
	 * Get addresses.
	 * @return List<FloodAddress>
	 */
	public List<FloodAddress> getAdresses() {
		return adresses;
	}

	/**
	 * Set addresses.
	 * @param adresses a List of addresses.
	 */
	public void setAdresses(List<FloodAddress> adresses) {
		this.adresses = adresses;
	}
	
}
