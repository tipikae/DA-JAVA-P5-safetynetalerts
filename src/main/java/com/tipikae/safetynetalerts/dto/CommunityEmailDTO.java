package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO fot CommunityEmail request.
 * @author tipikae
 * @version 1.0
 *
 */
public class CommunityEmailDTO implements Serializable {

	/**
	 * Emails
	 */
	private List<CommunityEmail> emails;

	/**
	 * The constructor.
	 * @param emails an emails List.
	 */
	public CommunityEmailDTO(List<CommunityEmail> emails) {
		this.emails = emails;
	}

	/**
	 * Get emails.
	 * @return List<CommunityEmail>
	 */
	public List<CommunityEmail> getEmails() {
		return emails;
	}

	/**
	 * Set emails.
	 * @param emails an emails List.
	 */
	public void setEmails(List<CommunityEmail> emails) {
		this.emails = emails;
	}
	
}
