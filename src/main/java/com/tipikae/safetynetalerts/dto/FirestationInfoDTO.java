package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for FirestationInfo request.
 * @author tipikae
 * @version 1.0
 *
 */
public class FirestationInfoDTO extends DTOResponse implements Serializable {

	/**
	 * Station number.
	 */
	private int station;
	/**
	 * Adults number.
	 */
	private int adults;
	/**
	 * Children number.
	 */
	private int children;
	/**
	 * Residents.
	 */
	private List<FirestationInfo> residents;

	/**
	 * The constructor.
	 * @param station a station number Integer.
	 * @param adults an adults number Integer.
	 * @param children a children number Integer.
	 * @param residents a residents List.
	 */
	public FirestationInfoDTO(int station, int adults, int children, List<FirestationInfo> residents) {
		this.station = station;
		this.adults = adults;
		this.children = children;
		this.residents = residents;
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
	 * Get adults number
	 * @return int
	 */
	public int getAdults() {
		return adults;
	}

	/**
	 * Set adults number.
	 * @param adults an adults number Integer.
	 */
	public void setAdults(int adults) {
		this.adults = adults;
	}

	/**
	 * Get children number.
	 * @return int
	 */
	public int getChildren() {
		return children;
	}

	/**
	 * Set children number.
	 * @param children a children number Integer.
	 */
	public void setChildren(int children) {
		this.children = children;
	}

	/**
	 * Get residents.
	 * @return List<FirestationInfo>
	 */
	public List<FirestationInfo> getResidents() {
		return residents;
	}

	/**
	 * Set residents.
	 * @param residents a residents List.
	 */
	public void setResidents(List<FirestationInfo> residents) {
		this.residents = residents;
	}
}
