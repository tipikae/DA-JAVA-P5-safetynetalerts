package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

/**
 * An object for FloodDTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class FloodAddress implements Serializable {

	/**
	 * Address.
	 */
	private String address;
	/**
	 * Members.
	 */
	private List<Flood> members;

	/**
	 * The constructor.
	 * @param address a String address.
	 * @param members a members List.
	 */
	public FloodAddress(String address, List<Flood> members) {
		this.address = address;
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
	 * Get members.
	 * @return List<Flood>
	 */
	public List<Flood> getMembers() {
		return members;
	}

	/**
	 * Set members.
	 * @param members a members List.
	 */
	public void setMembers(List<Flood> members) {
		this.members = members;
	}
	
}
