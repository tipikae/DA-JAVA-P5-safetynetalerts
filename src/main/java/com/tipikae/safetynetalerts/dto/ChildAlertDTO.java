package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for ChildAlert request.
 * @author tipikae
 * @version 1.0
 *
 */
public class ChildAlertDTO extends DTOResponse implements Serializable {

	/**
	 * Address.
	 */
	private String address;
	/**
	 * Children.
	 */
	private List<ChildAlert> children;
	/**
	 * Adults
	 */
	private List<ChildAlert> adults;
	
	/**
	 * The constructor.
	 * @param address a String address.
	 * @param children a children List.
	 * @param adults an adults List.
	 */
	public ChildAlertDTO(String address, List<ChildAlert> children, List<ChildAlert> adults) {
		this.address = address;
		this.children = children;
		this.adults = adults;
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
	 * Get children.
	 * @return List<ChildAlert>
	 */
	public List<ChildAlert> getChildren() {
		return children;
	}

	/**
	 * Set children.
	 * @param children a children List.
	 */
	public void setChildren(List<ChildAlert> children) {
		this.children = children;
	}

	/**
	 * Get adults.
	 * @return List<ChildAlert>
	 */
	public List<ChildAlert> getAdults() {
		return adults;
	}

	/**
	 * Set adults.
	 * @param members an adults List.
	 */
	public void setAdults(List<ChildAlert> members) {
		this.adults = members;
	}
}
