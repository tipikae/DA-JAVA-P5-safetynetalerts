package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO fot CommunityEmail request.
 * @author tipikae
 * @version 1.0
 *
 */
public class CommunityEmailDTO extends DTOResponse implements Serializable {

	/**
	 * City.
	 */
	private String city;
	/**
	 * Emails.
	 */
	private List<CommunityEmail> emails;

	/**
	 * The constructor.
	 * @param city a String.
	 * @param emails an emails List.
	 */
	public CommunityEmailDTO(String city, List<CommunityEmail> emails) {
		this.city = city;
		this.emails = emails;
	}

	/**
	 * Get city.
	 * @return String
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Set city.
	 * @param city a String.
	 */
	public void setCity(String city) {
		this.city = city;
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
