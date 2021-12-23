package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;

public class PhoneAlert implements Serializable {

	private String phone;

	public PhoneAlert(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
