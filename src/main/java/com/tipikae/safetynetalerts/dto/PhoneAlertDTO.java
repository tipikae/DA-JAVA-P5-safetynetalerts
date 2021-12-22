package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

public class PhoneAlertDTO implements Serializable {

	private List<PhoneAlert> phones;

	public PhoneAlertDTO(List<PhoneAlert> phones) {
		this.phones = phones;
	}

	public List<PhoneAlert> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneAlert> phones) {
		this.phones = phones;
	}
	
}
