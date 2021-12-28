package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * A DTO for Firestation model object.
 * @author tipikae
 * @version 1.0
 *
 */
public class FirestationDTO implements Serializable {

	/**
	 * Address.
	 */
	@NotBlank(message="Address is mandatory")
	private String address;
	/**
	 * Station number.
	 */
	@Positive(message="Station must be more than zero")
	private int station;

	/**
	 * The default constructor. 
	 */
	public FirestationDTO() {
		super();
	}

	/**
	 * The constructor.
	 * @param address a String.
	 * @param station an Integer station number.
	 */
	public FirestationDTO(String address, int station) {
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

