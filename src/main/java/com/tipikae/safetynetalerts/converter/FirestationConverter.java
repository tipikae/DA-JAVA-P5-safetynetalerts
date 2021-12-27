package com.tipikae.safetynetalerts.converter;

import java.util.ArrayList;
import java.util.List;

import com.tipikae.safetynetalerts.dto.FirestationDTO;
import com.tipikae.safetynetalerts.model.Firestation;

/**
 * A class for converting DTO Firestation to entity and vice-versa.
 * @author tipikae
 * @version 1.0
 *
 */
public class FirestationConverter {

	/**
	 * Convert DTO to entity.
	 * @param dto a FirestationDTO object.
	 * @return Firestation
	 */
	public static Firestation toEntity(FirestationDTO dto) {
		return new Firestation(dto.getAddress(), dto.getStation());
	}

	/**
	 * Convert entity to DTO
	 * @param entity a Firestation object.
	 * @return FirestationDTO
	 */
	public static FirestationDTO toDTO(Firestation entity) {
		return new FirestationDTO(entity.getAddress(), entity.getStation());
	}

	/**
	 * Convert a List<Firestation> to a List<FirestationDTO>
	 * @param entities a List of Firestation
	 * @return List<FirestationDTO>
	 */
	public static List<FirestationDTO> toDTOs(List<Firestation> entities) {
		if(entities == null)
			return null;
		
		List<FirestationDTO> dtos = new ArrayList<>();
		for(Firestation entity: entities) {
			FirestationDTO dto = toDTO(entity);
			dtos.add(dto);
		}
		return dtos;
	}
}
