package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

public class ChildAlertDTO implements Serializable {

	private String address;
	private List<ChildAlert> children;
	private List<ChildAlert> adults;
	
	public ChildAlertDTO(String address, List<ChildAlert> children, List<ChildAlert> adults) {
		this.address = address;
		this.children = children;
		this.adults = adults;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<ChildAlert> getChildren() {
		return children;
	}

	public void setChildren(List<ChildAlert> children) {
		this.children = children;
	}

	public List<ChildAlert> getAdults() {
		return adults;
	}

	public void setAdults(List<ChildAlert> members) {
		this.adults = members;
	}
}
