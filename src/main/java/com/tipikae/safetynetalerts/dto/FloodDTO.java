package com.tipikae.safetynetalerts.dto;

import java.io.Serializable;
import java.util.List;

/**
 * The Flood DTO.
 * @author tipikae
 * @version 1.0
 *
 */
public class FloodDTO extends DTOResponse implements Serializable {

	/**
	 * The floods.
	 */
	private List<FloodMaster> floods;

	/** The constructor.
	 * @param floods
	 */
	public FloodDTO(List<FloodMaster> floods) {
		this.floods = floods;
	}

	/**
	 * Get the floods.
	 * @return the floods
	 */
	public List<FloodMaster> getFloods() {
		return floods;
	}

	/**
	 * Set the floods.
	 * @param floods the floods to set
	 */
	public void setFloods(List<FloodMaster> floods) {
		this.floods = floods;
	}
	
}
