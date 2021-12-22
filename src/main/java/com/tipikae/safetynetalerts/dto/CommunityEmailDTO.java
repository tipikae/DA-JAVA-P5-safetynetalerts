package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

public class CommunityEmailDTO implements Serializable {

	private List<CommunityEmail> emails;

	public CommunityEmailDTO(List<CommunityEmail> emails) {
		this.emails = emails;
	}

	public List<CommunityEmail> getEmails() {
		return emails;
	}

	public void setEmails(List<CommunityEmail> emails) {
		this.emails = emails;
	}
	
}
