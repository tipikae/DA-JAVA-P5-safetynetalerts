package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for PhoneAlert request.
 * @author tipikae
 * @version 1.0
 *
 */
public class PhoneAlertDTO implements Serializable {

	/**
	 * Station number
	 */
	private int station;
	/**
	 * Phone numbers.
	 */
	private List<PhoneAlert> phones;

	/**
	 * The constructor.
	 * @param station an int station number.
	 * @param phones a List of PhoneAlert.
	 */
	public PhoneAlertDTO(int station, List<PhoneAlert> phones) {
		this.station = station;
		this.phones = phones;
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
	 * @param station an int.
	 */
	public void setStation(int station) {
		this.station = station;
	}

	/**
	 * Get phone numbers.
	 * @return List<PhoneAlert>
	 */
	public List<PhoneAlert> getPhones() {
		return phones;
	}

	/**
	 * Set phone numbers.
	 * @param phones a List of PhoneAlert.
	 */
	public void setPhones(List<PhoneAlert> phones) {
		this.phones = phones;
	}
	
}
