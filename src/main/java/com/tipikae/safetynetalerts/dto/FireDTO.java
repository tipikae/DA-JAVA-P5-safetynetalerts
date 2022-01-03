package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for Fire request.
 * @author tipikae
 * @version 1.0
 *
 */
public class FireDTO extends DTOResponse implements Serializable {

	/**
	 * Address.
	 */
	private String address;
	/**
	 * Station number.
	 */
	private int station;
	/**
	 * Members.
	 */
	private List<Fire> members;

	/**
	 * The constructor.
	 * @param address a String address.
	 * @param station a station number Integer.
	 * @param members a members list.
	 */
	public FireDTO(String address, int station, List<Fire> members) {
		this.address = address;
		this.station = station;
		this.members = members;
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
	 * @param address a String address.
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
	 * @param station a station number Integer.
	 */
	public void setStation(int station) {
		this.station = station;
	}

	/**
	 * Get members.
	 * @return List<Fire>
	 */
	public List<Fire> getMembers() {
		return members;
	}

	/**
	 * Set members.
	 * @param members a members List.
	 */
	public void setMembers(List<Fire> members) {
		this.members = members;
	}
	
}
