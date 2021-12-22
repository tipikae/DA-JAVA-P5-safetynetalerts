package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;

public class CommunityEmail implements Serializable {

	private String email;

	public CommunityEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
